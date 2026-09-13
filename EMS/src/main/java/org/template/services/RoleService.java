package org.template.services;

import org.springframework.stereotype.Service;
import org.template.common.services.BaseService;
import org.template.dao.RoleDaoJpa;
import org.template.models.DO.RoleDO;
import org.template.models.VO.RoleVO;
import org.template.services.validator.RoleValidator;
import org.template.tables.TbRole;

import java.util.Objects;

@Service
public class RoleService extends BaseService {

    private final RoleDaoJpa roleDaoJpa;

    public RoleService(RoleDaoJpa roleDaoJpa) {
        this.roleDaoJpa = roleDaoJpa;
    }

    public RoleVO getAllRoles(){

        return roleDaoJpa.getAllRoles();

    }

    public void saveRole(RoleVO roleVO) throws Exception {

        RoleValidator.validateVODO(roleVO);

        RoleDO roleDO = roleVO.getRoleDO();

        TbRole tbRole;

        if (Objects.isNull(roleDO.getRoleId())) {
            tbRole = new TbRole();
        } else {
            tbRole = roleDaoJpa.getById(TbRole.class, roleDO.getRoleId());
            if (Objects.isNull(tbRole)) {
                throw new Exception("Role not found");
            }
        }

        tbRole.setRoleCode(roleDO.getRoleCode());
        tbRole.setRoleName(roleDO.getRoleName());
        tbRole.setRoleDesc(roleDO.getRoleDesc());

        tbRole = roleDaoJpa.saveOrUpdate(tbRole);

    }

}
