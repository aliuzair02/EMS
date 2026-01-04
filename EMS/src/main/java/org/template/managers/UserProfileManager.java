package org.template.managers;

import org.springframework.stereotype.Service;
import org.template.common.constants.MessageConstants;
import org.template.common.managers.BaseManager;
import org.template.common.services.ObjectService;
import org.template.models.UserProfileVO;
import org.template.services.UserProfileService;

@Service
public class UserProfileManager extends BaseManager {

    private final UserProfileService userProfileService;

    public UserProfileManager(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    public void saveUserProfile(UserProfileVO userProfileVO){

        generateLogId();

        try{

            infoLog("Process saveUser Started");

            userProfileService.saveUserProfile(userProfileVO);

            ObjectService.setStatusVO(userProfileVO, true, MessageConstants.successMessage);

            infoLog("Process saveUser Ended");

        } catch (Exception e) {
            errorLog(e.getMessage());
            userProfileVO.clear();
            ObjectService.setStatusVO(userProfileVO, false, ObjectService.getErrorMessage(e));
        }

    }

    public UserProfileVO getAllUserProfiles(){

        infoLog("Process getAllUserProfiles Started");

        UserProfileVO userProfileVO =  userProfileService.getAllUserProfiles();

        ObjectService.setStatusVO(userProfileVO, true, MessageConstants.successMessage);

        infoLog("Process getAllUserProfiles Ended");

        return userProfileVO;

    }

    public void deleteUser(UserProfileVO userProfileVO){

        generateLogId();

        try{

            infoLog("Process deleteUser Started");

            userProfileService.deleteUser(userProfileVO);

            ObjectService.setStatusVO(userProfileVO, true, MessageConstants.successMessage);

            infoLog("Process deleteUser Ended");

        } catch (Exception e) {
            errorLog(e.getMessage());
            userProfileVO.clear();
            ObjectService.setStatusVO(userProfileVO, false, ObjectService.getErrorMessage(e));
        }

    }

    public void login(UserProfileVO userProfileVO){

        generateLogId();

        try {

            infoLog("Process login Started");

            userProfileService.login(userProfileVO);

            ObjectService.setStatusVO(userProfileVO, true, MessageConstants.successMessage);

            infoLog("Process login Ended");

        } catch (Exception e) {
            errorLog(e.getMessage());
            userProfileVO.clear();
            ObjectService.setStatusVO(userProfileVO, false, ObjectService.getErrorMessage(e));
        }

    }

    public void getUserDetails(UserProfileVO userProfileVO){

        generateLogId();

        try {

            infoLog("Process login Started");

            userProfileService.getUserDetails(userProfileVO);

            ObjectService.setStatusVO(userProfileVO, true, MessageConstants.successMessage);

            infoLog("Process login Ended");

        } catch (Exception e) {
            errorLog(e.getMessage());
            userProfileVO.clear();
            ObjectService.setStatusVO(userProfileVO, false, ObjectService.getErrorMessage(e));
        }

    }

}
