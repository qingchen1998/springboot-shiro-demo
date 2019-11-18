package com.qingchen.shiro.studyshiro.service.impl;

import com.qingchen.shiro.studyshiro.mapper.PermissionMapper;
import com.qingchen.shiro.studyshiro.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Created by QingChen on 2019/11/14 17:02
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<String> findByRoleId(List<Integer> roleIds) {
        return permissionMapper.findByRoleId(roleIds);
    }
}
