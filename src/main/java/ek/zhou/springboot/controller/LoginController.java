package ek.zhou.springboot.controller;

import ek.zhou.springboot.entities.User;
import ek.zhou.springboot.mapper.UserMapper;
import ek.zhou.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户登录,退出控制器
 */
@Controller
public class LoginController {
    //注入mapper
    @Autowired
    private UserService userService;
    /**
     * 登录方法
     * @param username
     * @param password
     * @param map
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String password, Map<String,Object> map, HttpServletRequest request){
        //调用service查询数据
        User user = userService.getUserByUsername(username);

        //登录成功
        if(user!=null&&user.getPassword().equals(password)){
            //将用户信息存入session
            request.getSession().setAttribute("user",user);
            //重定向到首页,需要增加一个视图
            return "redirect:/main.html";
        }
        //登录失败
        //存储错误信息
        map.put("msg","用户名或密码错误!");
        //回显用户名
        map.put("username",username);
        //跳转到登录页面
        return "main/login";
    }

    /**
     * 用户退出方法
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,Map<String,Object> map){
        //清除session中的用户信息
        request.getSession().removeAttribute("user");
        //失效session
        request.getSession().invalidate();;
        return "redirect:/index.html";
    }
}
