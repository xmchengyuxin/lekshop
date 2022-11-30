package com.chengyu.core.service.shop.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.ConfigEnums;
import com.chengyu.core.domain.result.ShopAccountDetails;
import com.chengyu.core.domain.result.ShopAccountRoleResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.OrgRoleMapper;
import com.chengyu.core.mapper.UmsShopAccountLoginLogMapper;
import com.chengyu.core.mapper.UmsShopAccountMapper;
import com.chengyu.core.mapper.UmsShopAccountRoleMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.security.util.JwtTokenUtil;
import com.chengyu.core.service.shop.ShopAccountService;
import com.chengyu.core.service.system.ConfigService;
import com.chengyu.core.util.ip.IpSearch;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopAccountServiceImpl implements ShopAccountService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShopAccountServiceImpl.class);

	@Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ConfigService configService;
	@Autowired
	private UmsShopAccountMapper accountMapper;
	@Autowired
	private UmsShopAccountLoginLogMapper accountLoginLogMapper;
	@Autowired
	private UmsShopAccountRoleMapper accountRoleMapper;
	@Autowired
	private OrgRoleMapper roleMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void register(UmsShopAccount account) throws ServiceException {
		//校验code是否已被注册
		UmsShopAccount haveAccount = this.getAccountByUsername(account.getUsername());
		if(haveAccount != null) {
			throw new ServiceException("account.register.error");
		}
		if(StringUtils.isBlank(account.getHeadImg())){
			account.setHeadImg(configService.getValueByNid(ConfigEnums.ConfigTypes.HEADIMG.getNid()));
		}
		if(StringUtils.isBlank(account.getNickname())){
			account.setNickname("U"+StringUtils.getSixCode());
		}
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setStatus(CommonConstant.YES_INT);
		account.setAddTime(DateUtil.date());
		accountMapper.insertSelective(account);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateAccount(UmsShopAccount updateAccount) {
		if(StringUtils.isNotBlank(updateAccount.getPassword())){
			updateAccount.setPassword(passwordEncoder.encode(updateAccount.getPassword()));
		}else{
			updateAccount.setPassword(null);
		}
		if(StringUtils.isNotBlank(updateAccount.getPayPassword())){
			updateAccount.setPayPassword(SecureUtil.md5(updateAccount.getPayPassword()));
		}else{
			updateAccount.setPayPassword(null);
		}
		updateAccount.setUpdTime(DateUtil.date());
		accountMapper.updateByPrimaryKeySelective(updateAccount);
	}

	@Override
	public List<UmsShopAccount> getAccountList(Integer shopId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsShopAccountExample example = new UmsShopAccountExample();
		example.setOrderByClause("add_time desc");
		UmsShopAccountExample.Criteria criteria = example.createCriteria();
		if(shopId != null) {
			criteria.andShopIdEqualTo(shopId);
		}
		return accountMapper.selectByExample(example);
	}

	@Override
	public UmsShopAccount getAccountById(Integer id) {
		return accountMapper.selectByPrimaryKey(id);
	}

	@Override
	public UmsShopAccount getAccountByUsername(String username) {
		UmsShopAccountExample example = new UmsShopAccountExample();
		UmsShopAccountExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<UmsShopAccount> list = accountMapper.selectByExample(example);
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}

	@Override
	public UmsShopAccount getCurrentAccount() {
		try {
			SecurityContext ctx = SecurityContextHolder.getContext();
	        Authentication auth = ctx.getAuthentication();
			ShopAccountDetails accountDetails = (ShopAccountDetails) auth.getPrincipal();
	        return accountDetails.getShopAccount();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		
		return null;
	}
	
	@Override
	public UmsShopAccount getCurrentAccountOrNull() {
		try {
			SecurityContext ctx = SecurityContextHolder.getContext();
			Authentication auth = ctx.getAuthentication();
			String nullKey = "anonymousUser";
			if(nullKey.equals(auth.getPrincipal())) {
				return null;
			}

			ShopAccountDetails accountDetails = (ShopAccountDetails) auth.getPrincipal();
			return accountDetails.getShopAccount();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username){
		UmsShopAccount account = getAccountByUsername(username);
		if(account!=null){
            return new ShopAccountDetails(account);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
	}

	@Override
	public String login(String username, String password, String ip) {
		String token = null;
        //密码需要客户端加密后传递
		UserDetails userDetails = null;
		UmsShopAccount account = getAccountByUsername(username);
		if(account!=null){
			userDetails = new ShopAccountDetails(account);
		}
		if(userDetails == null){
			throw new UsernameNotFoundException("用户名或密码错误");
		}
        try {
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            this.insertLoginLog(account, ip);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
	}

	@Override
	public String loginByNoPassword(String username) {
		String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
	}

	@Override
	public UmsShopAccountLoginLog getLastLoginLog(Integer accountId) {
		UmsShopAccountLoginLogExample example = new UmsShopAccountLoginLogExample();
		example.setOrderByClause("add_time desc limit 2");
		example.createCriteria().andAccountIdEqualTo(accountId);
		List<UmsShopAccountLoginLog> list = accountLoginLogMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}else if(list.size() == 1){
			return list.get(0);
		}else{
			return list.get(1);
		}
	}

	/**
	 * 添加登录记录
	 * @author LeGreen
	 * @date   2021/1/6
	 */
	private void insertLoginLog(UmsShopAccount account, String ip) {
		UmsShopAccountLoginLog loginLog = new UmsShopAccountLoginLog();
		loginLog.setAccountId(account.getId());
		loginLog.setAccountName(account.getUsername());
		loginLog.setAddTime(new Date());
		loginLog.setAddBy(account.getUsername());
		loginLog.setIp(ip);
		loginLog.setAddress(IpSearch.getInstance().getIpAddress(ip));
		accountLoginLogMapper.insert(loginLog);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addRole(Integer accountId, String roleIds) {
		if (StringUtils.isNotBlank(roleIds)) {
			OrgRole firstRole = null;
			for (String roleId : roleIds.split(CommonConstant.DH_REGEX)) {
				UmsShopAccountRoleExample example = new UmsShopAccountRoleExample();
				example.createCriteria().andAccountIdEqualTo(accountId).andRoleIdEqualTo(Integer.parseInt(roleId));
				if(accountRoleMapper.countByExample(example) > 0){
					continue;
				}
				OrgRole role = roleMapper.selectByPrimaryKey(Integer.parseInt(roleId));
				UmsShopAccountRole accountRole = new UmsShopAccountRole();
				accountRole.setAccountId(accountId);
				accountRole.setRoleId(role.getId());
				accountRole.setDeptId(role.getDeptId());
				accountRoleMapper.insertSelective(accountRole);
				if(firstRole == null){
					firstRole = role;
				}
			}
			UmsShopAccount account = this.getAccountById(accountId);
			if(account.getRoleId() == null){
				UmsShopAccount updateAccount = new UmsShopAccount();
				updateAccount.setId(accountId);
				updateAccount.setRoleId(firstRole.getId());
				updateAccount.setRoleName(firstRole.getRoleName());
				updateAccount.setDeptId(firstRole.getDeptId());
				updateAccount.setDeptName(firstRole.getDeptName());
				this.updateAccount(updateAccount);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAccountRole(Integer accountRoleId) {
		UmsShopAccountRole accountRole = accountRoleMapper.selectByPrimaryKey(accountRoleId);
		accountRoleMapper.deleteByPrimaryKey(accountRoleId);
		UmsShopAccount account = accountMapper.selectByPrimaryKey(accountRole.getAccountId());
		if(account.getRoleId() != null && account.getRoleId().equals(accountRole.getRoleId())){
			//更新用户的默认角色
			UmsShopAccountRoleExample example = new UmsShopAccountRoleExample();
			example.createCriteria().andAccountIdEqualTo(account.getId());
			List<UmsShopAccountRole> accountRoleList = accountRoleMapper.selectByExample(example);
			if(CollectionUtil.isNotEmpty(accountRoleList)) {
				OrgRole currentRole = roleMapper.selectByPrimaryKey(accountRoleList.get(0).getRoleId());
				UmsShopAccount updateAccount = new UmsShopAccount();
				updateAccount.setId(account.getId());
				updateAccount.setRoleId(currentRole.getId());
				updateAccount.setRoleName(currentRole.getRoleName());
				updateAccount.setDeptId(currentRole.getDeptId());
				updateAccount.setDeptName(currentRole.getDeptName());
				accountMapper.updateByPrimaryKeySelective(updateAccount);
			} else {
				account.setRoleId(null);
				account.setRoleName(null);
				account.setDeptId(null);
				account.setDeptName(null);
				accountMapper.updateByPrimaryKey(account);
			}
		}
	}

	@Override
	public List<ShopAccountRoleResult> getRoleList(Integer accountId) {
		UmsShopAccountRoleExample example = new UmsShopAccountRoleExample();
		example.createCriteria().andAccountIdEqualTo(accountId);
		List<UmsShopAccountRole> list = accountRoleMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}
		List<ShopAccountRoleResult> resultList = new ArrayList<>();
		for(UmsShopAccountRole role : list){
			ShopAccountRoleResult result = new ShopAccountRoleResult();
			BeanUtil.copyProperties(role, result);

			OrgRole orgRole = roleMapper.selectByPrimaryKey(role.getRoleId());
			result.setRoleName(orgRole.getRoleName());
			result.setDeptName(orgRole.getDeptName());
			resultList.add(result);
		}
		return resultList;
	}

	@Override
	public List<UmsShopAccount> getAccountListByDept(Integer deptId) {
		UmsShopAccountRoleExample example = new UmsShopAccountRoleExample();
		example.createCriteria().andDeptIdEqualTo(deptId);
		List<UmsShopAccountRole> roleList = accountRoleMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(roleList)){
			return null;
		}
		UmsShopAccountExample accountExample = new UmsShopAccountExample();
		accountExample.createCriteria().andIdIn(roleList.stream().map(UmsShopAccountRole::getAccountId).collect(Collectors.toList()));
		List<UmsShopAccount> accountList = accountMapper.selectByExample(accountExample);
		if(CollectionUtil.isNotEmpty(accountList)){
			for(UmsShopAccount account : accountList){
				account.setPassword(null);
				account.setPayPassword(null);
			}
		}
		return accountList;
	}

}