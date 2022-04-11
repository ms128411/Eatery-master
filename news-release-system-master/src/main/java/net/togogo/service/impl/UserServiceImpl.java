package net.togogo.service.impl;

import com.github.pagehelper.PageHelper;
import net.togogo.dao.UserDao;
import net.togogo.domain.User;
import net.togogo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    /*查询所有用户*/
    public List<User> getAllUser(int page, int limit)
    {
        /*计算当前页是从第几条数据开始查询*/
        PageHelper.startPage(page,limit);
        return userDao.getAllUser();
    }

    /*添加用户，根据目前登录用户的等级判断是否成功*/
    public Boolean addUser(User user,HttpServletRequest request)
    {
        /*获取会话中的user信息*/
        User user1=(User)request.getSession().getAttribute("loginUser");
        /*登录中的用户等级高于或等于要添加的用户等级，允许添加*/
        if(user1.getUserType()>=user.getUserType())
        {
            return userDao.addUser(user)>0;
        }
        else {
            return false;
        }
    }

    public Boolean deleteUser(List<Integer> ids, HttpServletRequest request) {

        /*获取会话中的user信息*/
        User user1=(User)request.getSession().getAttribute("loginUser");

        //如果删除的用户中的任意一个用户级别大于登录中的用户，拒绝删除
        for(int userID:ids){
           User user = userDao.getUserByID(userID);
           if(user.getUserType()>user1.getUserType()){
               return false;
           }
        }
        int deleteResult = userDao.deleteUserByIds(ids);

        System.out.println("删除结果:"+deleteResult);

        if(deleteResult>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    /*修改用户密码*/
    @Override
    public int updatePassword(int userID, int userType,String oldPwd, String newPwd, HttpServletRequest request) {
        /*获取会话中的user信息*/
        User user1=(User)request.getSession().getAttribute("loginUser");
        /*登录中的用户等级高于或等于要修改的用户等级，允许修改*/
        if(user1.getUserType()>=userType)
        {
            int updateResult = userDao.passwordUpdate(userID,oldPwd,newPwd);
            /*返回结果>1，修改密码成功*/
            if(updateResult>0)
            {
                return 1;
            }
            /*密码匹配错误返回-1*/
            else {
                return -1;
            }
        }
        else {
            //返回0说明权限不足
            return 0;
        }
    }

    @Override
    public Boolean updatePower(int userID, int oldType, int newType, HttpServletRequest request) {

        /*获取会话中的user信息*/
        User user1=(User)request.getSession().getAttribute("loginUser");
        /*登录中的用户等级高于或等于要修改的用户等级已经要修改后的等级才允许修改*/
        if(user1.getUserType()>=oldType && user1.getUserType()>=newType)
        {
            return userDao.powerUpdate(userID,newType)>0;
        }
        //权限不足
        else {
            return false;
        }
    }

    @Override
    public List<User> searchUser(String userID,String userName, String userType) {
        PageHelper.startPage(1,15);
        return userDao.searchUser(userID,userName, userType);
    }

    /*根据用户名和密码匹配，验证是否登录*/
    public Boolean checkLogin(String userName, String password, int userType,HttpServletRequest request)
    {
        User user = userDao.getUserByUP(userName,password);

        /*如果查询到的用户不为null*/
        if(user!=null)
        {
            request.getSession().setAttribute("loginUser",user);
            /*若登录为管理员且类型匹配*/
            /*可进行登录，用户信息存到session当中*/
            if(userType==1&&user.getUserType()>=1){
                return true;
            }
            /*若登录为游客且类型匹配*/
            else if(userType==user.getUserType()){
                return true;
            }
            else{
                return false;
            }
            /*用户名和密码是正确*/


        }
        else {
            return false;
        }
    }
}
