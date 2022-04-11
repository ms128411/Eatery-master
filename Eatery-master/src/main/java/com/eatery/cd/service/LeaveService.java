package com.eatery.cd.service;

import com.eatery.cd.pojo.Leave;
import com.eatery.cd.pojo.User;

import java.util.List;

public interface LeaveService {
    public List<Leave> findAllLeaveList();
    //删除一个
    public  void deleteById(String leaveId);
    //删除所有
    public  void deleteAll(String[] leaveIds);

    //修改所有的状态---多参数封装成map
    public void updateAll(String[] leaveIds, int state);

    //模糊查询，按条件查找
    public List<Leave> findConditionLeaveList(String username);

    /**
     * 更新一条留言状态
     * @param leaveId
     * @param state
     */
    void updateStateOnlyOne(String leaveId, int state);

    /**
     * 前台添加留言
     * @param content
     * @param user
     * @param state
     */
    void createLeave(String content, User user, int state);
}
