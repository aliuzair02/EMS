package org.template.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.template.common.services.BaseService;
import org.template.common.services.ObjectService;
import org.template.dao.UserProfileDaoJpa;
import org.template.models.UserProfileDO;
import org.template.models.UserProfileVO;
import org.template.tables.TbProfile;
import org.template.tables.TbUser;

import java.util.Objects;

@Service
public class UserProfileService extends BaseService {

    private final UserProfileDaoJpa userProfileDaoJpa;
    private final PasswordEncoder passwordEncoder;

    public UserProfileService(UserProfileDaoJpa userProfileDaoJpa, PasswordEncoder passwordEncoder, UserProfileValidator userProfileValidator){
        this.userProfileDaoJpa = userProfileDaoJpa;
        this.passwordEncoder = passwordEncoder;
    }

    public UserProfileVO getAllUserProfiles() {

        return userProfileDaoJpa.getAllUserProfile();

    }

    public void saveUserProfile(UserProfileVO userProfileVO) throws Exception {

        UserProfileValidator.validateVODO(userProfileVO);

        UserProfileDO userProfileDO = userProfileVO.getUserProfileDO();

        if (Objects.isNull(userProfileDO.getUserId())) {
            userProfileDaoJpa.getUserProfileByUsername(userProfileVO);

            if (!CollectionUtils.isEmpty(userProfileVO.getUserProfileDOList())) {
                throw new Exception("Username exist");
            }
        }

        TbProfile tbProfile;
        TbUser tbUser;

        if (Objects.isNull(userProfileDO.getProfileId())) {
            tbProfile = new TbProfile();
        } else {
            tbProfile = userProfileDaoJpa.getById(TbProfile.class, userProfileDO.getProfileId());
            if (Objects.isNull(tbProfile)) {
                throw new Exception("Profile not found");
            }
        }

        tbProfile.setName(userProfileDO.getName());
        tbProfile.setDateOfBirth(userProfileDO.getDateOfBirth());
        tbProfile.setIdNo(userProfileDO.getIdNo());
        tbProfile.setIdType(userProfileDO.getIdType());
        tbProfile.setGender(userProfileDO.getGender());
        tbProfile.setEmail(userProfileDO.getEmail());

        tbProfile = userProfileDaoJpa.saveOrUpdate(tbProfile);

        if (Objects.isNull(userProfileDO.getUserId())) {
            tbUser = new TbUser();
        } else {
            tbUser = userProfileDaoJpa.getById(TbUser.class, userProfileDO.getUserId());
            if (Objects.isNull(tbUser)) {
                throw new Exception("User not found");
            }
        }

        String rawPassword = userProfileDO.getPassword();

        if (Objects.nonNull(rawPassword) && !rawPassword.isBlank()) {

            // Only encode if it's a NEW user or password is changed
            if (Objects.isNull(userProfileDO.getUserId())
                    || !passwordEncoder.matches(rawPassword, tbUser.getPassword())) {

                tbUser.setPassword(passwordEncoder.encode(rawPassword));
            }

        }

        tbUser.setUsername(userProfileDO.getUsername());
        tbUser.setFkProfileId(tbProfile);

        tbUser = userProfileDaoJpa.saveOrUpdate(tbUser);

        userProfileDO.setUserId(tbUser.getPkUserId());
        userProfileDO.setProfileId(tbProfile.getPkProfileId());

    }

    public void deleteUser(UserProfileVO userProfileVO) throws Exception {

        // Step 0 - null checking

        UserProfileValidator.validateVODO(userProfileVO);

        UserProfileDO userProfileDO = userProfileVO.getUserProfileDO();

        // Step 1 - set entity
        TbUser tbUser = new TbUser();
        tbUser.setPkUserId(userProfileDO.getUserId());
        tbUser.setUsername(userProfileDO.getUsername());
        tbUser.setPassword(userProfileDO.getPassword());

        // Step 3 - delete
        userProfileDaoJpa.delete(tbUser);

    }

    public void login(UserProfileVO userProfileVO) throws Exception {

        UserProfileValidator.validateLogin(userProfileVO);

        userProfileDaoJpa.getUserProfileByUsername(userProfileVO);

        if (CollectionUtils.isEmpty(userProfileVO.getUserProfileDOList())) {
            throw new Exception("Username not exist");
        }

        UserProfileDO userProfileDODb = userProfileVO.getUserProfileDOList().getFirst();

        if (!passwordEncoder.matches(userProfileVO.getUserProfileDO().getPassword(), userProfileDODb.getPassword())) {
            throw new Exception("Incorrect password");
        }

    }

    public void getUserDetails(UserProfileVO userProfileVO) throws Exception {

        UserProfileValidator.validateVODO(userProfileVO);

        TbUser tbUser = userProfileDaoJpa.getById(TbUser.class, userProfileVO.getUserProfileDO().getUserId());

        if (Objects.isNull(tbUser)) {
            throw new Exception("User data missing");
        }

        TbProfile tbProfile = userProfileDaoJpa.getById(TbProfile.class, tbUser.getFkProfileId().getPkProfileId());

        if (Objects.isNull(tbProfile)) {
            throw new Exception("Profile data missing");
        }

        UserProfileDO userProfileDO = new UserProfileDO();
        userProfileDO.setUserId(tbUser.getPkUserId());
        userProfileDO.setProfileId(tbProfile.getPkProfileId());

        ObjectService.copyProperties(tbUser, userProfileDO);
        ObjectService.copyProperties(tbProfile, userProfileDO);

        userProfileVO.setUserProfileDO(userProfileDO);

    }

}
