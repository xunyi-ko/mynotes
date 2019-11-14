package com.jscwf.boss.shiro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.jscwf.boss.dao.SysPermissionDao;
import com.jscwf.boss.entity.SysPermissionEntity;
import com.jscwf.boss.entity.SysRoleEntity;
import com.jscwf.boss.entity.SysUserEntity;
import com.jscwf.boss.sevice.UserInfoService;
import com.jscwf.boss.util.Constants;
import com.jscwf.boss.weixin.WeixinToke;


/**
 * realm实现类,用于实现具体的验证和授权方法
 * @author Bean
 *
 */
public class MyShiroRealm extends AuthorizingRealm {
	
	@Resource  
	private UserInfoService userInfoService;
 
	@Resource  
	private SysPermissionDao sysPermissionDao;
	
	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUserEntity userInfo  = (SysUserEntity)principals.getPrimaryPrincipal();
		Set<String> permsSet = new HashSet<>();
        
		//系统管理员，拥有最高权限
		if(userInfo.getUsername().equals(Constants.SUPER_ADMIN)){
			authorizationInfo.addRole(Constants.SUPER_ADMIN);
			List<SysPermissionEntity> permissionList = sysPermissionDao.findAll();
			for(SysPermissionEntity p:permissionList){
            	if(StringUtils.isBlank(p.getPerms())){
    				continue;
    			}
            	permsSet.addAll(Arrays.asList(p.getPerms().trim().split(",")));
            }
			
		}else {
			//用户权限列表
	        for(SysRoleEntity role:userInfo.getRoleList()){
	            authorizationInfo.addRole(role.getRolename());
	            for(SysPermissionEntity p:role.getPermissions()){
	            	if(StringUtils.isBlank(p.getPerms())){
	    				continue;
	    			}
	            	permsSet.addAll(Arrays.asList(p.getPerms().trim().split(",")));
	            }
	        }
		}
        authorizationInfo.setStringPermissions(permsSet);
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("Shiro登录认证启动");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUserEntity userInfo = userInfoService.findByUsername(username);
        
        //若为微信用户token,直接登录
        if(token instanceof WeixinToke){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                    userInfo, //用户名
                    userInfo.getPassword(), //密码
                    ByteSource.Util.bytes(userInfo.getSalt()),
                    getName()  //realm name
            );
            return info;
        }
        
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
        	throw new UnknownAccountException("账号或密码不正确");
        }
        
		//账号锁定
		if(userInfo.getStatus() == 2){
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
        
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getSalt()),
                getName()  //realm name
        );
        return authenticationInfo;
    }
    
    @Override
    public boolean supports(AuthenticationToken token) {

        return token instanceof UsernamePasswordToken || token instanceof WeixinToke;
    }
    
    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        return principals.getPrimaryPrincipal();
    }

}

