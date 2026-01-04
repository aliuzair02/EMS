package org.template.models;

import org.template.common.models.BaseVO;

import java.util.List;

public class UserProfileVO extends BaseVO {
    private UserProfileDO userProfileDO;
    private List<UserProfileDO> userProfileDOList;

    public UserProfileVO() {}

    public UserProfileVO(UserProfileDO userProfileDO) {
        this.userProfileDO = userProfileDO;
    }

    public UserProfileVO(List<UserProfileDO> userProfileDOList) {
        this.userProfileDOList = userProfileDOList;
    }

    public List<UserProfileDO> getUserProfileDOList() {
        return userProfileDOList;
    }

    public void setUserProfileDOList(List<UserProfileDO> userProfileDOList) {
        this.userProfileDOList = userProfileDOList;
    }

    public UserProfileDO getUserProfileDO() {
        return userProfileDO;
    }

    public void setUserProfileDO(UserProfileDO userProfileDO) {
        this.userProfileDO = userProfileDO;
    }

    public void clear(){
        userProfileDO = null;
        userProfileDOList = null;
    }
}
