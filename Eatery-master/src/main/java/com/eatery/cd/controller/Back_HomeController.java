package com.eatery.cd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Back_HomeController {


    @RequestMapping("/backIndex")
    public String backIndex() {
        //跳转回用户列表页面
        return "/admin/index";
    }
    @RequestMapping("/home")
    public String home() {
        //跳转回用户列表页面
        return "/admin/home";
    }

/*    @RequestMapping("/brandManage")
    public String brandManage() {
        return "/admin/Brand_Manage";
    }*/



/*    @RequestMapping("/categoryManage")
    public String categoryManage() {
        return "/admin/Category_Manage";
    }*/
/*    @RequestMapping("toProduct_category_add")
    public String toProduct_category_add(){
        return "/admin/product-category-add";
    }*/




    /*订单信息*/
    @RequestMapping("/transaction")
    public String transaction() {
        return "/admin/transaction";
    }


/*    @RequestMapping("/amounts")
    public String amounts() {
        return "/admin/Amounts";
    }*/

    /*订单处理*/
    @RequestMapping("/orderHandling")
    public String orderHandling() {
        return "/admin/Order_handling";
    }

    /*支付管理*/
    @RequestMapping("/coverManagement")
    public String coverManagement() {
        return "/admin/Cover_management";
    }
/*    @RequestMapping("/userList")
    public String userList() {
        return "/admin/user_list";
    }*/
  /*  @RequestMapping("/memberGrading")
    public String memberGrading() {
        return "/admin/member-Grading";
    }*/
/*    @RequestMapping("/integration")
    public String integration() {
        return "/admin/integration";
    }*/

    /*意见反馈*/
    @RequestMapping("/feedBack")
    public String feedBack() {
        return "/admin/Feedback";
    }
    /*系统设置*/
    @RequestMapping("/systems")
    public String systems() {
        return "/admin/Systems";
    }
    /*  @RequestMapping("/menuManage")
      public String menuManage() {
          return "/admin/test";
      }*/

    /*测试页面*/
    @RequestMapping("/userManage")
    public String userManage() {
        return "/admin/test";
    }
       /*系统设置*/
    @RequestMapping("/systemSet")
    public String systemSet() {
        return "/admin/Systems";
    }

}
