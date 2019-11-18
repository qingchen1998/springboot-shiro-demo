package com.qingchen.shiro.studyshiro.service.impl;

import com.qingchen.shiro.studyshiro.entity.User;
import com.qingchen.shiro.studyshiro.mapper.UserMapper;
import com.qingchen.shiro.studyshiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Created by QingChen on 2019/11/14 16:58
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByAccount(String account) {
        return userMapper.findByAccount(account);
    }

    @Override
    public int findPermissionVersionById(int id) {
        return userMapper.findPermissionVersionById(id);
    }
}
