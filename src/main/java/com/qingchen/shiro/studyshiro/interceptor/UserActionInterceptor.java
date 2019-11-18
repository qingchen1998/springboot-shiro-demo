package com.qingchen.shiro.studyshiro.interceptor;

import com.qingchen.shiro.studyshiro.entity.User;
import com.qingchen.shiro.studyshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Created by QingChen on 2019/11/18 10:58
 */
public class UserActionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Session session = null;
        User user = null;
        int permissionVersion = 0;
        try {
            //1.获取当前用户的session
            session = SecurityUtils.getSubject().getSession();
            user = (User) session.getAttribute("USER_SESSION");
            //2.查询数据库中，该用户的权限版本号
            permissionVersion = userService.findPermissionVersionById(user.getId());
            //3.对比session中的版本号与数据库中查出来的版本号
            if (user.getPermissionVersion() != permissionVersion) {
                SecurityUtils.getSubject().logout();
                request.getRequestDispatcher("/login").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
