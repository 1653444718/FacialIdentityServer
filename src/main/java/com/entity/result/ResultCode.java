package com.entity.result;

public enum ResultCode {

    SUCCESS(200, "成功！"),
    ERROR( 201, "系统错误！"),
    LOGIN_ERROR(202, "未登录！"),
    PARAMS_ERROR(203,"参数错误！");

    private Integer code;
    private String message;

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
