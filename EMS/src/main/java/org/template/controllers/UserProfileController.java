package org.template.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.template.common.controllers.BaseController;
import org.template.common.models.ResponseObject;
import org.template.common.services.ObjectService;
import org.template.managers.UserProfileManager;
import org.template.models.UserProfileDO;
import org.template.models.UserProfileRequestObject;
import org.template.models.UserProfileVO;

@RestController
@RequestMapping("/user")
public class UserProfileController extends BaseController {

    private final UserProfileManager userProfileManager;

    public UserProfileController(UserProfileManager userProfileManager) {
        this.userProfileManager = userProfileManager;
    }

    // Basic Create & Update
    @PostMapping("/save")
    public ResponseEntity<ResponseObject> save(@RequestBody UserProfileRequestObject userProfileRequestObject) {

        UserProfileDO userProfileDO = new UserProfileDO();

        ObjectService.copyProperties(userProfileRequestObject, userProfileDO);

        UserProfileVO userProfileVO = new UserProfileVO(userProfileDO);

        userProfileManager.saveUserProfile(userProfileVO);

        return ObjectService.getResponseBody(userProfileVO);
    }

    // Basic Read
    @GetMapping("/getAll")
    public ResponseEntity<ResponseObject> getAll() {

        return ObjectService.getResponseBody(userProfileManager.getAllUserProfiles());
    }

    // Basic Delete
    @PostMapping("/delete")
    public ResponseEntity<ResponseObject> delete(@RequestBody UserProfileRequestObject userProfileRequestObject) {

        UserProfileDO userProfileDO = new UserProfileDO();

        ObjectService.copyProperties(userProfileRequestObject, userProfileDO);

        UserProfileVO userProfileVO = new UserProfileVO(userProfileDO);

        userProfileManager.deleteUser(userProfileVO);

        return ObjectService.getResponseBody(userProfileVO);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody UserProfileRequestObject userProfileRequestObject){

        UserProfileDO userProfileDO = new UserProfileDO();

        ObjectService.copyProperties(userProfileRequestObject, userProfileDO);

        UserProfileVO userProfileVO = new UserProfileVO(userProfileDO);

        userProfileManager.login(userProfileVO);

        return ObjectService.getResponseBody(userProfileVO);

    }

    @GetMapping("/getUserDetails/{userId}")
    public ResponseEntity<ResponseObject> getUserDetails(
            @PathVariable Long userId) {

        UserProfileDO userProfileDO = new UserProfileDO();
        userProfileDO.setUserId(userId);

        UserProfileVO userProfileVO = new UserProfileVO(userProfileDO);

        userProfileManager.getUserDetails(userProfileVO);

        return ObjectService.getResponseBody(userProfileVO);

    }

}
