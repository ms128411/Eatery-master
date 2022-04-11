package net.togogo.service;

import net.togogo.domain.Type;

import java.util.List;

public interface typeService {
    /*查询所有管理员*/
    public List<Type> getAllType(int page, int limit);

    /*根据用户名和密码匹配，验证是否登录*/
//    public Boolean checkLogin(String userName, String password, HttpServletRequest request);
    /*修改密码*/
    /*修改管理员密码*/
    public Boolean updateNt(int id, String newNt);
    public Boolean addType(String newType);
    public Boolean deleteType(List<Integer> ids);
    public List<Type> searchType(String newsType);
    public List<String> getAllType2();
    public boolean searchType1(String newsType);
}