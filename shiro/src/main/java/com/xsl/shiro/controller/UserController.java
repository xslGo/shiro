package com.xsl.shiro.controller;


import com.xsl.shiro.entity.User;
import com.xsl.shiro.vo.Json;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 登录接口，由于UserService中是模拟返回用户信息的，
     * 所以用户名随意，密码123456
     */
    @PostMapping("/login")
    public Json login(String name, String pwd) {

        String oper = "user login";
        if (StringUtils.isEmpty(name)) {
            return Json.fail(oper, "用户名不能为空");
        }
        if (StringUtils.isEmpty(pwd)) {
            return Json.fail(oper, "密码不能为空");
        }
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //登录
            currentUser.login(new UsernamePasswordToken(name, pwd));
            //从session取出用户信息
            User user = (User) currentUser.getPrincipal();
            if (user == null) throw new AuthenticationException();
            //返回登录用户的信息给前台，含用户的所有角色和权限
            return Json.succ(oper)
                    .data("uid", user.getUid())
                    .data("nick", user.getNick());
//                    .data("roles", user.getRoles())
//                    .data("perms", user.getPerms());

        } catch (UnknownAccountException uae) {
            log.warn("用户帐号不正确");
            return Json.fail(oper, "用户帐号或密码不正确");

        } catch (IncorrectCredentialsException ice) {
            log.warn("用户密码不正确");
            return Json.fail(oper, "用户帐号或密码不正确");

        } catch (LockedAccountException lae) {
            log.warn("用户帐号被锁定");
            return Json.fail(oper, "用户帐号被锁定不可用");

        } catch (AuthenticationException ae) {
            log.warn("登录出错");
            return Json.fail(oper, "登录失败：" + ae.getMessage());
        }
    }

    @GetMapping("/logout")
    public Json logout(){
        String oper = "user logout";
        SecurityUtils.getSubject().logout();
        return Json.succ(oper);
    }
}
