package org.template.models.RO;

import org.template.common.models.RequestObject;
import org.template.models.VO.RoleVO;
import org.template.models.VO.UserProfileVO;
import org.template.models.VO.UserRoleVO;

public class MasterRequestObject extends RequestObject {

    private UserProfileVO userProfileVO;
    private RoleVO roleVO;
    private UserRoleVO userRoleVO;

    public UserProfileVO getUserProfileVO() {
        return userProfileVO;
    }

    public void setUserProfileVO(UserProfileVO userProfileVO) {
        this.userProfileVO = userProfileVO;
    }

    public RoleVO getRoleVO() {
        return roleVO;
    }

    public void setRoleVO(RoleVO roleVO) {
        this.roleVO = roleVO;
    }

    public UserRoleVO getUserRoleVO() {
        return userRoleVO;
    }

    public void setUserRoleVO(UserRoleVO userRoleVO) {
        this.userRoleVO = userRoleVO;
    }
}
