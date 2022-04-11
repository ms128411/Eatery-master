package net.togogo.controller;

import com.github.pagehelper.PageInfo;
import net.togogo.domain.NewsInfo;
import net.togogo.service.impl.NewsInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/*新闻编辑页面接口*/
@Controller
public class NewsInfoController {

    @Autowired
    NewsInfoServiceImpl newsInfoServiceImpl;
    /*访问编辑页面*/
    @RequestMapping("/newsInfoIndex")
    public String newsIndex()
    {
        return "newsInfo/newsInfoIndex";
    }

    @RequestMapping("/newsInfoAll")
    @ResponseBody
    public Map<String,Object> getAllNews(int page, int limit)
    {
        Map<String,Object> map = new HashMap<>();

        /*所有管理员*/
        List<NewsInfo> typeList = newsInfoServiceImpl.getAllNews(page,limit);
        System.out.println("hh");
        System.out.println(typeList);

        /*包含分页信息*/
        PageInfo<NewsInfo> pageInfo = new PageInfo<>(typeList);
        map.put("data",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    /*访问添加新闻页面*/
    @RequestMapping("/newsAdd")
    public String newsAdd(){
        return "newsInfo/newsUpdate";
    }

    /*访问修改新闻页面*/
    @RequestMapping("/newsAlter")
    public String newsAlter(){

        return "newsInfo/newsAlter";
    }

    /*修改新闻请求*/
    @RequestMapping("/alterNews")
    @ResponseBody
    public Map<String,Object> alterNews(int newsID,String photoUrl,String title,String newsType,String content)
    {
        System.out.println("进入新闻修改控制器");
        System.out.println(newsID+photoUrl+title+newsType+content);
        Map<String,Object> map = new HashMap<>();

        Boolean alterResult = newsInfoServiceImpl.alterNews(newsID,photoUrl,title,newsType,content);
        /*修改成功*/
        if(alterResult==true){
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }

    /*添加新闻请求*/
    @RequestMapping("/addNews")
    @ResponseBody
    public Map<String,Object> addNews(String photoUrl,String title,String newsType,String content, HttpServletRequest request)
    {

        Map<String,Object> map = new HashMap<>();

        Boolean addResult = newsInfoServiceImpl.addNews(photoUrl,title,newsType,content,request);
        /*添加成功*/
        if(addResult==true){
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }



    @RequestMapping("/serachNews")
    @ResponseBody
    public Map<String,Object> serachNews(String title,String username,String newsType)
    {
        System.out.println("co"+title+username+newsType);

        Map<String,Object> map = new HashMap<>();
        if(title==null||title.trim().length()==0)
        {
            title=null;
        }
        if(username==null||username.trim().length()==0)
        {
            username=null;
        }
        if(newsType==null||newsType.trim().length()==0)
        {
            newsType=null;
        }


        List<NewsInfo> news = newsInfoServiceImpl.searchNews(title,username,newsType);
        PageInfo<NewsInfo> pageInfo = new PageInfo<>(news);
        map.put("data",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping("/deleteNews")
    @ResponseBody
    public Map<String,Object> deleteNews(String ids)
    {
        System.out.println(ids);
        System.out.println("进入删除新闻的控制器");
        HashMap<String,Object> map = new HashMap<>();

        /*需要把ids转化为集合*/
        List<String> idlist = Arrays.asList(ids.split(","));
        List<Integer> idList = new ArrayList<>();

        for (String id:idlist)
        {
            idList.add(Integer.parseInt(id));
        }

        System.out.println(idList);
        boolean deleteresult = newsInfoServiceImpl.deleteNews(idList);

        if(deleteresult==true)
        {
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }

}
