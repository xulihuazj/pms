package com.xulihuazj.pms.user;

import com.xulihuazj.pms.entity.BOBaseEntity;
import com.xulihuazj.pms.enums.user.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户 业务层 BO
 */
public class UserBO extends BOBaseEntity {

    // 用户ID
    @Getter
    @Setter
    private Long id;

    // 用户名
    @Getter
    @Setter
    private String userName;

    // 性别
    @Getter
    @Setter
    private String userSex;

    // 昵称
    @Getter
    @Setter
    private String nickName;

    // 用户图标
    @Getter
    @Setter
    private String userIcon;

    // 用户电话
    @Getter
    @Setter
    private String userPhone;

    // 部门ID
    @Getter
    @Setter
    private Integer departmentId;

    // 岗位ID
    @Getter
    @Setter
    private Integer positionId;

    // 用户状态EFFECTIVE：有效 INVALID：无效
    @Getter
    @Setter
    private UserStatusEnum userStatus;

    // 用户类型
    @Getter
    @Setter
    private String userType;

    // 当前登录错误次数
    @Getter
    @Setter
    private Integer currentLoginFailCount;

    // 总登录错误次数
    @Getter
    @Setter
    private Integer loginFailCount;

    public static class Builder {
        // 必要参数
        private String userName;
        private String userSex;
        private String userPhone;
        private String userType;
        private UserStatusEnum userStatus;

        // 可选参数
        private String nickName;
        private String userIcon;
        private Integer departmentId;
        private Integer positionId;
        private Integer currentLoginFailCount;
        private Integer loginFailCount;

        public Builder(String userName, String userSex, String userPhone, String userType, UserStatusEnum userStatus) {
            this.userName = userName;
            this.userSex = userSex;
            this.userPhone = userPhone;
            this.userType = userType;
            this.userStatus = userStatus;
        }

        public Builder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder userIcon(String userIcon) {
            this.userIcon = userIcon;
            return this;
        }

        public Builder departmentId(Integer departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public Builder positionId(Integer positionId) {
            this.positionId = positionId;
            return this;
        }

        public Builder currentLoginFailCount(Integer currentLoginFailCount) {
            this.currentLoginFailCount = currentLoginFailCount;
            return this;
        }

        public Builder loginFailCount(Integer loginFailCount) {
            this.loginFailCount = loginFailCount;
            return this;
        }
    }

    private UserBO(Builder builder) {
        this.userName = builder.userName;
        this.userSex = builder.userSex;
        this.userPhone = builder.userPhone;
        this.userType = builder.userType;
        this.userStatus = builder.userStatus;
    }


}
