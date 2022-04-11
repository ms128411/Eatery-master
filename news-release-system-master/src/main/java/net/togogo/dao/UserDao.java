package net.togogo.dao;
import net.togogo.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*用户服务实现接口 增删查改*/
public interface UserDao {
    /*分页查询所有管理员*/
    public List<User> getAllUser();

    /*验证登录*/
    public User getUserByUP(String username,String password);

    public User getUserByID(int userID);

    /*添加用户*/
    public int addUser(User user);

    /*删除用户*/
    public int deleteUserByIds(List<Integer> ids);

    /*修改密码*/
    public int passwordUpdate(int userID,String oldPwd,String newPwd);

    /*修改权限*/
    public int powerUpdate(int userID,int newType);

    /*搜索用户：根据用户名和类型查询*/
    public List<User> searchUser(@Param("userID") String userID,@Param("userName") String userName, @Param("userType") String userType);
}
