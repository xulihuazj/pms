package com.xulihuazj.pms.service.user;

import com.xulihuazj.pms.request.APIRequest;
import com.xulihuazj.pms.request.user.UserManageRequest;
import com.xulihuazj.pms.response.APIResponse;
import com.xulihuazj.pms.response.user.UserManageResponse;

public interface UserService {

    /**
     *  用户增加
     * @param request
     * @return
     */
    APIResponse<UserManageResponse> manage(APIRequest<UserManageRequest> request);


}
