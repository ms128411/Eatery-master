package net.togogo.service.impl;

import com.github.pagehelper.PageHelper;
import net.togogo.dao.NewsInfoDao;
import net.togogo.dao.TypeDao;
import net.togogo.domain.NewsInfo;
import net.togogo.domain.User;
import net.togogo.service.newsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class NewsInfoServiceImpl implements newsInfoService {

    @Autowired
    NewsInfoDao newsInfoDao;

    @Resource
    TypeDao typeDao;

    /*添加新闻*/
    @Override
    public Boolean addNews(String photoUrl, String title, String newsType, String content, HttpServletRequest request)
    {

        /*当前用户ID即为作者ID*/
        User user=(User)request.getSession().getAttribute("loginUser");

        int newsTypeID = typeDao.getTypeByNy(newsType).getTypeID();

        return newsInfoDao.addNews(photoUrl,title,user.getUserID(),newsTypeID,content)>0;
    }

    @Override
    public Boolean alterNews(int newsID, String photoUrl, String title, String newsType, String content) {

        System.out.println("进入新闻修改服务");

        int newsTypeID = typeDao.getTypeByNy(newsType).getTypeID();
        System.out.println(newsID+photoUrl+title+newsTypeID+content);
        return newsInfoDao.alterNews(newsID,photoUrl,title,newsTypeID,content)>0;
    }

    /*查询所有新闻*/
    public List<NewsInfo> getAllNews(int page, int limit)
    {
        System.out.println("ser");
        /*计算当前页是从第几条数据开始查询*/
        PageHelper.startPage(page,limit);
        List<NewsInfo> newlist=newsInfoDao.getAllNews();
        return  newlist;
    }
    public List<NewsInfo> searchNews(String title, String username, String newsType)
    {
        PageHelper.startPage(1,15);
        return newsInfoDao.searchNews(title,username,newsType);
    }

    public Boolean deleteNews(List<Integer> ids)
    {
        int deletResult = newsInfoDao.deleteNews(ids);
        System.out.println("ser");
        if(deletResult>0)
        {
            return true;
        }
        else {
            return false;
        }
    }


}
