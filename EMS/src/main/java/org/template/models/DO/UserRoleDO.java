package org.template.models.DO;

import org.template.common.models.BaseDO;

public class UserRoleDO extends BaseDO {

    private Long UserRoleId;
    private UserProfileDO userProfileDO;
    private RoleDO roleDO;

    public Long getUserRoleId() {
        return UserRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        UserRoleId = userRoleId;
    }

    public UserProfileDO getUserProfileDO() {
        return userProfileDO;
    }

    public void setUserProfileDO(UserProfileDO userProfileDO) {
        this.userProfileDO = userProfileDO;
    }

    public RoleDO getRoleDO() {
        return roleDO;
    }

    public void setRoleDO(RoleDO roleDO) {
        this.roleDO = roleDO;
    }


}
