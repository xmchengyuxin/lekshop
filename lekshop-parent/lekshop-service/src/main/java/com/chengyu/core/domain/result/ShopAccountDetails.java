package com.chengyu.core.domain.result;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.model.UmsShopAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * @title  会员详情封装
 * @author LeGreen
 * @date   2021/1/7
 */
public class ShopAccountDetails implements UserDetails {
    private static final long serialVersionUID = 819224196656403974L;

    private UmsShopAccount shopAccount;

    public ShopAccountDetails(UmsShopAccount shopAccount) {
        this.shopAccount = shopAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return shopAccount.getPassword();
    }

    @Override
    public String getUsername() {
        return shopAccount.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return CommonConstant.YES_INT == shopAccount.getStatus();
    }

    public UmsShopAccount getShopAccount() {
        return shopAccount;
    }
}
