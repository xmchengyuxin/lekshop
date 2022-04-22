package com.chengyu.core.service.system.impl;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.SysVerifycodeMapper;
import com.chengyu.core.model.SysVerifycode;
import com.chengyu.core.model.SysVerifycodeExample;
import com.chengyu.core.service.system.VerifyCodeService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.util.sms.SmsUtil;
import com.chengyu.core.utils.DateUtil;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
	

	@Autowired
	private SysVerifycodeMapper verifycodeMapper;
	@Autowired
	private SmsUtil smsUtil;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public List<SysVerifycode> getVerifyCodeList(String type, String name, String sendStatus, Integer pageNum, Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		
        SysVerifycodeExample example = new SysVerifycodeExample();
        example.setOrderByClause("send_time desc");
        SysVerifycodeExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(type)){
        	criteria.andTypeEqualTo(type);
        }
        if(StringUtils.isNotBlank(name)){
        	criteria.andNameLike("%"+name+"%");
        }
        if(StringUtils.isNotBlank(sendStatus)){
        	criteria.andSendStatusEqualTo(sendStatus);
        }
        return verifycodeMapper.selectByExample(example);
	}

	@Override
	public void addVerifycode(SysVerifycode verifyCode){
		verifyCode.setSendTime(new Date());
		verifyCode.setSendStatus(CommonConstant.NO);
		verifycodeMapper.insert(verifyCode);
	}

	@Override
	public String getLastVerifyCode(String type, String name) {
		PageHelper.startPage(1, 1);
		
	 	SysVerifycodeExample example = new SysVerifycodeExample();
	 	example.setOrderByClause("send_time desc");
        SysVerifycodeExample.Criteria criteria = example.createCriteria();
    	criteria.andTypeEqualTo(type).andNameEqualTo(name);
    	List<SysVerifycode> list = verifycodeMapper.selectByExample(example);
		if(list != null && !list.isEmpty()) {
			return list.get(0).getCode();
		}
		return null;
	}

	@Override
	public void sendSms(String username, String templateType, Map<String, String> templateParam) throws ServiceException {
		Date sendTime = new Date();
		
		Date lastSendTime = (Date) redisUtil.getRedisValue(RedisEnums.VERIFY_CODE_KEY.getKey() +"-"+username);
		if(lastSendTime != null && (sendTime.getTime() - lastSendTime.getTime()) <= 60000){
			throw new ServiceException("您发送频率过高,请稍后再发送!");
		}
		
		//生成短信验证码
		String code = this.getSixCode();
		SysVerifycode message = new SysVerifycode();
		message.setType("1");
		message.setName(username);
		message.setCode(code);
		message.setSendTime(sendTime);
		message.setSendStatus("0");
		Integer id = verifycodeMapper.insertSelective(message);
		
		//记录发送时间
		redisUtil.setRedisValue(RedisEnums.VERIFY_CODE_KEY.getKey()+"-"+username, sendTime, 300L, TimeUnit.SECONDS);
		
		try {
			String content = "验证码: "+code+", 如非本人操作, 请忽略本短信。";
			templateParam.put("code", code);
			templateParam.put("content", content);
			smsUtil.send(username, templateType, templateParam);

			message.setSendStatus(CommonConstant.SUS);
		} catch (Exception e) {
			e.printStackTrace();
			message.setSendStatus(CommonConstant.FAIL);
			throw new ServiceException("短信发送失败,请稍后重试!");
		}finally {
			message.setId(id);
			verifycodeMapper.updateByPrimaryKeySelective(message);
		}
	}
	
	@Override
	public void validateCode(String userName, String code) throws ServiceException {
		PageHelper.startPage(1, 1);
		
	 	SysVerifycodeExample example = new SysVerifycodeExample();
	 	example.setOrderByClause("send_time desc");
        SysVerifycodeExample.Criteria criteria = example.createCriteria();
    	criteria.andNameEqualTo(userName);
    	//查询5分钟内的验证码
    	criteria.andSendTimeGreaterThanOrEqualTo(DateUtil.addSeconds(new Date(), -300));
    	List<SysVerifycode> list = verifycodeMapper.selectByExample(example);
		if(list == null || list.isEmpty()) {
			throw new ServiceException("验证码错误,请重新输入!");
		}
		
		String verifyCode = list.get(0).getCode();
		if(!CommonConstant.COMMON_CODE.equals(code)){
			if(StringUtils.isBlank(verifyCode) || !verifyCode.equals(code) ){
				throw new ServiceException("验证码错误,请重新输入!");
			}
		}
	}
	
	private String getSixCode(){
		String code = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int r = random.nextInt(10); 
			code = code + r;
		}
		return code;
	}
}