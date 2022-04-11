package com.eatery.cd.service;

import com.eatery.cd.mapper.RoleMapper;
import com.eatery.cd.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRoleList() {
        return roleMapper.findRoleList();
    }
}
