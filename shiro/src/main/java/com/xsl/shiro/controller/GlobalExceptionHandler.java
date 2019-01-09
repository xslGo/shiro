package com.xsl.shiro.controller;

import com.xsl.shiro.constant.Code;
import com.xsl.shiro.vo.Json;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 统一捕捉shiro的异常，返回给前台一个json信息，
 * 前台根据这个信息显示对应的提示，或者做页面的跳转。
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //不满足@RequiresGuest注解时抛出的异常信息
    private static final String GUEST_ONLY = "Attempting to perform a guest-only operation";

    @ExceptionHandler(ShiroException.class)
    public Json handleShiroException(ShiroException e) {
        String eName = e.getClass().getSimpleName();
        log.error("shiro执行出错：{}",eName);
        return new Json(eName, false, Code.SHIRO_ERR.getCode(), "鉴权或授权过程出错", null);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public Json page401(UnauthenticatedException e) {
        String eMsg = e.getMessage();
        if (StringUtils.startsWithIgnoreCase(eMsg,GUEST_ONLY)){
            return new Json("401", false, Code.UNAUTHEN.getCode(), "只允许游客访问，若您已登录，请先退出登录", null)
                    .data("detail",e.getMessage());
        }else{
            return new Json("401", false, Code.UNAUTHEN.getCode(), "用户未登录", null)
                    .data("detail",e.getMessage());
        }
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Json page403() {
        return new Json("403", false, Code.UNAUTHZ.getCode(), "用户没有访问权限", null);
    }
}
