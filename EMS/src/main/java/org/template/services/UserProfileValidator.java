package org.template.services;

import ch.qos.logback.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.template.common.services.BaseLog;
import org.template.models.UserProfileVO;

import java.util.Objects;

@Service
public class UserProfileValidator extends BaseLog {

    public static void validateLogin(UserProfileVO userProfileVO) throws Exception {

        validateVODO(userProfileVO);

        if (StringUtil.isNullOrEmpty(userProfileVO.getUserProfileDO().getUsername())) {
            throw new Exception("Username is required");
        }

        if (StringUtil.isNullOrEmpty(userProfileVO.getUserProfileDO().getPassword())) {
            throw new Exception("Password is required");
        }

    }

    public static void validateVODO(UserProfileVO userProfileVO) throws Exception {
        if (Objects.isNull(userProfileVO) || Objects.isNull(userProfileVO.getUserProfileDO())) {
            throw new Exception("Something went wrong");
        }
    }

}
