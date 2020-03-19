package ek.zhou.springboot.interceptor;

import ek.zhou.springboot.entities.User;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义权限拦截器
 * 将没有登录的用户拦截到登录页面
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //在调用目标方法前请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session中的用户信息
        User user = (User)request.getSession().getAttribute("user");
        //如果用户已经登录,放行
        if(user!=null&&!StringUtils.isEmpty(user.getUsername())){
            return true;
        }
        //如果用户还没登录,请求转发到登录页面
        //设置未登录信息
        request.setAttribute("msg","无权限,请登录后访问!");
        //请求转发到登录页面
        request.getRequestDispatcher("/index.html").forward(request,response);
        return false;
    }
}
