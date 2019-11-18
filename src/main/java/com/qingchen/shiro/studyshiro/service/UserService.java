package com.qingchen.shiro.studyshiro.service;

import com.qingchen.shiro.studyshiro.entity.User;

/**
 * @author Created by QingChen on 2019/11/14 16:56
 */
public interface UserService {

    /**
     * 根据账户 查询用户信息
     * @param account
     * @return
     */
    User findByAccount(String account);

    int findPermissionVersionById(int id);
}
