package com.xsl.shiro.entity;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private static final long serialVersionUID = -8650877029027357127L;

    private Long rid;       // 角色id
    private String name;    // 角色名，用于显示
    private String desc;    // 角色描述
    private String val;     // 角色值，用于权限判断
    private Date created;   // 创建时间
    private Date updated;   // 修改时间


    public Role() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", val='" + val + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
