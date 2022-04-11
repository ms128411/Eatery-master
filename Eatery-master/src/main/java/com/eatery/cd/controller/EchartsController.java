package com.eatery.cd.controller;
import com.eatery.cd.pojo.OrderAddCount;
import com.eatery.cd.pojo.Product;
import com.eatery.cd.service.EchartsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EchartsController {
    @Autowired
    private EchartsService echartsService;
    @RequestMapping("/buyCount")
    @ResponseBody
    public Object getBuyCount() throws JsonProcessingException {
        List<Product> proList= echartsService.findAll();
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(proList);
       System.out.println(json);
        return json;
    }
    @RequestMapping("/buyAddr")
    @ResponseBody
    public Object getBuyAddr() throws JsonProcessingException {
        List<OrderAddCount> ordList=echartsService.findAllAddr();
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(ordList);
//        System.out.println(json);
        return json;
    }
}
