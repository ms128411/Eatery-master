package net.togogo.dao;

import net.togogo.domain.NewsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsInfoDao {

    /*分页查询所有类别*/
    public List<NewsInfo> getAllNews();
    public List<NewsInfo> searchNews(@Param("title") String title, @Param("username") String username, @Param("newsType") String newsType);
    public int deleteNews(List<Integer> ids);

    /*添加新闻*/
    public int addNews(String photoUrl, String title, int authorID,int newsTypeID, String content);
    /*修改新闻*/
    public int alterNews(int newsID,String photoUrl,String title,int newsTypeID,String content);

}
