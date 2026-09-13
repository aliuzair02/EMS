package org.template.models.VO;

import org.template.common.models.BaseVO;
import org.template.models.DO.UserRoleDO;

import java.util.List;

public class UserRoleVO extends BaseVO {

    private UserRoleDO userRoleDO;
    private List<UserRoleDO> userRoleDOList;

    public UserRoleVO() {

    }

    public UserRoleVO(UserRoleDO userRoleDO) {
        this.userRoleDO = userRoleDO;
    }

    public UserRoleDO getUserRoleDO() {
        return userRoleDO;
    }

    public void setUserRoleDO(UserRoleDO userRoleDO) {
        this.userRoleDO = userRoleDO;
    }

    public List<UserRoleDO> getUserRoleDOList() {
        return userRoleDOList;
    }

    public void setUserRoleDOList(List<UserRoleDO> userRoleDOList) {
        this.userRoleDOList = userRoleDOList;
    }

    public void clear(){
        this.userRoleDO = null;
        this.userRoleDOList = null;
    }
}
