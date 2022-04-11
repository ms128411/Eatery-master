package com.eatery.cd.service;

import com.eatery.cd.pojo.User;

import java.util.List;

public interface UserService {
    User findUserInfo(String userId);

    /**
     * 修改地址 马爽
     * @param user
     */
    void updateAddress(User user);

    /**
     * 修改密码  马爽
     * @param comfirmPwd
     */
    void updatePwd(String comfirmPwd);

    void updateTel(String newTel,String userId);

    void deleteTel(String userId);

    void updateEmail(String userId,String newEmail);

    /**
     * 查询所有的管理员信息
     * @return  存有管理员对象的列表
     */
    List<User> findAdminList();

    /**
     * 根据管理员Id查询管理员
     * @param userId  管理员的Id
     * @return  返回的是管理员对象
     */
    User findUserByUserId(String userId);

    /**
     * 更新管理数据
     * @param user 管理员对象
     */
    void updateUser(User user);

    /**
     * 更改管理员的密码
     * @param user   更新信息
     */
    void updateUserPassword(User user);

    List<String> findAdminRole(String userId);

    void addUser(User user);

    void deleteAdminsById(String[] userIds);

    List<User> findUserByUsername(String username);

    void updateUserState(String userId, int state);

    void deleteAdminById(String userId);

    User findUserByU_P(String username, String password);

    User finuserByUsername(String username);

    void registerUser(User user);

    void updateUserById(User user);

    /**
     * 注册角色
     * @param userId
     */
    void registerRole(String userId);

}
