package com.qingchen.shiro.studyshiro.mapper;

import com.qingchen.shiro.studyshiro.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Created by QingChen on 2019/11/14 16:36
 */
public interface UserMapper {
    /**
     * 根据账户查询用户信息(username可以任意改，account账户唯一)
     * @param account
     * @return
     */
    User findByAccount(@Param("account") String account);

    int findPermissionVersionById(@Param("id") int id);
}
