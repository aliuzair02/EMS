package org.template.managers;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.template.common.constants.MessageConstants;
import org.template.common.managers.BaseManager;
import org.template.common.services.ObjectService;
import org.template.models.DO.UserRoleDO;
import org.template.models.VO.RoleVO;
import org.template.models.VO.UserProfileVO;
import org.template.models.VO.UserRoleVO;
import org.template.services.UserRoleService;
import org.template.services.UserProfileService;

import java.util.Objects;

@Service
public class RoleManager extends BaseManager {

    private final UserRoleService userRoleService;
    private final UserProfileService userProfileService;

    public RoleManager(UserRoleService userRoleService, UserProfileService userProfileService) {
        this.userRoleService = userRoleService;
        this.userProfileService = userProfileService;
    }

    public RoleVO getAllRoles() {

        generateLogId();

        infoLog("Process getAllRoles Started");

        RoleVO roleVO =  userRoleService.getAllRoles();

        ObjectService.setStatusVO(roleVO, true, MessageConstants.successMessage);

        infoLog("Process getAllRoles Ended");

        return roleVO;

    }

    public void saveRole(RoleVO roleVO) {

        generateLogId();

        try{

            infoLog("Process saveRole Started");

            userRoleService.saveRole(roleVO);

            ObjectService.setStatusVO(roleVO, true, MessageConstants.successMessage);

            infoLog("Process saveRole Ended");

        } catch (Exception e) {
            errorLog(e.getMessage());
            roleVO.clear();
            ObjectService.setStatusVO(roleVO, false, ObjectService.getErrorMessage(e));
        }

    }

    public void assignRoles(UserRoleVO userRoleVO) throws Exception {

        if (Objects.isNull(userRoleVO)) {
            // TODO: build service to custom error
            throw new Exception("UserRoleVO is null/empty");
        }

        try {

            infoLog("Process assignRoles Started");

            if (Objects.nonNull(userRoleVO.getUserRoleDO())) {

                assignRole(userRoleVO.getUserRoleDO());

            }

            if (!CollectionUtils.isEmpty(userRoleVO.getUserRoleDOList())) {

            }

            ObjectService.setStatusVO(userRoleVO, true, MessageConstants.successMessage);

            infoLog("Process assignRoles Ended");

        } catch (Exception e) {
            errorLog(e.getMessage());
            userRoleVO.clear();
            ObjectService.setStatusVO(userRoleVO, false, ObjectService.getErrorMessage(e));
        }

    }

    private void assignRole(UserRoleDO userRoleDO) throws Exception {

        if (Objects.isNull(userRoleDO.getUserProfileDO())) {
            throw new Exception("UserProfileDO is null/empty");
        }

        UserProfileVO userProfileVO = new UserProfileVO(userRoleDO.getUserProfileDO());

        userProfileService.getUserDetails(userProfileVO);

        if (Objects.isNull(userProfileVO.getUserProfileDO())) {
            throw new Exception("UserProfileDO is null/empty");
        }

        userRoleDO.setUserProfileDO(userProfileVO.getUserProfileDO());

        if (Objects.isNull(userRoleDO.getRoleDO())) {
            throw new Exception("RoleDO is null/empty");
        }

        RoleVO roleVO = new RoleVO(userRoleDO.getRoleDO());

        userRoleService.getRoleDetails(roleVO);

        if (Objects.isNull(roleVO.getRoleDO())) {
            throw new Exception("RoleDO is null/empty");
        }

        userRoleDO.setRoleDO(roleVO.getRoleDO());

        userRoleService.saveUserRole(new UserRoleVO(userRoleDO));

    }

}
