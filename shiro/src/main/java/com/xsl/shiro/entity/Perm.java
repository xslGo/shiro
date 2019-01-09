package com.xsl.shiro.entity;

import java.io.Serializable;
import java.util.Date;

public class Perm implements Serializable {
    private static final long serialVersionUID = 8699943335112333017L;

    /**
     * 权限类型：菜单
     */
    public static int TYPE_MENU = 1;
    /**
     * 权限类型：按钮
     */
    public static int TYPE_BUTTON = 2;

    private Long pid;       // 权限id
    private String name;    // 权限名称
    private Integer type;   // 权限类型：1.菜单；2.按钮
    private String val;     // 权限值，shiro的权限控制表达式
    private Date created;   // 创建时间
    private Date updated;   // 修改时间


    public Perm() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static int getTypeMenu() {
        return TYPE_MENU;
    }

    public static void setTypeMenu(int typeMenu) {
        TYPE_MENU = typeMenu;
    }

    public static int getTypeButton() {
        return TYPE_BUTTON;
    }

    public static void setTypeButton(int typeButton) {
        TYPE_BUTTON = typeButton;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        return "Perm{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", val='" + val + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
