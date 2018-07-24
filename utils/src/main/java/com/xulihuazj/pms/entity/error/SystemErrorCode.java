package com.xulihuazj.pms.entity.error;


/**
 * 系统异常码定义  系统码以500开头
 */
public enum SystemErrorCode implements ErrorCode {

    /**
     * 1.api默认系统码100 1.1 jws相关业务码位10
     */
    JWS_SIGN_ERROR("50010001", "TOKEN验证异常"),

    SYSTEM_ERROR("50010002", "系统异常"),

    TOKEN_NULL("50010009", "请求头中未带入token或token为空"),

    URL_NOT_EFFECTIVE("90010015", "当前请求地址不存在或者未生效"),

    TOKEN_EXPIRED("50010016", "token过期"),;

    SystemErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}