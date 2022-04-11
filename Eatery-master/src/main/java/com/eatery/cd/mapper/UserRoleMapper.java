package com.eatery.cd.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    void addUser(@Param("userId") String userId, @Param("roleId") String roleId);

    void deleteAdminsById(String[] userIds);

    void deleteAdminById(String userId);

    void updateUser(@Param("roleId") String roleId, @Param("userId") String userId);

}
