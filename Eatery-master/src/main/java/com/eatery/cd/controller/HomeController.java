package com.eatery.cd.controller;

import com.eatery.cd.pojo.Evaluate;
import com.eatery.cd.pojo.Leave;
import com.eatery.cd.pojo.Notice;
import com.eatery.cd.pojo.Product;
import com.eatery.cd.service.EvaService;
import com.eatery.cd.service.LeaveService;
import com.eatery.cd.service.NoticeService;
import com.eatery.cd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ProductService productService;

    @Autowired
    private LeaveService leaveService;
    @Autowired
    private EvaService evaService;
    //跳转到首页
    @RequestMapping(value = {"/","/proHome"})
    public String home(Model model) {

        List<Product> list=productService.findAllProductList();
        List<Notice> noticeList = noticeService.findAll();
        List<Product> productListThree=productService.findThreeProductList();
        List<Evaluate> evaList = evaService.find2Eva();

        //查找菜品分类
        List productList = productService.findAllProductKindNameList();


        //用户留言表
        List<Leave> leaveList = leaveService.findAllLeaveList();

        model.addAttribute("productList",productList);
        //公告
        model.addAttribute("noticeList",noticeList);
        model.addAttribute("list",list);
        model.addAttribute("productListThree",productListThree);
        model.addAttribute("leaveList",leaveList);
        model.addAttribute("evaList",evaList);
        return "/pages/index";
    }


}
