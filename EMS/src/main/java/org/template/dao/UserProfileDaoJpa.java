package org.template.dao;

import org.springframework.stereotype.Repository;
import org.template.common.constants.RecordStatusConstant;
import org.template.common.models.QueryParameter;
import org.template.common.services.ObjectService;
import org.template.models.UserProfileDO;
import org.template.models.UserProfileVO;
import org.template.services.UserProfileValidator;
import org.template.tables.TbUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserProfileDaoJpa extends DaoJpa{

    public UserProfileVO getAllUserProfile(){

        UserProfileVO userProfileVO = new UserProfileVO();

        List<UserProfileDO> userProfileDOList = new ArrayList<>();
        List<TbUser> tbUserList = this.getAll(TbUser.class);

        for (TbUser tbUser : tbUserList) {
            UserProfileDO userProfileDO = new UserProfileDO();
            ObjectService.copyProperties(tbUser, userProfileDO);
            userProfileDOList.add(userProfileDO);

            if (Objects.isNull(userProfileVO.getUserProfileDO())) {
                userProfileVO.setUserProfileDO(userProfileDO);
            }
        }

        userProfileVO.setUserProfileDOList(userProfileDOList);

        return userProfileVO;
    }

    public void getUserProfileByUsername(UserProfileVO userProfileVO) throws Exception {

        UserProfileValidator.validateVODO(userProfileVO);

        UserProfileDO userProfileDO = userProfileVO.getUserProfileDO();

        List<QueryParameter> paramList = new ArrayList<>();
        String jpaQlStr = "SELECT new org.template.models.UserProfileDO(" +
                "u.username, u.password)" +
                " FROM TbUser as u" +
                " WHERE u.statusDb = " +
                RecordStatusConstant.ACTIVE.getValue() +
                " AND u.username = :username";
        paramList.add(new QueryParameter("username", userProfileDO.getUsername()));

        List<UserProfileDO> userProfileDOList = getResultList(jpaQlStr, paramList, UserProfileDO.class);

        userProfileVO.setUserProfileDOList(userProfileDOList);

    }

}
