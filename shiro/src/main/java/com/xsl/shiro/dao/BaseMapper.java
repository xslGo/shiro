package com.xsl.shiro.dao;

public interface BaseMapper<T> {

    void add(T t);

    void selectById(Integer id);

    void updateById(T t);

    void deleteById(Integer id);



}
