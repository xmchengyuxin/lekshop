package com.chengyu.core.service.system.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.SysThirdWarnMapper;
import com.chengyu.core.model.SysAlismsConfig;
import com.chengyu.core.model.SysThirdWarn;
import com.chengyu.core.model.SysThirdWarnExample;
import com.chengyu.core.service.system.AlismsConfigService;
import com.chengyu.core.service.system.ApproveConfigService;
import com.chengyu.core.service.system.ThirdWarnService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.util.sms.DuanxinBaoSms;
import com.chengyu.core.util.sms.SmsUtil;
import com.chengyu.core.util.third.manager.ThirdManager;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class ThirdWarnServiceImpl implements ThirdWarnService {
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysThirdWarnMapper configMapper;
	@Autowired
	private SmsUtil smsUtil;

	@Override
	public List<SysThirdWarn> getAllConfig() {
		SysThirdWarnExample example = new SysThirdWarnExample();
		example.createCriteria().andStatusEqualTo(CommonConstant.SUS_INT);
		return configMapper.selectByExample(example);
	}

	@Override
	public void updateConfig(SysThirdWarn config) {
		configMapper.updateByPrimaryKeySelective(config);
		redisUtil.delete(RedisEnums.THIRD_WARN.getKey());
	}

	@Override
	public void warn() throws Exception {
		//判断是否通知过
		List<SysThirdWarn> list = (List<SysThirdWarn>) redisUtil.getRedisValue(RedisEnums.THIRD_WARN.getKey());
		if(CollectionUtil.isEmpty(list)){
			list = this.getAllConfig();
			redisUtil.setRedisValue(RedisEnums.THIRD_WARN.getKey(), list);
		}
		if(CollectionUtil.isNotEmpty(list)){
			String noticeKey = "WARN_NOTICE_KEY";
			for(SysThirdWarn warn : list){
				String notice = (String) redisUtil.getRedisValue(noticeKey+"-"+warn.getNid()+warn.getInterfaceNid());
				if(StringUtils.isNotBlank(notice)){
					continue;
				}
				if("0".equals(warn.getWarnPhone()) && "0".equals(warn.getWarnEmail())){
					continue;
				}
				//查询余额
				BigDecimal balance = this.getLeftAmount(warn.getNid(), warn.getInterfaceNid());
				if(balance == null){
					continue;
				}
				if(balance.compareTo(warn.getWarnAmount()) <= 0){
					//发通知
					try {
						if(!"0".equals(warn.getWarnPhone())){
							String content = warn.getName()+"接口余额不足, 请及时充值!";
							Map<String, String> templateParam = new HashMap<>();
							templateParam.put("content", content);
							smsUtil.send(warn.getWarnPhone(), "4", templateParam);
						}
						if(!"0".equals(warn.getWarnEmail())){
							//邮箱通知TODO
						}

						redisUtil.setRedisValue(noticeKey+"-"+warn.getNid()+warn.getInterfaceNid(), "Noticed", 1L, TimeUnit.DAYS);
					}catch (Exception ignored){
					}
				}
			}
		}
	}

	@Autowired
	private AlismsConfigService alismsConfigService;
	@Autowired
	private ApproveConfigService approveConfigService;

	@Override
	public BigDecimal getLeftAmount(String nid, String interfaceNid) throws Exception {
		try {
			switch (nid) {
				case ThirdManager.DXB:
					SysAlismsConfig smsConfig = alismsConfigService.getAlismsConfig();
					return new BigDecimal(new DuanxinBaoSms().getBalance(smsConfig.getAccount(), smsConfig.getPassword()));
				case ThirdManager.WWYW:
				/*SysApproveConfig approveConfig = approveConfigService.getApproveConfig();
				if("idcard".equals(interfaceNid)){

				}else if("jd_detail".equals(interfaceNid)){

				}else if("pdd_detail".equals(interfaceNid)){

				}*/
					return null;
				default:
					return null;
			}
		}catch (Exception e){
			return null;
		}

	}
}