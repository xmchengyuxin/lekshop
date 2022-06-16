package com.chengyu.core.service.floor.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.domain.result.FloorDataResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.FloorMobilePagesMapper;
import com.chengyu.core.mapper.FloorMobileTemplateMapper;
import com.chengyu.core.model.FloorMobilePages;
import com.chengyu.core.model.FloorMobilePagesExample;
import com.chengyu.core.model.FloorMobileTemplate;
import com.chengyu.core.model.FloorMobileTemplateExample;
import com.chengyu.core.service.floor.FloorService;
import com.chengyu.core.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {
	
	@Autowired
	private FloorMobileTemplateMapper floorMobileTemplateMapper;
	@Autowired
	private FloorMobilePagesMapper floorMobilePagesMapper;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addFloorMobileTemplate(FloorMobileTemplate template) {
		template.setAddTime(DateUtil.date());
		template.setUpdTime(template.getAddTime());
		template.setStatus(CommonConstant.NO_INT);
		floorMobileTemplateMapper.insertSelective(template);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateFloorMobileTemplate(FloorMobileTemplate template) {
		template.setUpdTime(DateUtil.date());
		floorMobileTemplateMapper.updateByPrimaryKeySelective(template);

		if(template.getStatus() == CommonConstant.YES_INT){
			this.updateDefault(template.getId(), template.getType());
		}
	}

	private void updateDefault(Integer id, Integer type){
		//如果是首页模板的话，启用该模板，其他模板自动关闭
		FloorMobileTemplateExample example = new FloorMobileTemplateExample();
		example.createCriteria().andIdNotEqualTo(id).andTypeEqualTo(1);
		FloorMobileTemplate updateTemplate = new FloorMobileTemplate();
		updateTemplate.setStatus(CommonConstant.NO_INT);
		floorMobileTemplateMapper.updateByExampleSelective(updateTemplate, example);

		if(type == 1){
			redisUtil.delete(RedisEnums.MOBILE_INDEX_TEMPLATE.getKey());
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteFloorMobileTemplateById(Integer id) throws ServiceException {
		if(id == 1){
			throw new ServiceException("系统默认模板不可删除");
		}
		FloorMobileTemplate template = floorMobileTemplateMapper.selectByPrimaryKey(id);
		if(template.getStatus() == CommonConstant.YES_INT){
			throw new ServiceException("当前正在使用此模板,请先设置新的模板才能进行删除");
		}
		floorMobileTemplateMapper.deleteByPrimaryKey(id);

		FloorMobilePagesExample example = new FloorMobilePagesExample();
		example.createCriteria().andTemplateIdEqualTo(id);
		floorMobilePagesMapper.deleteByExample(example);
	}

	@Override
	public List<FloorMobileTemplate> getFloorMobileTemplateList(Integer type, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		FloorMobileTemplateExample example = new FloorMobileTemplateExample();
		example.setOrderByClause("add_time desc");
		FloorMobileTemplateExample.Criteria criteria = example.createCriteria();
		if(type != null){
			criteria.andTypeEqualTo(type);
		}
		return floorMobileTemplateMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void saveMobileData(Integer templateId, String name, Integer type, Integer status, String pageData, String oriData) {
		Date now = DateUtil.date();
		FloorMobileTemplate template = new FloorMobileTemplate();
		template.setId(templateId);
		template.setName(name);
		template.setStatus(status);
		template.setType(type);
		template.setOriData(oriData);
		template.setUpdTime(now);
		if(templateId == null){
			template.setAddTime(now);
			floorMobileTemplateMapper.insertSelective(template);
		}else{
			floorMobileTemplateMapper.updateByPrimaryKeySelective(template);

			//先删除旧配置
			FloorMobilePagesExample example = new FloorMobilePagesExample();
			example.createCriteria().andTemplateIdEqualTo(templateId);
			floorMobilePagesMapper.deleteByExample(example);
		}


		List<FloorDataResult> list = JSONArray.parseArray(pageData, FloorDataResult.class);
		int i = 1;
		for(FloorDataResult floorData : list){
			FloorMobilePages page = new FloorMobilePages();
			page.setModuleKey(floorData.getType());
			page.setModuleName(floorData.getName());
			page.setModuleData(floorData.getOptions());
			page.setTemplateId(template.getId());
			page.setSort(i);
			page.setAddTime(now);
			page.setUpdTime(now);
			floorMobilePagesMapper.insertSelective(page);
			i++;
		}

		if(template.getStatus() == CommonConstant.YES_INT){
			if(template.getType() == null){
				template = floorMobileTemplateMapper.selectByPrimaryKey(template.getId());
			}
			this.updateDefault(template.getId(), template.getType());
		}
	}

	@Override
	public List<FloorMobilePages> getCurrentIndexFloor() {
		List<FloorMobilePages> list = (List<FloorMobilePages>) redisUtil.getRedisValue(RedisEnums.MOBILE_INDEX_TEMPLATE.getKey());
		if(CollectionUtil.isNotEmpty(list)){
			return list;
		}
		FloorMobileTemplateExample example = new FloorMobileTemplateExample();
		example.createCriteria().andTypeEqualTo(1).andStatusEqualTo(CommonConstant.YES_INT);
		List<FloorMobileTemplate> templateList = floorMobileTemplateMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(templateList)){
			return null;
		}
		FloorMobilePagesExample pagesExample = new FloorMobilePagesExample();
		pagesExample.setOrderByClause("sort asc");
		pagesExample.createCriteria().andTemplateIdEqualTo(templateList.get(0).getId());
		list = floorMobilePagesMapper.selectByExampleWithBLOBs(pagesExample);
		if(CollectionUtil.isNotEmpty(list)){
			redisUtil.setRedisValue(RedisEnums.MOBILE_INDEX_TEMPLATE.getKey(), list);
		}
		return list;
	}

	@Override
	public List<FloorMobilePages> getFloorMobileDataList(Integer templateId) {
		FloorMobilePagesExample pagesExample = new FloorMobilePagesExample();
		pagesExample.setOrderByClause("sort asc");
		pagesExample.createCriteria().andTemplateIdEqualTo(templateId);
		return floorMobilePagesMapper.selectByExampleWithBLOBs(pagesExample);
	}

	@Override
	public FloorMobileTemplate getFloorMobileTemplate(Integer templateId) {
		return floorMobileTemplateMapper.selectByPrimaryKey(templateId);
	}
}