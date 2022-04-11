package com.eatery.cd.controller;

import com.eatery.cd.pojo.Product;
import com.eatery.cd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DetailController {

    @Autowired
    private ProductService productService;

    //跳转到商品详情页面
    @RequestMapping("/toDetail")
    public String toDetail(String productId, Model model){

        //根据商品id查询商品信息
        Product product = productService.findProductById(productId);
        //查询热门商品信息列表(商品销量大于100)
        List<Product> HotProductList = productService.findHotProductList();
        model.addAttribute("product",product);
        model.addAttribute("HotProductList",HotProductList);
        return "/pages/list/detailsp";
    }
}
