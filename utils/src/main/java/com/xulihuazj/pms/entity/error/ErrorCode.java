package com.xulihuazj.pms.entity.error;

/**
 * 异常码定义
 */
public interface ErrorCode {

    /**
     * 获取异常code
     *
     * @return
     */
    String getCode();

    /**
     * 获取异常描述信息
     *
     * @return
     */
    String getMessage();
}
