package com.eatery.cd.mapper;

import com.eatery.cd.pojo.User;
import com.eatery.cd.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    /**
     * 更新用户表中的数据
     * @param userInfo 用户详情对象
     */
    public void updateUser(UserInfo userInfo);

    void addUser(UserInfo userInfo);

    void deleteAdminsById(String[] userIds);

    void updateUserState(@Param("userId") String userId, @Param("state") int state);

    void deleteAdminById(String userId);
}
