package net.togogo.service;

import net.togogo.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    /*查询所有用户*/
    public List<User> getAllUser(int page, int limit);

    /*根据用户名、密码以及用户类型匹配，验证是否登录*/
    public Boolean checkLogin(String userName, String password, int userType, HttpServletRequest request);

    /*添加用户，根据目前登录用户的等级判断是否成功*/
    public Boolean addUser(User user,HttpServletRequest request);

    /*删除用户，根据目前登录用户的等级判断是否成功*/
    public Boolean deleteUser(List<Integer> ids,HttpServletRequest request);

    /*修改用户密码*/
    public int updatePassword(int userID,int userType,String oldPwd,String newPwd,HttpServletRequest request);

    /*修改用户权限*/
    public Boolean updatePower(int userID,int oldType,int newType,HttpServletRequest request);

    /*根据用户名和类型搜索用户*/
    public List<User> searchUser(String userID,String userName,String userType);
}
