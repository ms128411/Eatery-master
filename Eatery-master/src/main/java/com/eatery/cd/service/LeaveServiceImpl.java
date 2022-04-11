package com.eatery.cd.service;

import com.eatery.cd.mapper.LeaveMapper;
import com.eatery.cd.pojo.Leave;
import com.eatery.cd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Steven on 2017/10/21.
 */
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;

    public List<Leave> findAllLeaveList() {
        return  leaveMapper.findAllLeaveList();
    }

    public  void deleteById(String leaveId){
          leaveMapper.deleteById(leaveId);
    }
    public  void deleteAll(String[] leaveId){
        leaveMapper.deleteAll(leaveId);
    }

    public void updateAll(String[] leaveIds,int state){
        leaveMapper.updateAll(leaveIds,state);
    }
    public List<Leave> findConditionLeaveList(String username){
        return  leaveMapper.findConditionLeaveList(username);
    }

    @Override
    public void updateStateOnlyOne(String leaveId, int state) {
        leaveMapper.updateStateOnlyOne(leaveId,state);
    }

    @Override
    public void createLeave(String content, User user, int state) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Leave leave = new Leave();
        String leaveId = UUID.randomUUID().toString();
        leave.setLeaveId(leaveId);
        leave.setUser(user);
        leave.setContent(content);
        leave.setLeaveDate(sdf.format(date));
        leave.setState(state);
        leaveMapper.createLeave(leave);
    }
}
