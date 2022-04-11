package net.togogo.controller;


import com.github.pagehelper.PageInfo;
import net.togogo.domain.User;
import net.togogo.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class  UserController {

    @Resource
    UserServiceImpl userService;

    /*访问用户编辑页面*/
    @RequestMapping("/userIndex")
    public String userIndex()
    {
        return "user/userIndex";
    }

    /*访问添加用户页面*/
    @RequestMapping("/userAdd")
    public String userAdd()
    {
        return "user/userAdd";
    }

    /*当前用户修改自身密码*/
    @RequestMapping("/alterSelfPwd")
    public String alterSelfPwd(Model model,HttpServletRequest request){

        User user=(User)request.getSession().getAttribute("loginUser");

        model.addAttribute("userID",user.getUserID());
        model.addAttribute("userType",user.getUserType());
        model.addAttribute("userName",user.getUserName());
        return "user/passwordUpdate";

    }
    /*访问修改密码页面*/
    @RequestMapping("/queryUserById")
    public String queryUserById(int userID, int userType,String userName,Model model)
    {
        //将userID、userType加入model中 前端用el表达式获取
        model.addAttribute("userID",userID);
        model.addAttribute("userType",userType);
        model.addAttribute("userName",userName);
        //userName为null时访问修改密码页面，否则访问修改权限页面
        if(userName==null){
            return "user/passwordUpdate";
        }
        else{
            return "user/powerUpdate";
        }
    }


    /*修改密码请求*/
    @RequestMapping("/updatePwdSubmit")
    @ResponseBody
    public Map<String,Object> updatePwdSubmit(int userID,int userType,String oldPwd,String newPwd,HttpServletRequest request)
    {
        System.out.println("修改密码控制器");
        System.out.println("userID:"+userID);
        System.out.println("userType:"+userType);
        Map<String,Object> map = new HashMap<>();
        int updateResult = userService.updatePassword(userID,userType,oldPwd,newPwd,request);

        /*修改成功*/
        if(updateResult==1){
            map.put("code",0);
        }
        else if(updateResult==0){
            map.put("code",-1);
            map.put("msg","权限不足，修改失败！");
        }
        else {
            map.put("code",-1);
            map.put("msg","原密码错误，请重新输入");
        }
        return map;
    }

    /*修改权限请求*/
    @RequestMapping("/updatePowerSubmit")
    @ResponseBody
    public Map<String,Object> updatePowerSubmit(int userID,int oldType,int newType,HttpServletRequest request)
    {
        Map<String,Object> map = new HashMap<>();
        Boolean updateResult = userService.updatePower(userID,oldType,newType,request);

        /*修改成功*/
        if(updateResult==true){
            map.put("code",0);
        }
        else {
            map.put("code",-1);
            map.put("msg","权限不足，修改失败！");
        }
        return map;
    }

    /*删除用户请求,传入的参数为字符串*/
    @RequestMapping("/deleteUserByIds")
    @ResponseBody
    public Map<String,Object> deleteUsers(String ids,HttpServletRequest request)
    {
        Map<String,Object> map = new HashMap<>();

        System.out.println("ids:"+ids);

        /*需要把ids转化为集合*/
        List<String> idlist = Arrays.asList(ids.split(","));
        List<Integer> idList = new ArrayList<>();
        for (String id:idlist)
        {
            idList.add(Integer.parseInt(id));
        }

        System.out.println("idList:"+idList);

        boolean addResult = userService.deleteUser(idList,request);

        /*添加成功*/
        if(addResult==true){
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }


    /*添加用户请求*/
    @RequestMapping("/addUserSubmit")
    @ResponseBody
    public Map<String,Object> addUser(User user, HttpServletRequest request)
    {
        Map<String,Object> map = new HashMap<>();
        boolean addResult = userService.addUser(user,request);

        /*添加成功*/
        if(addResult==true){
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }

    @RequestMapping("/userAll")
    @ResponseBody
    public Map<String,Object> userAll(int page,int limit)
    {
        Map<String,Object> map = new HashMap<>();

        /*所有管理员*/
        List<User> adminList = userService.getAllUser(page,limit);
        /*包含分页信息*/
        PageInfo<User> pageInfo = new PageInfo<>(adminList);
        map.put("data",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code",0);//layui中0才表示成功
        map.put("msg","");
        return map;
    }

    @RequestMapping("/searchUser")
    @ResponseBody
    public Map<String,Object> searchUser(String userID,String userName,String userType)
    {
        Map<String,Object> map = new HashMap<>();
        if(userID==null||userID.trim().length()==0)
        {
            userID=null;
        }
        if(userName==null||userName.trim().length()==0)
        {
            userName=null;
        }
        if(userType==null||userType.trim().length()==0)
        {
            userType=null;
        }

        List<User> users = userService.searchUser(userID,userName,userType);

        PageInfo<User> pageInfo = new PageInfo<>(users);

        map.put("data",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code",0);
        map.put("msg","");
        return map;
    }

}
