package com.eatery.cd.controller;

import com.eatery.cd.pojo.Evaluate;
import com.eatery.cd.pojo.Order;
import com.eatery.cd.pojo.User;
import com.eatery.cd.service.EvaService;
import com.eatery.cd.service.OrderService;
import com.eatery.cd.service.UserService;
import com.eatery.cd.tools.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 后台订单列表
 */
@Controller
public class Back_OrderController {

    @Autowired()
    private OrderService orderService;
    @Autowired()
    private UserService userService;
    @Autowired
    private EvaService evaService;

    /**
     * 跳转到订单详情页面--并展示订单详情（配餐订单管理）
     * @return
     */
    @RequestMapping("/orderForm")
    public String orderForm(Model model) {
        List<Order> orderList = orderService.findOrderAll();
        model.addAttribute("orderList",orderList);
        return "/admin/Orderform";
    }

    @RequestMapping("/deleteOrder")
    public String deleteOrder(String orderId){
        orderService.deleteOrder(orderId);
        return "redirect:/orderForm";
    }
    /**
     * 修改后台订单状态
     * @param orderId
     * @param orderStatus
     * @return
     */
    @RequestMapping("/updateAdminOrderStatus")
    public String updateAdminOrderStatus(String orderId,String orderStatus){
        orderService.updateOrderStatus(orderStatus,orderId);
        return "redirect:/orderForm";
    }

    /**
     * 跳转到回复评论界面
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping("/toAnsEva")
    public String toAnsEva(String orderId,Model model){
        Evaluate evaluate = evaService.findEvaListByOrderId(orderId);
        model.addAttribute("evaluate",evaluate);
        return "/admin/adminAnsEva";
    }
    @RequestMapping("/ansEva")
    public String ansEva(String orderId,String ansContent,String orderStatus){
        evaService.saveAnsEva(orderId,ansContent);
        orderService.updateOrderStatus(orderStatus,orderId);
        return "redirect:/orderForm";
    }
    //跳转到订单详情页面
    @RequestMapping("/orderDetailed")
    public String orderDetailed(String orderId,Model model){
        Order order =  orderService.findListByOrderId(orderId);
        User user = userService.findUserInfo(order.getUserId());

        order.setUser(user);

        model.addAttribute("order",order);

        return "/admin/order_detailed";
    }



    //根据订单id查询对应的数据
    @RequestMapping("/searchOrderList")
    public String searchOrderList(String orderId,Model model){
        Order order =  orderService.findListByOrderId(orderId);
        if (order == null) {
            return "redirect:/orderForm" ;
        }
        List<Order> orderList = new ArrayList<Order>();
        //将order封装进一个集合，方便页面中遍历
        orderList.add(order);
        model.addAttribute("orderList",orderList);
        return "/admin/Orderform";
    }


    //订单打印
   /* @RequestMapping("/stamp")
    public String toStamp(){*/
        /*List<Order> orderList = orderService.findOrderAll();
        //int count = orderService.findOrderTotal();
        try {
            VerifyCode.writeToDisk(orderList,4,"订单1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/orderForm";*/

/*
        final String filePath="D:\\";
        final String fileName="123.csv";
        StringBuffer conValue = new StringBuffer();
        conValue.append("123,234,567\r\n");
        try {
            FileWriter writer = new FileWriter(filePath+fileName);
            writer.write(conValue.toString());
            writer.close();		}
        catch (IOException e)
        {		e.printStackTrace();		}

        return "redirect:/orderForm";
    }

*/


}
