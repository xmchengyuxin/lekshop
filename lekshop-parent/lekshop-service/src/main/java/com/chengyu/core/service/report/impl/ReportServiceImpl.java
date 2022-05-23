package com.chengyu.core.service.report.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.enums.OrderEnums;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.domain.form.ShopSearchForm;
import com.chengyu.core.domain.result.RebuyCountResult;
import com.chengyu.core.domain.result.RepGoodsCountResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.*;
import com.chengyu.core.model.*;
import com.chengyu.core.service.goods.GoodsService;
import com.chengyu.core.service.report.ReportService;
import com.chengyu.core.service.shop.ShopService;
import com.chengyu.core.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ShopService shopService;
	@Autowired
	private CustomReportMapper reportMapper;
	@Autowired
	private OmsOrderMapper orderMapper;
	@Autowired
	private RepConversionDayMapper conversionDayMapper;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private RepPlatformDayMapper platformDayMapper;
	@Autowired
	private RepPlatformGoodsDayMapper platformGoodsDayMapper;
	@Autowired
	private RepShopDayMapper shopDayMapper;
	@Autowired
	private RepShopGoodsDayMapper shopGoodsDayMapper;

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void genShopDayReport(Date beginTime, Date endTime) throws ServiceException {
		ShopSearchForm form = new ShopSearchForm();
		form.setStatus(CommonConstant.SUS_INT);
		List<UmsShop> shopList = shopService.getShopList(form, 1, 9999);
		if(CollectionUtils.isEmpty(shopList)) {
			return;
		}

		this.genShopDayReportByShop(shopList, beginTime, endTime);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void genPlatformDayReport(Date beginTime, Date endTime) {
		this.genPlatformDayReportByAll(beginTime, endTime);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void genMemberDayReport(Date beginTime, Date endTime) {
		reportMapper.genMemberDayReport(beginTime, endTime);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void genConversionDayReport(Date beginTime, Date endTime) {
		RepConversionDayExample example = new RepConversionDayExample();
		example.createCriteria().andDayEqualTo(DateUtil.parseDate(DateUtil.formatDate(beginTime)));
		List<RepConversionDay> dayList = conversionDayMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(dayList)){
			RepConversionDay day = new RepConversionDay();
			day.setDay(new Date());
			day.setXcxOpenNum(0);
			day.setAppOpenNum(0);
			day.setTransRate(BigDecimal.ZERO);
			day.setAddTime(day.getDay());
			day.setUpdTime(day.getAddTime());
			conversionDayMapper.insertSelective(day);
		} else{
			RepConversionDay day = dayList.get(0);
			RepConversionDay updateDay = new RepConversionDay();
			updateDay.setId(day.getId());
			
			//本日成交单数
			OmsOrderExample orderExample = new OmsOrderExample();
			orderExample.createCriteria().andStatusIn(CollectionUtil.newArrayList(
					OrderEnums.OrderStatus.WAIT_DELIVERY.getValue(),
					OrderEnums.OrderStatus.WAIT_RECEIVE.getValue(),
					OrderEnums.OrderStatus.FINISHED.getValue()))
			.andAddTimeBetween(beginTime, endTime);
			long count = orderMapper.countByExample(orderExample);
			
			updateDay.setTransRate(NumberUtils.div(new BigDecimal(day.getAppOpenNum()+day.getXcxOpenNum()), new BigDecimal(count), 2));
			updateDay.setUpdTime(new Date());
			conversionDayMapper.updateByPrimaryKeySelective(updateDay);
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void genShopMonthReport(Date beginTime, Date endTime) {
		String month = DateUtil.formatDate(beginTime).substring(0, 7);
		reportMapper.genShopMonthReport(beginTime, endTime, month);
		reportMapper.genShopGoodsMonthReport(beginTime, endTime, month);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void genPlatformMonthReport(Date beginTime, Date endTime) {
		String month = DateUtil.formatDate(beginTime).substring(0, 7);
		reportMapper.genPlatformMonthReport(beginTime, endTime, month);
		reportMapper.genPlatformGoodsMonthReport(beginTime, endTime, month);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void genMemberMonthReport(Date beginTime, Date endTime) {
		String month = DateUtil.formatDate(beginTime).substring(0, 7);
		reportMapper.genMemberMonthReport(beginTime, endTime, month);
	}

	@Override
	public void updateConversion(String type) {
		RepConversionDayExample example = new RepConversionDayExample();
		example.createCriteria().andDayEqualTo(DateUtil.parseDate(DateUtil.today()));
		List<RepConversionDay> dayList = conversionDayMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(dayList)){
			RepConversionDay day = new RepConversionDay();
			day.setDay(new Date());
			day.setXcxOpenNum("1".equals(type) ? 1 : 0);
			day.setAppOpenNum("1".equals(type) ? 0 : 1);
			day.setTransRate(BigDecimal.ZERO);
			day.setAddTime(day.getDay());
			day.setUpdTime(day.getAddTime());
			conversionDayMapper.insertSelective(day);
		} else{
			reportMapper.updateConversion(type);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void reGenDayReport(Integer shopId, Integer platformId, Date now) throws ServiceException {
		Date beginTime = DateUtil.beginOfDay(now);
		Date endTime = DateUtil.endOfDay(now);
		//生成店铺日报表
		if(shopId != null){
			RepShopDayExample shopDayExample = new RepShopDayExample();
			shopDayExample.createCriteria().andShopIdEqualTo(shopId).andDayEqualTo(now);
			shopDayMapper.deleteByExample(shopDayExample);

			RepShopGoodsDayExample goodsDayExample = new RepShopGoodsDayExample();
			goodsDayExample.createCriteria().andShopIdEqualTo(shopId).andDayEqualTo(now);
			shopGoodsDayMapper.deleteByExample(goodsDayExample);

			UmsShop shop = shopService.getShopById(shopId);
			this.genShopDayReportByShop(CollectionUtil.newArrayList(shop), beginTime, endTime);
		}
		//生成平台日报表
		if(platformId != null){
			RepPlatformDayExample platformDayExample = new RepPlatformDayExample();
			platformDayExample.createCriteria().andDayEqualTo(now);
			platformDayMapper.deleteByExample(platformDayExample);

			RepPlatformGoodsDayExample goodsDayExample = new RepPlatformGoodsDayExample();
			goodsDayExample.createCriteria().andDayEqualTo(now);
			platformGoodsDayMapper.deleteByExample(goodsDayExample);

			this.genPlatformDayReportByAll(beginTime, endTime);
		}
	}

	public void genShopDayReportByShop(List<UmsShop> shopList, Date beginTime, Date endTime) throws ServiceException{
		List<RepShopDay> repList = new ArrayList<>();
		List<RepShopGoodsDay> repGoodsList = new ArrayList<>();

		for(UmsShop shop : shopList){
			RepShopDay shopDay = reportMapper.getReportShopDayData(shop.getId(), beginTime, endTime);
			if(shopDay == null ) {
				shopDay = new RepShopDay();
			}
			shopDay.setShopId(shop.getId());
			shopDay.setShopName(shop.getName());
			shopDay.setDay(beginTime);
			shopDay.setRefundRate(NumberUtils.div(new BigDecimal(shopDay.getCancelNum()), new BigDecimal(shopDay.getOrderNum()), 2));
			shopDay.setAddTime(beginTime);
			shopDay.setUpdTime(shopDay.getAddTime());
			shopDayMapper.insertSelective(shopDay);

			GoodsSearchForm form = new GoodsSearchForm();
			form.setStatus(GoodsEnums.GoodsStatus.SELL.getValue());
			form.setShopId(shop.getId());
			List<PmsGoods> goodsList = goodsService.getGoodsList(form, 1, 9999);
			List<RepGoodsCountResult> goodsCountResultList = reportMapper.getRepGoodsCountResult(shop.getId(), beginTime, endTime);
			Map<Integer,List<RepGoodsCountResult>> goodsMap = goodsCountResultList.stream()
					.collect(Collectors.groupingBy(RepGoodsCountResult::getGoodsId));
			for(PmsGoods goods : goodsList){
				RepShopGoodsDay goodsDay = new RepShopGoodsDay();
				goodsDay.setShopId(shop.getId());
				goodsDay.setShopName(shop.getName());
				goodsDay.setDay(beginTime);
				goodsDay.setAddTime(beginTime);
				goodsDay.setUpdTime(beginTime);
				List<RepGoodsCountResult> flist = goodsMap.get(goodsDay.getId());
				if(CollectionUtil.isEmpty(flist)){
					goodsDay.setGoodsId(goods.getId());
					goodsDay.setGoodsName(goods.getTitle());
					goodsDay.setGoodsMainImg(goods.getMainImg());
					goodsDay.setCateId(goods.getCateId());
					goodsDay.setCateName(goods.getCateName());
					goodsDay.setPrice(goods.getPrice());
					goodsDay.setSellNum(0);
					goodsDay.setSellAmount(BigDecimal.ZERO);
					goodsDay.setGoodCommentNum(0);
					goodsDay.setMediumCommentNum(0);
					goodsDay.setBadCommentNum(0);
					goodsDay.setRebuyNum(0);
					goodsDay.setRebuyRate(BigDecimal.ZERO);
					goodsDay.setBuyTwoMannum(0);
					goodsDay.setBuyMannum(0);
				}else{
					RepGoodsCountResult goodsCountResult = flist.get(0);
					goodsDay.setCateId(goods.getCateId());
					goodsDay.setCateName(goods.getCateName());
					goodsDay.setPrice(goods.getPrice());
					goodsDay.setGoodsId(goodsCountResult.getGoodsId());
					goodsDay.setGoodsName(goodsCountResult.getGoodsName());
					goodsDay.setGoodsMainImg(goodsCountResult.getMainImg());
					goodsDay.setSellNum(goodsCountResult.getSellNum());
					goodsDay.setSellAmount(goodsCountResult.getSellAmount());
					goodsDay.setGoodCommentNum(goodsCountResult.getGoodCommentNum());
					goodsDay.setMediumCommentNum(goodsCountResult.getMediumCommentNum());
					goodsDay.setBadCommentNum(goodsCountResult.getBadCommentNum());

					RebuyCountResult result = reportMapper.getRebuyCount(shop.getId(), goods.getId(), beginTime, endTime);
					goodsDay.setBuyMannum(result.getBuyManNum());//该商品购买总人数
					goodsDay.setRebuyNum(result.getRebuyNum());//复购销量
					goodsDay.setBuyTwoMannum(result.getBuyTwoMannum());//购买2次以上人数
					goodsDay.setRebuyRate(NumberUtils.div(new BigDecimal(goodsDay.getBuyTwoMannum()), new BigDecimal(goodsDay.getBuyMannum()), 2));//复购率(购买两次以上人数/购买总人数)
				}
				shopGoodsDayMapper.insertSelective(goodsDay);
			}
		}
	}

	public void genPlatformDayReportByAll(Date beginTime, Date endTime) {
		RepPlatformDay platformDay = reportMapper.getReportPlatformDayData(beginTime, endTime);
		if(platformDay == null ) {
			platformDay = new RepPlatformDay();
		}
		platformDay.setDay(beginTime);
		platformDay.setAddTime(beginTime);
		platformDay.setUpdTime(platformDay.getAddTime());
		platformDayMapper.insertSelective(platformDay);

		GoodsSearchForm form = new GoodsSearchForm();
		form.setStatus(GoodsEnums.GoodsStatus.SELL.getValue());
		List<PmsGoods> goodsList = goodsService.getGoodsList(form, 1, 999999);

		List<RepGoodsCountResult> goodsCountResultList = reportMapper.getRepGoodsCountResult(null, beginTime, endTime);
		Map<Integer,List<RepGoodsCountResult>> goodsMap = goodsCountResultList.stream()
				.collect(Collectors.groupingBy(RepGoodsCountResult::getGoodsId));
		for(PmsGoods goods : goodsList){
			RepPlatformGoodsDay goodsDay = new RepPlatformGoodsDay();
			goodsDay.setDay(beginTime);
			goodsDay.setAddTime(beginTime);
			goodsDay.setUpdTime(beginTime);
			List<RepGoodsCountResult> flist = goodsMap.get(goods.getId());
			if(CollectionUtil.isEmpty(flist)){
				goodsDay.setGoodsId(goods.getId());
				goodsDay.setGoodsName(goods.getTitle());
				goodsDay.setGoodsMainImg(goods.getMainImg());
				goodsDay.setCateId(goods.getCateId());
				goodsDay.setCateName(goods.getCateName());
				goodsDay.setPrice(goods.getPrice());
				goodsDay.setSellNum(0);
				goodsDay.setSellAmount(BigDecimal.ZERO);
				goodsDay.setGoodCommentNum(0);
				goodsDay.setMediumCommentNum(0);
				goodsDay.setBadCommentNum(0);
				goodsDay.setRebuyNum(0);
				goodsDay.setRebuyRate(BigDecimal.ZERO);
				goodsDay.setBuyTwoMannum(0);
				goodsDay.setBuyMannum(0);
			}else{
				RepGoodsCountResult goodsCountResult = flist.get(0);
				goodsDay.setCateId(goods.getCateId());
				goodsDay.setCateName(goods.getCateName());
				goodsDay.setPrice(goods.getPrice());
				goodsDay.setGoodsId(goodsCountResult.getGoodsId());
				goodsDay.setGoodsName(goodsCountResult.getGoodsName());
				goodsDay.setGoodsMainImg(goodsCountResult.getMainImg());
				goodsDay.setSellNum(goodsCountResult.getSellNum());
				goodsDay.setSellAmount(goodsCountResult.getSellAmount());
				goodsDay.setGoodCommentNum(goodsCountResult.getGoodCommentNum());
				goodsDay.setMediumCommentNum(goodsCountResult.getMediumCommentNum());
				goodsDay.setBadCommentNum(goodsCountResult.getBadCommentNum());

				RebuyCountResult result = reportMapper.getRebuyCount(null, goods.getId(), beginTime, endTime);
				goodsDay.setBuyMannum(result.getBuyManNum());//该商品购买总人数
				goodsDay.setRebuyNum(result.getRebuyNum());//复购销量
				goodsDay.setBuyTwoMannum(result.getBuyTwoMannum());//购买2次以上人数
				goodsDay.setRebuyRate(NumberUtils.div(new BigDecimal(goodsDay.getBuyTwoMannum()), new BigDecimal(goodsDay.getBuyMannum()), 2));//复购率(购买两次以上人数/购买总人数)
				platformGoodsDayMapper.insertSelective(goodsDay);
			}
		}
	}

}