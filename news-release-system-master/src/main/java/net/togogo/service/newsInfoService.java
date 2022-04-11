package net.togogo.service;

import net.togogo.domain.NewsInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface newsInfoService {
    public List<NewsInfo> getAllNews(int page, int limit);
    public List<NewsInfo> searchNews(String title, String username, String newsType);
    public Boolean deleteNews(List<Integer> ids);

    public Boolean addNews(String photoUrl,String title,String newsType,String content, HttpServletRequest request);

    public Boolean alterNews(int newsID,String photoUrl,String title,String newsType,String content);
    /*根据用户名和密码匹配，验证是否登录*/
//    public Boolean checkLogin(String username, String password, HttpServletRequest request);
    /*修改密码*/
    /*修改管理员密码*/
//    public Boolean updateNt(int id,String newNt);
//    public Boolean addType(String newType);
//    public Boolean deleteType(List<Integer> ids);
//    public List<NewsInfo> searchType(String newsType);
}

