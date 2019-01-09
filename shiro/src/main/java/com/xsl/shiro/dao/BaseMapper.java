package com.xsl.shiro.dao;

import java.util.List;

public interface BaseMapper<T> {

    void add(T t);

    void selectById(Integer id);

    void updateById(T t);

    void deleteById(Integer id);

    List<T> listPage(Integer pageNo, Integer pageSize);

    Long count();

}
