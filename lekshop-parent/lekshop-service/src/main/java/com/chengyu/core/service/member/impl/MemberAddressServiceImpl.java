package com.chengyu.core.service.member.impl;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsMemberAddressMapper;
import com.chengyu.core.model.UmsMemberAddress;
import com.chengyu.core.model.UmsMemberAddressExample;
import com.chengyu.core.service.member.MemberAddressService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class MemberAddressServiceImpl implements MemberAddressService {

	@Autowired
	private UmsMemberAddressMapper addressMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addAddress(UmsMemberAddress address) throws ServiceException {
		//判断该用户是否有默认收货地址
		UmsMemberAddress defaultAddress = this.getDefaultAddressByMemberId(address.getMemberId());
		if(defaultAddress == null){
			address.setStatus(CommonConstant.YES_INT);
		}
		
		//判断是否设为默认地址
		if("1".equals(address.getStatus())){
			UmsMemberAddressExample example = new UmsMemberAddressExample();
			example.createCriteria().andMemberIdEqualTo(address.getMemberId());
			
			UmsMemberAddress updateAddress = new UmsMemberAddress();
			updateAddress.setStatus(CommonConstant.NO_INT);
			addressMapper.updateByExampleSelective(updateAddress, example);
		}
		address.setAddTime(new Date());
		addressMapper.insert(address);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateAddress(UmsMemberAddress address) {
		//判断是否设为默认地址
		if(address.getStatus() == CommonConstant.YES_INT){
			UmsMemberAddressExample example = new UmsMemberAddressExample();
			example.createCriteria().andMemberIdEqualTo(address.getMemberId());

			UmsMemberAddress updateAddress = new UmsMemberAddress();
			updateAddress.setStatus(CommonConstant.NO_INT);
			addressMapper.updateByExampleSelective(updateAddress, example);
		}

		addressMapper.updateByPrimaryKeySelective(address);
	}

	@Override
	public UmsMemberAddress getDefaultAddressByMemberId(Integer memberId) {
		UmsMemberAddressExample example = new UmsMemberAddressExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo(CommonConstant.YES_INT);
		List<UmsMemberAddress> list = addressMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public UmsMemberAddress getAddressById(Integer id) {
		return addressMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UmsMemberAddress> getAddressList(Integer memberId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		UmsMemberAddressExample example = new UmsMemberAddressExample();
		example.setOrderByClause("status desc");
		example.createCriteria().andMemberIdEqualTo(memberId);
		return addressMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteAddress(Integer id) {
		addressMapper.deleteByPrimaryKey(id);
	}
	
}