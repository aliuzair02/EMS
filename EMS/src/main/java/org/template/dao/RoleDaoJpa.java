package org.template.dao;

import org.springframework.stereotype.Repository;
import org.template.common.services.ObjectService;
import org.template.models.DO.RoleDO;
import org.template.models.VO.RoleVO;
import org.template.tables.TbRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RoleDaoJpa extends DaoJpa{

    public RoleVO getAllRoles(){

        RoleVO roleVO = new RoleVO();

        List<RoleDO> roleDOList = new ArrayList<>();
        List<TbRole> tbRoleList = this.getAll(TbRole.class);

        for (TbRole tbRole : tbRoleList) {
            RoleDO roleDO = new RoleDO();
            ObjectService.copyProperties(tbRole, roleDO);

            roleDOList.add(roleDO);

            if (Objects.isNull(roleVO.getRoleDO())) {
                roleVO.setRoleDO(roleDO);
            }
        }

        roleVO.setRoleDOList(roleDOList);

        return roleVO;

    }

}
