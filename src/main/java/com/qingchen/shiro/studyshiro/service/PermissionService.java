package com.qingchen.shiro.studyshiro.service;

import java.util.List;

/**
 * @author Created by QingChen on 2019/11/14 17:01
 */
public interface PermissionService {

    /**
     * 根据角色id 查询对应的权限
     * @param roleIds
     * @return
     */
    List<String> findByRoleId(List<Integer> roleIds);
}
