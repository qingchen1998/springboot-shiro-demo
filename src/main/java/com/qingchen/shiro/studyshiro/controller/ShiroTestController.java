package com.qingchen.shiro.studyshiro.controller;

import com.qingchen.shiro.studyshiro.entity.User;
import com.qingchen.shiro.studyshiro.enums.StatusEnums;
import com.qingchen.shiro.studyshiro.vo.ResponseCode;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;

import org.apache.shiro.subject.Subject;

import org.springframework.web.bind.annotation.*;

import static org.apache.shiro.SecurityUtils.getSubject;

/**
 * @author Created by QingChen on 2019/11/14 17:16
 */
@RestController
public class ShiroTestController {
    /**
     * 登录请求
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseCode login(@RequestBody User user) {
        Subject userSubject = getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
        try {
            // 登录验证
            userSubject.login(token);
            return ResponseCode.success();
        } catch (DisabledAccountException e) {
            return ResponseCode.error(StatusEnums.ACCOUNT_IS_DISABLED);
        } catch (IncorrectCredentialsException e) {
            return ResponseCode.error(StatusEnums.INCORRECT_CREDENTIALS);
        } catch (Throwable e) {
            e.printStackTrace();
            return ResponseCode.error(StatusEnums.SYSTEM_ERROR);
        }
    }

    /**
     * 登陆页面的请求
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "显示登陆页面";
    }

    /**
     * 验证是否登陆
     * @return
     */
    @GetMapping("/auth")
    public String auth() {
        return "已成功登录";
    }

    /**
     * 测试当前角色是否是admin
     * @return
     */
    @GetMapping("/role")
    @RequiresRoles({"admin"})
    public String role() {
        return "你是admin角色";
    }

    /**
     * 测试权限
     * @return
     */
    @GetMapping("/permission")
    @RequiresPermissions(value = {"add", "update"}, logical = Logical.AND)
    public String permission() {
        return "你有Add和Update权限";
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    public ResponseCode logout() {
        getSubject().logout();
        return ResponseCode.success();
    }
}
