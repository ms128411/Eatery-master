package com.eatery.cd.service;

import com.eatery.cd.mapper.NoticeMapper;
import com.eatery.cd.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class NoticeServiceImpl implements  NoticeService {
    @Autowired
    NoticeMapper noticeMapper ;

    //前台女
    @Override
    public List<Notice> findAll() {
        return noticeMapper.findAll();
    }
    //后台
    @Override
    public List<Notice> findAll1() {
        return noticeMapper.findAll1();
    }

    @Override
    public void deleteNotices(String[] noticeIds) {
        noticeMapper.deleteNotices(noticeIds);
    }

    @Override
    public void addNotice(String title, String content, int state) {
        Notice notice = new Notice();
        notice.setNoticeId(UUID.randomUUID().toString());
        notice.setTitle(title);
        notice.setContent(content);
        notice.setState(state);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        notice.setNoticetime(sdf.format(date));
        noticeMapper.addNotice(notice);
    }

    @Override
    public void closeNotices(String[] noticeIds) {
        int state = 0;
        noticeMapper.updateState(state,noticeIds);
    }

    @Override
    public void showNotices(String[] noticeIds) {
        int state = 1;
        noticeMapper.updateState(state,noticeIds);
    }

    @Override
    public void updateNotice(String noticeId, String utitle, String ucontent, int ustate) {
        Notice notice = new Notice();
        notice.setState(ustate);
        notice.setContent(ucontent);
        notice.setTitle(utitle );
        notice.setNoticeId(noticeId);
        noticeMapper.updateNotice(notice);
    }
}
