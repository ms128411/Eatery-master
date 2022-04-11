package com.eatery.cd.mapper;

import com.eatery.cd.pojo.User;
import com.eatery.cd.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User findUserInfo(String userId);

    void updateAddress(UserInfo userInfo);

    void updatePwd(String comfirmPwd);

    void updateTel(@Param("newTel") String newTel, @Param("userId") String userId);

    void deleteTel(String userId);

    void updateEmail(@Param("userId") String userId,@Param("newEmail") String newEmail);



    /*---------------------------------------------------------------------------------*/

    User findUserByUserId(String userId);

    void updateUser(User user);

    List<String> findAdminRole(String userId);

    void addUser(User user);

    void deleteAdminsById(String[] userIds);

    List<User> findUserByUsername(String username);

    void deleteAdminById(String userId);

    /**
     * 查询所有的管理员信息
     * @return  存有管理员对象的列表
     */
    List<User> findAdminList();



    /*-------------------------------------lww----------------------------------------*/
    User findUserByU_P(@Param("username") String username, @Param("password") String password);

    User finuserByUsername(String username);

    void registerUser(User user);
    void registerUserInfo(UserInfo userInfo);

    /**
     * 注册用户角色
     * @param userId
     */
    void registerRole(String userId);
}

