package com.eatery.cd.service;

import com.eatery.cd.pojo.Notice;

import java.util.List;

/**
 * 实现公告的增删改查
 */
public interface NoticeService {

    /**
     * 查询全部Notice集合
     * @return List<Notice> 公告集合
     */
    //前台
    List<Notice> findAll();

    //后台
    List<Notice> findAll1();

    /**
     * 批量删除公告
     * @param noticeIds id数组
     */
    void deleteNotices(String[] noticeIds);

    /**
     * 添加公告
     * @param title 标题
     * @param content   文本
     * @param state     状态
     */
    void addNotice(String title, String content, int state);

    /**
     * 显示公告
     * @param noticeIds
     */
    void closeNotices(String[] noticeIds);

    /**
     * 关闭公告
     * @param noticeIds
     */
    void showNotices(String[] noticeIds);

    void updateNotice(String noticeId, String utitle, String ucontent, int ustate);
}
