package org.template.services.validator;

import org.template.models.VO.RoleVO;

import java.util.Objects;

public class RoleValidator {

    public static void validateVODO(RoleVO roleVO) throws Exception {
        if (Objects.isNull(roleVO) || Objects.isNull(roleVO.getRoleDO())) {
            throw new Exception("Something went wrong");
        }
    }
}
