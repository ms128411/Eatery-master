package com.eatery.cd.controller;

import com.eatery.cd.pojo.Evaluate;
import com.eatery.cd.pojo.Order;
import com.eatery.cd.pojo.User;
import com.eatery.cd.service.EvaService;
import com.eatery.cd.service.LeaveService;
import com.eatery.cd.service.OrderService;
import com.eatery.cd.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private EvaService evaService;
    @Autowired
    private LeaveService leaveService;
    /**
     *订单查询  马爽
     */
    @RequestMapping("/userOrderList")
    public String userOrderlist(@RequestParam(required=true,defaultValue="1") Integer page, HttpSession session, Model model){
        User users = (User) session.getAttribute("user");
        User user = userService.findUserInfo(users.getUserId());
        //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
        PageHelper.startPage(page, 3);
        List<Order> orderList = orderService.findOrderListByUserId(users.getUserId());
        PageInfo<Order> p=new PageInfo<Order>(orderList);
        model.addAttribute("page", p);
        model.addAttribute("orderList",orderList);
        model.addAttribute("user",user);
        return "/pages/user/user_orderlist";

    }





    /**
     * 显示收货地址 马爽
     * @param model
     * @return
     */
    @RequestMapping("/userAddress")
    public String userAddress(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        User userInfos = userService.findUserInfo(user.getUserId());
        model.addAttribute("userInfos",userInfos);
        return "/pages/user/user_address";
    }
    /**
     *
     *更新收货地址  马爽
     */
    @RequestMapping("/updateAddress")
    public String updateAddress(User user,Model model){
        userService.updateAddress(user);
        return "redirect:/userAddress";
    }

    /**
     * 返回用户中心 马爽
     * @param model 数据
     * @return
     */
    @RequestMapping("userCenter")
    public String userCenter(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "/login";
        }
        User userInfos = userService.findUserInfo(user.getUserId());
        model.addAttribute("userInfos",userInfos);
        return "/pages/user/user_center";
    }

    /**
     * 显示个人中心信息 马爽
     * @param model
     * @return
     */
    @RequestMapping("/userAccount")
    public String userAccount(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "/login";
        }
        User userInfos = userService.findUserInfo(user.getUserId());
        model.addAttribute("userInfos",userInfos);
        return "/pages/user/user_account";
    }

    /**
     * 跳转修改密码界面 马爽
     * @return
     */
    @RequestMapping("/updatePassword")
    public String updatePassword(){
        return "/pages/user/user_password";
    }

    /**
     * 修改密码 马爽
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param comfirmPwd 确认密码
     * @param model 数据
     * @return
     */
    @RequestMapping("/savePassword")
    public String  saveAddress(String oldPwd, String newPwd, String comfirmPwd,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "/login";
        }
        User userInfo = userService.findUserInfo(user.getUserId());
        String dbPwd = userInfo.getUpassword();
        if(StringUtils.isEmpty(oldPwd) && StringUtils.isEmpty(newPwd) && StringUtils.isEmpty(comfirmPwd)){
            //输入为空
            model.addAttribute("errorInfo","输入为空！");
            return "/pages/user/user_password";
        }else if(!dbPwd.equals(oldPwd)){
            //旧密码不对
            model.addAttribute("errorInfo","旧密码不对！");
            return "/pages/user/user_password";
        }else if(!newPwd.equals(comfirmPwd)){
            //两次密码输入不一致
            model.addAttribute("errorInfo","两次输入密码不一致！");
            return "/pages/user/user_password";
        }else{
            //输入合法，进入修改密码
            userService.updatePwd(comfirmPwd);
            return "redirect:/userAccount";
        }
    }

    /**
     * 跳转修改更换手机号界面
     * @return
     */
    @RequestMapping("/updateTel")
    public String updateTel(){
        return "/pages/user/user_telephone";
    }

    /**
     * 更换手机号 马爽
     * @param newTel
     * @return
     */
    @RequestMapping("/saveTelephone")
    public String saveTel(String newTel,Model model,HttpSession session){
        String regexMobile = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "/login";
        }

        if(!newTel.matches(regexMobile)){
            model.addAttribute("msg","格式不对！");
            return "/pages/user/user_telephone";
        }
        userService.updateTel(newTel,user.getUserId());
        model.addAttribute("msg","修改成功");
        return "redirect:/userAccount";
    }

    /**
     * 解绑手机号 马爽
     * @return
     */
    @RequestMapping("/deleteTel")
    public String deleteTel(HttpSession session){
        User user = (User) session.getAttribute("user");
        userService.deleteTel(user.getUserId());
        return "redirect:/userAccount";
    }

    /**
     * 跳转到更换邮箱界面 马爽
     * @return
     */
    @RequestMapping("/updateEmail")
    public String updateEmail(){
        return "/pages/user/user_email";
    }

    /**
     * 更换邮箱 马爽
     * @param newEmail 新邮箱
     * @return
     */
    @RequestMapping("/saveEmail")
    public String saveEmail(String newEmail,Model model,HttpSession session){
        String regexEmail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "/login";
        }
        if (!newEmail.matches(regexEmail)){
            model.addAttribute("msg","格式错误！");
            return "/pages/user/user_email";
        }
        userService.updateEmail(user.getUserId(),newEmail);
        return "redirect:/userAccount";
    }

    /**
     * 修改订单状态
     * @param status
     * @param orderId
     * @return
     */
    @RequestMapping("/updateOrderStatus")
    public String updateOrderStatus(String status,String orderId){
        orderService.updateOrderStatus(status,orderId);
        return "redirect:/userOrderList";
    }

    /**
     * 跳转评价页面
     * @param productName
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping("/toEvaluate")
    public String toEvaluate(String productName,String orderId,Model model,String status){
        model.addAttribute("p",productName);
        model.addAttribute("o",orderId);
        model.addAttribute("status",status);
        return "/pages/user/evaluate";
    }


    /**
     * 发布评论
     * @param orderId
     * @param productName
     * @return
     */
    @RequestMapping("/saveEva")
    public String saveEva(String orderId,String productName,String content,String status){
        evaService.saveEva(orderId,productName,content,status);
        return "redirect:/toEvaList";
    }

    /**
     * 用户评价列表
     * @return
     */
    @RequestMapping("/toEvaList")
    public String toEvaList(@RequestParam(required=true,defaultValue="1") Integer page,Model model){
        PageHelper.startPage(page, 4);
        List<Evaluate> evaList = evaService.findAllEvaList();
        //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
        PageInfo<Evaluate> p=new PageInfo<Evaluate>(evaList);
        model.addAttribute("page", p);
        model.addAttribute("evaList",evaList);
        return "/pages/user/eva_list";
    }

    @RequestMapping("/createLeave")
    public String createLeave(String content,HttpSession session){
        User user = (User)session.getAttribute("user");
        //留言默认状态为0，表示未浏览
        int state = 0;
        leaveService.createLeave(content,user,state);
        return "redirect:/proHome";
    }

    //食品安全常识
    @RequestMapping("/aboutUs")
    public String aboutUs(){
        return "/pages/other/index";
    }

    //用户系统退出
    @RequestMapping("/userLogout")
    public String userLogout(HttpSession httpSession){
        //将user对象从session域中删除
        httpSession.removeAttribute("userSession");
        //页面应该跳转到登陆页面
        return "/login";
    }
}
