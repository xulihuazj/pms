package com.xulihuazj.pms.response;

import com.xulihuazj.pms.enums.ResponseStatusCodeEnum;
import lombok.Getter;
import lombok.Setter;

public class APIResponse<T> extends AbstractResponse {

    @Getter
    @Setter
    private T bizResponse;

    public static <T> APIResponse<T> instance(T response) {
        APIResponse<T> apiResponse = new APIResponse<T>();
        apiResponse.setBizResponse(response);
        apiResponse.setStatusCode(ResponseStatusCodeEnum.SUCCESS.getDesc());
        apiResponse.setMessage(ResponseStatusCodeEnum.SUCCESS.getDesc());
        return apiResponse;
    }

}
