package com.xulihuazj.pms.entity.exception;

import com.xulihuazj.pms.entity.error.ErrorCode;
import lombok.Getter;
import lombok.Setter;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 3409302683535023138L;

    @Getter
    @Setter
    private String exErrorCode = ResConstant.STATUS_ERROR_CODE.toString();

    @Getter
    @Setter
    private Integer httpStatus;

    @Getter
    @Setter
    private ErrorCode errorCode;

    public BusinessException(Integer errorCode, String message) {
        super(message);
        if (null != errorCode) {
            this.exErrorCode = errorCode.toString();
        }
    }

    public BusinessException(Integer httpStatus, Integer errorCode, String message) {
        super(message);
        this.httpStatus = httpStatus;
        if (errorCode != null) {
            this.exErrorCode = errorCode.toString();
        }
    }

    public BusinessException(Integer httpStatus, ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = httpStatus;
        this.exErrorCode = errorCode.getCode();
    }


    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.exErrorCode = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.exErrorCode = errorCode.getCode();
    }

}
