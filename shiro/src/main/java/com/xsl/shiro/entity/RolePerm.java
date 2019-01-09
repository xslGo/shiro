package com.xsl.shiro.entity;

import java.io.Serializable;

public class RolePerm implements Serializable {
    private static final long serialVersionUID = -8642046465693616513L;

    private Long roleId;
    private Long permId;

    public RolePerm() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermId() {
        return permId;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
    }


}
