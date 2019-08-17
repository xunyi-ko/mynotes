package com.jscwf.boss.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import com.jscwf.boss.weixin.WeixinToke;

public class MyRetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

	@Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    	
    	if(token instanceof WeixinToke){ // 免密码登录
            return true;
        }
    	
    	return super.doCredentialsMatch(token, info);
    }


}
