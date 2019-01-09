package com.xsl.shiro.common;

import java.util.List;

/**
 * @Description 分页类
 * @Author 谢山林
 * @Date 2019-01-09
 **/
public class Paging<T> {

    private Integer pageNo;     // 当前页
    private Integer pageSize;   // 每页条数

    private List<T> rows;       // 集合中的实体
    private Long total;         // 总共有多少条
    private Long pages;         // 共有几页

    public Paging() {
    }

    public Paging(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getPages() {
        long num = total % pageSize;
        return num == 0 ? total / pageSize : total / pageSize + 1L;
    }

    public Integer getBeginIndex() {
        return (pageNo - 1) * pageSize;
    }


}
