package com.qingchen.shiro.studyshiro.mapper;

import com.qingchen.shiro.studyshiro.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Created by QingChen on 2019/11/14 16:41
 */
public interface RoleMapper {

    /**
     * 根据用户id，查询该用户有哪些角色。比如：我可以是 超级管理员 和 VIP用户
     * @param userId
     * @return
     */
    List<Role> findRoleByUserId(@Param("userId") Integer userId);
}
