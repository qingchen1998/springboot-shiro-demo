package com.qingchen.shiro.studyshiro.realm;

import com.qingchen.shiro.studyshiro.entity.Role;
import com.qingchen.shiro.studyshiro.entity.User;

import com.qingchen.shiro.studyshiro.service.PermissionService;
import com.qingchen.shiro.studyshiro.service.RoleService;
import com.qingchen.shiro.studyshiro.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.Resource;
import java.util.*;

/**
 * @author Created by QingChen on 2019/11/14 17:13
 */
public class AuthRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    private static final int ACCOUNT_STATUS = 0;

    @Autowired
    private SessionDAO sessionDAO;


    /**
     * 认证(登陆)
     *
     * @param authToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        System.out.println("into doGetAuthenticationInfo");

        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        // 获取用户输入的用户名和密码
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        // 根据用户名查询用户信息
        User user = userService.findByAccount(userName);
        //账号不存在
        if (user == null) {
            throw new IncorrectCredentialsException();
        }
        //账号或密码错误
        if (!user.getPassword().equals(password)) {
            throw new IncorrectCredentialsException();
        }
        //账号禁用
        if (user.getStatus() == ACCOUNT_STATUS) {
            throw new DisabledAccountException();
        }
        //互斥登陆
        if (userName.equals(user.getAccount())&&password.equals(user.getPassword())){
            //获取所有已登陆用户的session列表
            Collection<Session> sessions = sessionDAO.getActiveSessions();
            if(!sessions.isEmpty()){
                for (Session session:sessions){
                    User SessionUser = (User)session.getAttribute("USER_SESSION");
                    if (SessionUser!=null){
                        if(userName.equals(SessionUser.getAccount())){
                            session.setTimeout(0);
                        }
                    }
                }
            }
        }
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("USER_SESSION",user);
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 根据用户Id查询角色信息
        List<Role> roleList = roleService.findRoleByUserId(user.getId());
        Set<String> roleSet = new HashSet<>();
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleSet.add(role.getRole());
            roleIds.add(role.getId());
        }
        // 放入角色信息
        authorizationInfo.setRoles(roleSet);
        // 放入权限信息
        List<String> permissionList = permissionService.findByRoleId(roleIds);
        authorizationInfo.setStringPermissions(new HashSet<>(permissionList));

        return authorizationInfo;
    }
}
