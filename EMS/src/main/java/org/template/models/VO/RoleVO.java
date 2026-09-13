package org.template.models.VO;

import org.template.common.models.BaseVO;
import org.template.models.DO.RoleDO;

import java.util.List;

public class RoleVO extends BaseVO {

    private RoleDO roleDO;
    private List<RoleDO> roleDOList;

    public RoleVO(){}

    public RoleVO(RoleDO roleDO){
        this.roleDO = roleDO;
    }

    public RoleDO getRoleDO() {
        return roleDO;
    }

    public void setRoleDO(RoleDO roleDO) {
        this.roleDO = roleDO;
    }

    public List<RoleDO> getRoleDOList() {
        return roleDOList;
    }

    public void setRoleDOList(List<RoleDO> roleDOList) {
        this.roleDOList = roleDOList;
    }

    public void clear(){
        this.roleDO = null;
        this.roleDOList = null;
    }
}
