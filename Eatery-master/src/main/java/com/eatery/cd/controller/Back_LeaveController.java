package com.eatery.cd.controller;


import com.eatery.cd.pojo.Leave;
import com.eatery.cd.pojo.User;
import com.eatery.cd.service.LeaveService;
import com.eatery.cd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class Back_LeaveController {

    //留言信息处理
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private UserService userService;


    @RequestMapping("/guestBook")
    public String guestBook(Model model) {
        List<Leave> list=leaveService.findAllLeaveList();
        model.addAttribute("list",list);

        return "/admin/Guestbook";
    }
    @RequestMapping("/delete")
    public String  deleteById(String leaveId){
        leaveService.deleteById(leaveId);
        return "redirect:/guestBook";
    }



    @RequestMapping("/deleteAll")
    public String  deleteAll(@RequestParam("leaveId") String[] leaveIds){
        System.out.println(Arrays.toString(leaveIds));
        leaveService.deleteAll(leaveIds);
        return "redirect:/guestBook";
    }


    @RequestMapping("/openAll")
    public String updateState(@RequestParam("leaveId")String[] leaveIds){
        System.out.print(leaveIds);
        int state=0;
        leaveService.updateAll(leaveIds,state);

        return "redirect:/guestBook";
    }
    @RequestMapping("/updateStateOnlyOne")
    public String updateStateOnlyOne(String leaveId){
        int state = 1;
        leaveService.updateStateOnlyOne(leaveId,state);
        return "redirect:/guestBook";
    }
    @RequestMapping("/closeAll")
    public String closeAll(@RequestParam("leaveId")String[] leaveIds){
        int state=1;
        leaveService.updateAll(leaveIds,state);
        return "redirect:/guestBook";
    }
    @RequestMapping("/findConditionLeaveList")
    public  String findConditionLeaveList(@RequestParam("username")String username,Model model){
        List<Leave> list=leaveService.findConditionLeaveList(username);
         model.addAttribute("list",list);
        return "/admin/Guestbook";
    }

    //用户详情页面
    @RequestMapping("/memberShow")
    public  String memberShow(String userId,Model model){

        User user = userService.findUserInfo(userId);
        model.addAttribute("user",user);
        return "/admin/member-show";
    }




}
