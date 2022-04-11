package com.eatery.cd.controller;

import com.eatery.cd.pojo.Notice;
import com.eatery.cd.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 实现公告相关的增删改查
 */
@Controller
public class Back_NoticeController {
    @Autowired
    private NoticeService noticeService;    //注入noticeService
    @RequestMapping("/sortAds")
    public String sortAds(Model model) {   //点击公告管理回显公告
       List<Notice> noticeList = noticeService.findAll1();
        model.addAttribute("noticeList",noticeList);
        return "/admin/Sort_ads";
    }

    @RequestMapping("/deleteNotice")
    public  String deleteNotice(@RequestParam("noticeId") String[] noticeIds){
        noticeService.deleteNotices(noticeIds);
        return "redirect:/sortAds";
    }

    @RequestMapping("/createNotice")
    public  String createNotice(String title,String content,int state){
        noticeService.addNotice(title,content,state);
        return "redirect:/sortAds";
    }

    @RequestMapping("/showNotice")
    public  String showNotice(@RequestParam("noticeId") String[] noticeIds){
        noticeService.showNotices(noticeIds);
        return "redirect:/sortAds";
    }

    @RequestMapping("/closeNotice")
    public  String closeNotice(@RequestParam("noticeId") String[] noticeIds){
        noticeService.closeNotices(noticeIds);
        return "redirect:/sortAds";
    }
    @RequestMapping("/updateNotice")
    public  String updateNotice(String noticeId,String utitle,String ucontent,int ustate){
        noticeService.updateNotice(noticeId,utitle,ucontent,ustate);
        return "redirect:/sortAds";
    }

}
