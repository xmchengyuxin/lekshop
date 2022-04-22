package com.chengyu.core.service.member.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsMemberImeidMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberImeid;
import com.chengyu.core.model.UmsMemberImeidExample;
import com.chengyu.core.service.member.MemberImeIdService;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  设备
 * @author LeGreen
 * @date   2021/9/13
 */
@Service
public class MemberImeIdServiceImpl implements MemberImeIdService {
	
	@Autowired
	private UmsMemberImeidMapper memberImeidMapper;

	@Override
	public void loginByImeId(UmsMember member, String imeId, String phoneType) throws ServiceException {
		if(StringUtils.isBlank(imeId)){
			return;
		}
		UmsMemberImeidExample example = new UmsMemberImeidExample();
		example.createCriteria().andImeidEqualTo(imeId);
		List<UmsMemberImeid> list = memberImeidMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			UmsMemberImeid imeid = new UmsMemberImeid();
			imeid.setMemberId(member.getId());
			imeid.setMemberName(member.getCode());
			imeid.setImeid(imeId);
			imeid.setPhoneType(phoneType);
			imeid.setAddTime(DateUtil.date());
			memberImeidMapper.insertSelective(imeid);
		}else{
			/*ConfigInviteCode config = configInviteCodeService.getConfigInviteCodeByGroupId(member.getGroupId());
			if(config != null && config.getLimitImeid() != null && config.getLimitImeid() == CommonConstant.YES_INT){*/
				UmsMemberImeid imeid = list.get(0);
				if(!imeid.getMemberId().equals(member.getId())){
					throw new ServiceException("同设备仅能登录一个账号");
				}
//			}
		}
	}

	@Override
	public boolean isLoginByImeId(Integer memberId, String imeId) {
		UmsMemberImeidExample example = new UmsMemberImeidExample();
		example.createCriteria().andImeidEqualTo(imeId).andMemberIdNotEqualTo(memberId);
		return memberImeidMapper.countByExample(example) > 0;
	}

	@Override
	public List<UmsMemberImeid> getAllImeid(Integer memberId) {
		UmsMemberImeidExample example = new UmsMemberImeidExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		return memberImeidMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteImeid(Integer imeId) {
		memberImeidMapper.deleteByPrimaryKey(imeId);
	}
}