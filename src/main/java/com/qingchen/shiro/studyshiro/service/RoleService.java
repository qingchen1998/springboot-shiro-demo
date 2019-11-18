package com.qingchen.shiro.studyshiro.service;

import com.qingchen.shiro.studyshiro.entity.Role;

import java.util.List;

/**
 * @author Created by QingChen on 2019/11/14 16:59
 */
public interface RoleService {

    /**
     * 根据用户ID 查询角色信息
     * @param id
     * @return
     */
    List<Role> findRoleByUserId(Integer id);
}
