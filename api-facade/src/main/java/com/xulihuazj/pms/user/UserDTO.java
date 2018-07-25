package com.xulihuazj.pms.user;

import com.xulihuazj.pms.entity.ToString;
import com.xulihuazj.pms.enums.user.CertTypeEnum;
import com.xulihuazj.pms.validate.Remark;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息DTO
 */
public class UserDTO extends ToString {

    private static final long serialVersionUID = -2308555435962125602L;

    @Getter
    @Setter
    @Remark("用户名称")
    private String userName;

    @Getter
    @Setter
    @Remark("用户昵称")
    private String nickName;

    @Getter
    @Setter
    @Remark("用户性别")
    private String userSex;

    @Getter
    @Setter
    @Remark("用户Id")
    private String userId;

    @Getter
    @Setter
    @Remark("用户头像地址")
    private String userImageHref;

    @Getter
    @Setter
    @Remark("用户手机号")
    private String userPhone;

    @Getter
    @Setter
    @Remark("证件类型")
    private CertTypeEnum certType;

    @Getter
    @Setter
    @Remark("证件号")
    private String certNum;
}
