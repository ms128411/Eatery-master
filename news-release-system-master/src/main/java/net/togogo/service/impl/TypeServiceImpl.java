package net.togogo.service.impl;

import com.github.pagehelper.PageHelper;
import net.togogo.dao.TypeDao;
import net.togogo.domain.Type;
import net.togogo.service.typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements typeService {
    @Resource
    TypeDao typeDao;

    /*查询所有管理员*/
    public List<Type> getAllType(int page, int limit)
    {
        /*计算当前页是从第几条数据开始查询*/
        PageHelper.startPage(page,limit);
        return typeDao.getAllType();
    }
    public List<String> getAllType2()
    {
        /*计算当前页是从第几条数据开始查询*/
        return typeDao.getAllType2();
    }
//    /*根据用户名和密码匹配，验证是否登录*/
//    public Boolean checkLogin(String userName, String password, HttpServletRequest request)
//    {
//        User admin = adminDao.getAdminByUP(userName,password);
//
//        /*如果查询到的用户不为null*/
//        if(admin!=null)
//        {
//            /*用户名和密码是正确*/
//            /*可进行登录，用户信息存到session当中*/
//            request.getSession().setAttribute("loginAdmin",admin);
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
public Boolean updateNt(int id, String newNt)
{
    System.out.println(id+newNt);
    int updateReuslt = typeDao.updateNt(id,newNt);

    if (updateReuslt> 0) {
        return true;
    } else {
        return false;
    }
}

    /*添加管理员*/
    public Boolean addType(String newType)
    {
        System.out.println(newType+" ser");
        int addResult = typeDao.addType(newType);

        /*根据返回的整数判断是否添加成功*/
        if(addResult>0)
        {
            /*返回true表示添加成功*/
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean deleteType(List<Integer> ids)
    {
        int deletResult = typeDao.deleteType(ids);
        System.out.println("ser");
        if(deletResult>0)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public List<Type> searchType(String newsType)
    {
        PageHelper.startPage(1,10);
        return typeDao.searchType(newsType);
    }

    public boolean searchType1(String newsType) {

        if (typeDao.searchType1(newsType) != null)
        {
            System.out.println("有");
            return true;}

        else
            return false;
    }

}
