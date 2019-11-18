package com.qingchen.shiro.studyshiro.service.impl;

import com.qingchen.shiro.studyshiro.entity.Role;
import com.qingchen.shiro.studyshiro.mapper.RoleMapper;
import com.qingchen.shiro.studyshiro.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Created by QingChen on 2019/11/14 17:00
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRoleByUserId(Integer id) {
        return roleMapper.findRoleByUserId(id);
    }
}
