package com.xsl.shiro.constant;

public enum Code {

    /** 未登录 */
    UNAUTHEN(4401),

    /** 未授权，拒绝访问 */
    UNAUTHZ(4403),

    /** shiro相关的错误 */
    SHIRO_ERR(4444),

    /** 服务端异常 */
    SERVER_ERR(5500);

    Code(Integer code) {
        this.code = code;
    }

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
