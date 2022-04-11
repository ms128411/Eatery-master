package net.togogo.controller;

import com.github.pagehelper.PageInfo;
import net.togogo.domain.Type;
import net.togogo.service.impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;


/*新闻类型管理接口*/
@Controller
public class TypeController {
    /*访问类型管理页面*/

    @Resource
    TypeServiceImpl typeServiceimpl;
    /*访问编辑页面*/
    @RequestMapping("/newsTypeIndex")
    public String bookIndex()
    {
        return "newsType/newsTypeIndex";
    }

    @RequestMapping("/newsTypeAll")
    @ResponseBody
    public Map<String,Object> typeAll(int page, int limit)
    {
        Map<String,Object> map = new HashMap<>();

        /*所有管理员*/
        List<Type> typeList =typeServiceimpl.getAllType(page,limit);
        /*包含分页信息*/
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        map.put("data",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping("/typeAdd")
    public String typeAdd()
    {
        return "newsType/typeAdd1";
    }

    /*添加管理员操作*/
    @RequestMapping("/addTypeSubmit")
    @ResponseBody
    public Map<String,Object> addType(String newType)
    {

        HashMap<String,Object> map = new HashMap<>();
        /*调用AdminService里面的添加管理员的业务*/
        if (typeServiceimpl.searchType1(newType.trim()))
        {
            System.out.println("有了");
            map.put("code", -1);
        }
        else
        {
            boolean addresult = typeServiceimpl.addType(newType);

            if (addresult == true)
            {
                map.put("code", 0);
            }
        }

        return map;
    }

    @RequestMapping("/deleteType")
    @ResponseBody
    public Map<String,Object> deleteAdmins(String ids)
    {
        System.out.println(ids);
        System.out.println("进入删除管理员的控制器");
        HashMap<String,Object> map = new HashMap<>();

        /*需要把ids转化为集合*/
        List<String> idlist = Arrays.asList(ids.split(","));
        List<Integer> idList = new ArrayList<>();

        for (String id:idlist)
        {
            idList.add(Integer.parseInt(id));
        }

        System.out.println(idList);
        boolean deleteresult = typeServiceimpl.deleteType(idList);

        if(deleteresult==true)
        {
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }
@RequestMapping("/newsTypeAll2")
@ResponseBody
public Map<String,Object> newsTypeAll2(){

    Map<String,Object> map = new HashMap<>();

    /*所有管理员*/
    List<String> typeList =typeServiceimpl.getAllType2();
    /*包含分页信息*/

    map.put("data",typeList);
    map.put("code",0);
    map.put("msg","");
    return map;


}
    /*打开修改类别的页面*/
    @RequestMapping("/queryTypeById")
    public String queryTypeById(String id, Model model)
    {

        model.addAttribute("id",id);
        return "newsType/updateType";
    }

    /*修改管理员密码*/
    @RequestMapping("/updateNtSubmit")
    @ResponseBody
    public Map<String,Object> updateNt(String id,String newNt) {
        HashMap<String, Object> map = new HashMap<>();
        if (typeServiceimpl.searchType1(newNt) == true) {
            map.put("code", -1);
        }
        else {
            /*调用AdminService内的修改密码的业务*/
            boolean updateresult = typeServiceimpl.updateNt(Integer.parseInt(id), newNt);
            System.out.println(updateresult);

            if (updateresult == true) {
                map.put("code", 0);
            }
        }

        return map;

    }

   @RequestMapping("/searchType")
    @ResponseBody
    public Map<String,Object> searchType(String newsType)
    {
        Map<String,Object> map = new HashMap<>();
        if(newsType==null||newsType.trim().length()==0)
        {
            newsType=null;
        }
        List<Type> types;
        if(newsType == null){
    types =typeServiceimpl.searchType(newsType);}
        else
        {  types =typeServiceimpl.searchType(newsType.trim());}

        PageInfo<Type> pageInfo = new PageInfo<>(types);
        map.put("data",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code",0);
        map.put("msg","");
        return map;
    }

}
