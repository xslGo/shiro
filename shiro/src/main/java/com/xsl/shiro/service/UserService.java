package com.xsl.shiro.service;

import com.xsl.shiro.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class UserService {

    /**
     * 模拟查询返回用户信息
     * @param name
     * @return
     */
    public User findUserByName(String name){
        User user = new User();
        user.setName(name);
        user.setNick(name+"NICK");
        user.setPwd("J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho=");//密码明文是123456
        user.setSalt("wxKYXuTPST5SG0jMQzVPsg==");//加密密码的盐值
        user.setUid(new Random().nextLong());//随机分配一个id
        user.setCreated(new Date());
        return user;
    }
}
