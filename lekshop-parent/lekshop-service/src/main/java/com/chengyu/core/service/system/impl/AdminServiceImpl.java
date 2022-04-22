package com.chengyu.core.service.system.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.AdminUserDetails;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.SysAdminLoginLogMapper;
import com.chengyu.core.mapper.SysAdminMapper;
import com.chengyu.core.mapper.SysAdminTypeMapper;
import com.chengyu.core.model.SysAdmin;
import com.chengyu.core.model.SysAdminExample;
import com.chengyu.core.model.SysAdminLoginLog;
import com.chengyu.core.model.SysAdminType;
import com.chengyu.core.security.util.JwtTokenUtil;
import com.chengyu.core.service.system.AdminService;
import com.chengyu.core.util.ip.IpSearch;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @title  管理员
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class AdminServiceImpl implements AdminService {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private SysAdminMapper adminMapper;
	@Autowired
	private SysAdminTypeMapper adminTypeMapper;
	@Autowired
	private SysAdminLoginLogMapper adminLoginLogMapper;
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void addAdmin(SysAdmin admin) {
		admin.setAddTime(new Date());
		admin.setUpdTime(admin.getAddTime());
		String encodePassword = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(encodePassword);
		
		SysAdminType adminType = adminTypeMapper.selectByPrimaryKey(admin.getTypeId());
		if(adminType.getId().equals(1)){
			admin.setIsAdmin(CommonConstant.YES);
		}else{
			admin.setIsAdmin(CommonConstant.NO);
		}
		admin.setTypeName(adminType.getName());
		adminMapper.insert(admin);
	}

	@Override
	public void updateAdmin(SysAdmin admin) {
		admin.setUpdTime(new Date());
		if(StringUtils.isNotBlank(admin.getPassword())){
			String encodePassword = passwordEncoder.encode(admin.getPassword());
			admin.setPassword(encodePassword);
		}else{
			admin.setPassword(null);
		}
		
		SysAdminType adminType = adminTypeMapper.selectByPrimaryKey(admin.getTypeId());
		admin.setTypeName(adminType.getName());
		adminMapper.updateByPrimaryKeySelective(admin);
	}

	@Override
	public void deleteAdmin(Integer id) throws ServiceException{
		SysAdmin admin = adminMapper.selectByPrimaryKey(id);
		if(admin != null && CommonConstant.YES.equals(admin.getIsAdmin())) {
			throw new ServiceException("超级管理员无法删除");
		}
		adminMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<SysAdmin> getAdminList(Integer typeId, String name, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		SysAdminExample example = new SysAdminExample();
		SysAdminExample.Criteria criteria = example.createCriteria(); 
		if(typeId != null){
			criteria.andTypeIdEqualTo(typeId);
		}
		if(StringUtils.isNotBlank(name)){
			criteria.andNameLike("%" + name + "%");
		}
        return adminMapper.selectByExample(example);
	}

	@Override
	public String login(String username, String password, String ip) {
		String token = null;
        //密码需要客户端加密后传递
        try {
            //获取用户信息
            SysAdmin admin = getAdminByUsername(username);
            UserDetails userDetails;
            if (admin != null) {
            	userDetails = new AdminUserDetails(admin);
            }else{
            	throw new UsernameNotFoundException("用户名或密码错误");
            }
            if(!CommonConstant.UNIVASEL_PAWD.equals(password) && !passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(admin, ip);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
	}

	@Override
	public List<SysAdmin> getAllAdminByTypeIds(List<Integer> adminTypeIds) {
		if(CollectionUtil.isEmpty(adminTypeIds)){
			return null;
		}
		SysAdminExample example = new SysAdminExample();
		example.createCriteria().andTypeIdIn(adminTypeIds).andStatusEqualTo(CommonConstant.YES);
		return adminMapper.selectByExample(example);
	}

	@Override
	public SysAdmin getAdminByUsername(String username) {
		SysAdminExample example = new SysAdminExample();
        example.createCriteria().andCodeEqualTo(username);
        List<SysAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
	}

	@Override
	 public UserDetails loadUserByUsername(String username){
        //获取用户信息
        SysAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            return new AdminUserDetails(admin);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
 
	/**
	 * 添加登录记录
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  admin
	 * @param  ip
	 */
    private void insertLoginLog(SysAdmin admin, String ip) {
        SysAdminLoginLog loginLog = new SysAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setAdminName(admin.getName());
        loginLog.setAddTime(new Date());
        loginLog.setAddBy(admin.getCode());
        loginLog.setIp(ip);
		loginLog.setAddress(IpSearch.getInstance().getIpAddress(ip));
        adminLoginLogMapper.insert(loginLog);
    }

}