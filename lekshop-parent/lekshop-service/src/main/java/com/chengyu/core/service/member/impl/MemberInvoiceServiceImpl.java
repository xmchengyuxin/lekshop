package com.chengyu.core.service.member.impl;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.mapper.UmsMemberInvoiceMapper;
import com.chengyu.core.model.UmsMemberInvoice;
import com.chengyu.core.model.UmsMemberInvoiceExample;
import com.chengyu.core.service.member.MemberInvoiceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class MemberInvoiceServiceImpl implements MemberInvoiceService {

	@Autowired
	private UmsMemberInvoiceMapper invoiceMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addInvoice(UmsMemberInvoice invoice) {
		//判断该用户是否有默认发票
		UmsMemberInvoice defaultInvoice = this.getDefaultInvoiceByMemberId(invoice.getMemberId());
		if(defaultInvoice == null){
			invoice.setStatus(CommonConstant.YES_INT);
			invoice.setIsDefault(CommonConstant.YES_INT);
		}
		
		//判断是否设为默认发票
		this.setOtherInvoiceNotDefault(invoice);
		invoice.setAddTime(new Date());
		invoiceMapper.insert(invoice);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateInvoice(UmsMemberInvoice invoice) {
		//判断是否设为默认发票
		this.setOtherInvoiceNotDefault(invoice);
		invoiceMapper.updateByPrimaryKeySelective(invoice);
	}

	private void setOtherInvoiceNotDefault(UmsMemberInvoice invoice){
		UmsMemberInvoiceExample example = new UmsMemberInvoiceExample();
		example.createCriteria().andMemberIdEqualTo(invoice.getMemberId());

		UmsMemberInvoice updateinvoice = new UmsMemberInvoice();
		updateinvoice.setStatus(CommonConstant.NO_INT);
		updateinvoice.setIsDefault(CommonConstant.NO_INT);
		invoiceMapper.updateByExampleSelective(updateinvoice, example);
	}

	@Override
	public UmsMemberInvoice getDefaultInvoiceByMemberId(Integer memberId) {
		UmsMemberInvoiceExample example = new UmsMemberInvoiceExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo(CommonConstant.YES_INT);
		List<UmsMemberInvoice> list = invoiceMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public UmsMemberInvoice getInvoiceById(Integer id) {
		return invoiceMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UmsMemberInvoice> getInvoiceList(Integer memberId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		UmsMemberInvoiceExample example = new UmsMemberInvoiceExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		return invoiceMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteInvoice(Integer id) {
		invoiceMapper.deleteByPrimaryKey(id);
	}
}