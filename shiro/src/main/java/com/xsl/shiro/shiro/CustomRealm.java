package com.xsl.shiro.shiro;

import com.xsl.shiro.entity.User;
import com.xsl.shiro.service.PermService;
import com.xsl.shiro.service.RoleService;
import com.xsl.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 自定义realm,实现认证，授权，密码校验
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermService permService;


    // 告诉shiro如何根据获取到的用户中的密码和盐值来校验密码
    {
        // 设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher pwdMatcher = new HashedCredentialsMatcher();
        pwdMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        pwdMatcher.setStoredCredentialsHexEncoded(false);
        pwdMatcher.setHashIterations(1024);
        this.setCredentialsMatcher(pwdMatcher);
    }

    /**
     * 定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if(principalCollection == null){
            throw new AuthorizationException("PrincipalCollection method argument cannot be null");
        }
        User user = (User) getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

//        info.setRoles(user.getRoles());
//        info.setStringPermissions(user.getPerms());

        return info;
    }

    /**
     * 定义如何获取用户信息的业务逻辑，给shiro做登录
     * 该方法自动运行，不需要调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        token.setRememberMe(true);
        String userName = token.getUsername();
        if(userName == null){
            throw new AccountException("Null username are not allowed by this realm");
        }
        User user = userService.findUserByName(userName);
        if(user == null){
            throw new UnknownAccountException("No account found for admin["+ userName +"]");
        }

        /**
         * 查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
         * SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
         */
//        Set<String> roles = roleService.getRolesByUserId(user.getUid());
//        Set<String> perms = permService.getPermsByUserId(user.getUid());
//
//        user.setRoles(roles);
//        user.setPerms(perms);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPwd(), getName());
        if(user.getSalt() != null){
            info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        }
        return info;
    }
}
