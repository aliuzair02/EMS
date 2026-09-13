package org.template.managers;

import org.springframework.stereotype.Service;
import org.template.common.constants.MessageConstants;
import org.template.common.managers.BaseManager;
import org.template.common.services.ObjectService;
import org.template.models.VO.RoleVO;
import org.template.models.VO.UserProfileVO;
import org.template.services.RoleService;

@Service
public class RoleManager extends BaseManager {

    private final RoleService roleService;

    public RoleManager(RoleService roleService) {
        this.roleService = roleService;
    }

    public RoleVO getAllRoles(){

        generateLogId();

        infoLog("Process getAllRoles Started");

        RoleVO roleVO =  roleService.getAllRoles();

        ObjectService.setStatusVO(roleVO, true, MessageConstants.successMessage);

        infoLog("Process getAllRoles Ended");

        return roleVO;

    }

    public void saveRole(RoleVO roleVO){

        generateLogId();

        try{

            infoLog("Process saveRole Started");

            roleService.saveRole(roleVO);

            ObjectService.setStatusVO(roleVO, true, MessageConstants.successMessage);

            infoLog("Process saveRole Ended");

        } catch (Exception e) {
            errorLog(e.getMessage());
            roleVO.clear();
            ObjectService.setStatusVO(roleVO, false, ObjectService.getErrorMessage(e));
        }

    }

}
