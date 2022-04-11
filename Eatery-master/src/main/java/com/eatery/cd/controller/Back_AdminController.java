package com.eatery.cd.controller;

import com.eatery.cd.pojo.Role;
import com.eatery.cd.pojo.User;
import com.eatery.cd.service.RoleService;
import com.eatery.cd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Back_AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    /*后台---用户列表显示页面*/
    @RequestMapping("/administrator")
    public String findAdminList(HttpSession session, Model model){

        List<User> adminList = userService.findAdminList();

        List<Role> roleList  = roleService.findRoleList();


        String userId =((User)session.getAttribute("user")).getUserId();

        List<String> roleListById = userService.findAdminRole(userId);


        String role1 = "超级管理员";



        if(roleListById.contains(role1)) {
            model.addAttribute("role", role1);
        } else {
            model.addAttribute("role", "");
        }

        model.addAttribute("roleList", roleList);
        model.addAttribute("adminList", adminList);
        return "/admin/administrator";
    }

    /**
     *
     * @param session  前台传过来的管理员的session，里面存有user对象
     * @param model
     * @return
     */
    @RequestMapping("/adminInfo")
    public String findUserByUserId(HttpSession session, Model model){

    String userId =((User)session.getAttribute("user")).getUserId();

        User user = userService.findUserByUserId(userId);

        model.addAttribute("user", user);
        return "/admin/admin_info";
    }

    @RequestMapping("/updateAdmin")
    public String updateUser(User user) {

        System.out.print(user + "=========================================================================================");


        userService.updateUser(user);

        return "redirect:/adminInfo";
    }


    @RequestMapping("/updateAdminById")
    public String updateUserById(User user) {


        userService.updateUserById(user);

        return "redirect:/adminInfo";
    }

    @RequestMapping("/check")
    @ResponseBody
    public String checkPassowrd(User user, String npassword){

        String password = userService.findUserByUserId(user.getUserId()).getUpassword();

        if(user.getUpassword().equals(password)){

            user.setUpassword(npassword);
            userService.updateUserPassword(user);

            return "true";
        }else{
            return "false";
        }
    }

    @RequestMapping("/addAdmin")
    public String addAdmin(User user) {

        userService.addUser(user);
        //System.out.println("======================================================================================");
        System.out.println(user.toString());

        return "redirect:/administrator";
    }

    /*按id用户*/
    @RequestMapping("/deleteAdmins")
    public String deleteAdmins(@RequestParam("userId") String[] userIds){
        userService.deleteAdminsById(userIds);
        return "redirect:/administrator";
    }

    /*后台---按名字查询*/
    @RequestMapping("/selectAdmin")
    public String selectAdmin(String username, Model model, HttpSession session){




        List<User> adminList = userService.findUserByUsername(username);

        List<Role> roleList  = roleService.findRoleList();

        String userId =((User)session.getAttribute("user")).getUserId();

        List<String> roleListById = userService.findAdminRole(userId);

        String role1 = "超级管理员";


        if(roleListById.contains(role1)) {
            model.addAttribute("role", role1);
        }else {
            model.addAttribute("role", "");
        }

        model.addAttribute("roleList", roleList);
        model.addAttribute("adminList", adminList);
        return "/admin/administrator";

       /* List<User> adminList = userService.findUserByUsername(username);
        List<Role> roleList  = roleService.findRoleList();



        model.addAttribute("adminList", adminList);
        model.addAttribute("roleList", roleList);

        return "/admin/administrator";*/
    }

    /*停用*/
    @RequestMapping("/stopState")
    public void stopState(String userId) {
        int state = 0;

        userService.updateUserState(userId, state);
    }

    /*启用*/
    @RequestMapping("/startState")
    public void startState(String userId) {
        int state = 1;
        userService.updateUserState(userId, state);
    }

    @RequestMapping("/deleteAdminById")
    public void deleteAdminById(String userId) {
        userService.deleteAdminById(userId);
    }

    @RequestMapping("/adminCompetence")
    public String adminCompetence(Model model) {
        List<User> userList = userService.findAdminList();

        String pRoleName = "";
        String sRoleName = "";
        int pCount = 0;
        int sCount = 0;
        for(User user : userList){
            if("超级管理员".equals(user.getRole().getRoleName())){
                sRoleName = sRoleName + " " + user.getUsername();
                sCount = sCount + 1;
            }else{
                pRoleName = pRoleName + "  " + user.getUsername();
                pCount = pCount + 1;
            }
        }

        model.addAttribute("pRoleName", pRoleName);
        model.addAttribute("pCount", pCount);
        model.addAttribute("sRoleName" , sRoleName);
        model.addAttribute("sCount", sCount);

        return "/admin/admin_Competence";
    }


}

