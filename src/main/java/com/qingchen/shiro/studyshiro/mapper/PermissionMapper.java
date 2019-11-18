package com.qingchen.shiro.studyshiro.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Created by QingChen on 2019/11/14 16:52
 */
public interface PermissionMapper {

    /**
     * 根据角色ID，查询权限
     * @param roleIds
     * @return
     */
    List<String> findByRoleId(@Param("roleIds") List<Integer> roleIds);

}
