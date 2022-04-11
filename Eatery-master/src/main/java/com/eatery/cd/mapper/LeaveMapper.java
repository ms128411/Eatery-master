package com.eatery.cd.mapper;

import com.eatery.cd.pojo.Leave;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface LeaveMapper {
    //查找所有数据
    public List<Leave> findAllLeaveList();

    //根据id删除
    public void deleteById(String leaveId);

    public  void deleteAll(String[] leaveIds);

    //批量状态修改
    public void updateAll(@Param(value = "leaveIds") String[] leaveIds, @Param(value = "state") int state);

    //按条件查询
    public List<Leave> findConditionLeaveList(@Param("username") String username);

    /**
     * 更新一条留言状态
     * @param leaveId
     * @param state
     */
    void updateStateOnlyOne(@Param("leaveId") String leaveId, @Param("state") int state);

    /**
     * 前台添加留言
     * @param leave
     */
    void createLeave(Leave leave);
}
