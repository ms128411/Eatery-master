package com.eatery.cd.service;

import com.eatery.cd.mapper.UserInfoMapper;
import com.eatery.cd.mapper.UserMapper;
import com.eatery.cd.mapper.UserRoleMapper;
import com.eatery.cd.pojo.User;
import com.eatery.cd.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserInfo(String userId) {
        return userMapper.findUserInfo(userId);
    }

    @Override
    public void updateAddress(User user) {
        UserInfo userInfo = user.getUserInfo();
        userInfo.setUserInfoId(user.getUserId());
        userMapper.updateAddress(userInfo);
    }

    @Override
    public void updatePwd(String comfirmPwd) {
        userMapper.updatePwd(comfirmPwd);
    }

    @Override
    public void updateTel(String newTel, String userId) {
        userMapper.updateTel(newTel, userId);
    }

    @Override
    public void deleteTel(String userId) {
        userMapper.deleteTel(userId);
    }

    @Override
    public void updateEmail(String userId, String newEmail) {
        userMapper.updateEmail(userId, newEmail);
    }


    @Override
    public List<User> findAdminList() {
        return userMapper.findAdminList();
    }

    @Override
    public User findUserByUserId(String userId) {
        return userMapper.findUserByUserId(userId);
    }

    @Override
    public void updateUser(User user) {

        userMapper.updateUser(user);//跟新用户表

        UserInfo userInfo = user.getUserInfo();
        userInfo.setUserInfoId(user.getUserId());
        userInfoMapper.updateUser(userInfo);//跟新用户详情表

        System.out.println(user.getRole().getRoleId()+ " +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        if (user.getRole().getRoleId().equals("超级管理员")) {
            user.getRole().setRoleId("1");
        } else {
            user.getRole().setRoleId("2");
        }
        System.out.println(user.getRole().getRoleId()+ " -----------------------------------------------------");

        userRoleMapper.updateUser(user.getRole().getRoleId(), user.getUserId());


    }

    @Override
    public void updateUserById(User user) {

        userMapper.updateUser(user);//跟新用户表

        UserInfo userInfo = user.getUserInfo();
        userInfo.setUserInfoId(user.getUserId());
        userInfoMapper.updateUser(userInfo);//跟新用户详情表


    }

    @Override
    public void registerRole(String userId) {
        userMapper.registerRole(userId);
    }

    public void updateUserPassword(User user) {

        userMapper.updateUser(user);//跟新用户表

    }

    @Override
    public List<String> findAdminRole(String userId) {
        return userMapper.findAdminRole(userId);
    }

    @Override
    public void addUser(User user) {

        user.setUserId(UUID.randomUUID().toString());
        userMapper.addUser(user);

        UserInfo userInfo = user.getUserInfo();
        userInfo.setUserInfoId(user.getUserId());
        userInfo.setState(user.getUserInfo().getState());
        //System.out.println("=======================================+++++++++++++++++++++++++++++++++++++++++++++++");
        userInfoMapper.addUser(userInfo);

        String roleName = user.getRole().getRoleName();
        userRoleMapper.addUser(user.getUserId(), roleName);

    }

    @Override
    public void deleteAdminsById(String[] userIds) {

        userRoleMapper.deleteAdminsById(userIds);
        userInfoMapper.deleteAdminsById(userIds);
        userMapper.deleteAdminsById(userIds);

    }

    /*后台--按名字查询*/
    @Override
    public List<User> findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public void updateUserState(String userId, int state) {
        userInfoMapper.updateUserState(userId, state);
    }

    @Override
    public void deleteAdminById(String userId) {

        userRoleMapper.deleteAdminById(userId);

        userInfoMapper.deleteAdminById(userId);

        userMapper.deleteAdminById(userId);
    }

    @Override
    public User findUserByU_P(String username, String password) {

        return userMapper.findUserByU_P(username, password);
    }

    @Override
    public User finuserByUsername(String username) {
        return userMapper.finuserByUsername(username);
    }

    /*前端用户注册*/
    @Override
    public void registerUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setUserId(id);
        UserInfo userInfo = user.getUserInfo();
        userInfo.setUserInfoId(id);
        userMapper.registerUser(user);
        userMapper.registerUserInfo(userInfo);
    }

}

