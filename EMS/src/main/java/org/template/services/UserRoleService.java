package org.template.services;

import org.springframework.stereotype.Service;
import org.template.common.services.BaseService;
import org.template.common.services.ObjectService;
import org.template.dao.RoleDaoJpa;
import org.template.dao.UserProfileDaoJpa;
import org.template.models.DO.RoleDO;
import org.template.models.DO.UserProfileDO;
import org.template.models.DO.UserRoleDO;
import org.template.models.VO.RoleVO;
import org.template.models.VO.UserRoleVO;
import org.template.services.validator.RoleValidator;
import org.template.tables.TbRole;
import org.template.tables.TbUser;
import org.template.tables.TbUserRole;

import java.util.Objects;

@Service
public class UserRoleService extends BaseService {

    private final RoleDaoJpa roleDaoJpa;
    private final UserProfileDaoJpa userProfileDaoJpa;

    public UserRoleService(RoleDaoJpa roleDaoJpa, UserProfileDaoJpa userProfileDaoJpa) {
        this.roleDaoJpa = roleDaoJpa;
        this.userProfileDaoJpa = userProfileDaoJpa;
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

        roleDaoJpa.saveOrUpdate(tbRole);

    }

    public void getRoleDetails(RoleVO roleVO) throws Exception {

        RoleValidator.validateVODO(roleVO);

        TbRole tbRole = roleDaoJpa.getById(TbRole.class, roleVO.getRoleDO().getRoleId());

        if (Objects.isNull(tbRole)) {
            throw new Exception("Role data missing");
        }

        RoleDO roleDO = new RoleDO();
        roleDO.setRoleId(tbRole.getPkRoleId());

        ObjectService.copyProperties(tbRole, roleDO);

        roleVO.setRoleDO(roleDO);

    }

    public void saveUserRole(UserRoleVO userRoleVO) throws Exception {

//        RoleValidator.validateVODO(userRoleVO);

        UserRoleDO userRoleDO = userRoleVO.getUserRoleDO();

        // TODO: validate this 2
        RoleDO roleDO = userRoleDO.getRoleDO();
        UserProfileDO userProfileDO = userRoleDO.getUserProfileDO();

        TbRole tbRole = roleDaoJpa.getById(TbRole.class, roleDO.getRoleId());
        TbUser tbUser = userProfileDaoJpa.getById(TbUser.class, userProfileDO.getUserId());

        if (Objects.isNull(tbRole) || Objects.isNull(tbUser)) {
            throw new Exception("tbRole or tbUser not found");
        }

        TbUserRole tbUserRole;

        if (Objects.isNull(userRoleDO.getUserRoleId())) {
            tbUserRole = new TbUserRole();
        } else {
            tbUserRole = roleDaoJpa.getById(TbUserRole.class, userRoleDO.getUserRoleId());
            if (Objects.isNull(tbUserRole)) {
                throw new Exception("UserRole not found");
            }
        }

        tbUserRole.setFkRoleId(tbRole);
        tbUserRole.setFkUserId(tbUser);

        roleDaoJpa.saveOrUpdate(tbUserRole);

    }
}
