package com.xulihuazj.pms.request;

import lombok.Getter;
import lombok.Setter;

public class APIRequest<T> extends AbstractRequest {

    @Getter
    @Setter
    private T bizRequest;

    public T getRequest(Class<T> clazz) {
        if (null != this.bizRequest && this.bizRequest.getClass().equals(clazz)) {
            return this.bizRequest;
        } else {
            return null;
        }
    }

    public static <T> APIRequest<T> instance(AbstractRequest abstractRequest, T bizRequest) {
        if (null == abstractRequest && null == bizRequest) {
            return null;
        } else {
            APIRequest<T> apiRequest = new APIRequest<>();
            apiRequest.setDeviceId(abstractRequest.getDeviceId());
            apiRequest.setSource(abstractRequest.getSource());
            apiRequest.setDtMonitor(abstractRequest.getDtMonitor());
            apiRequest.setVersion(abstractRequest.getVersion());
            apiRequest.setBizRequest(bizRequest);
            return apiRequest;
        }
    }
    
}
