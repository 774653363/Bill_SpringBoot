package ek.zhou.springboot.controller;

import ek.zhou.springboot.entities.Provider;
import ek.zhou.springboot.entities.User;
import ek.zhou.springboot.mapper.UserMapper;
import ek.zhou.springboot.service.ProviderService;
import ek.zhou.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 */
@Controller
public class UserController {
    //获取日志对象
    Logger logger = LoggerFactory.getLogger(getClass());
    //注入service
    @Autowired
    private UserService userService;
    /**
     * 查询所有用户列表并响应给列表页面
     * @return
     */
    @GetMapping("/users")
    public String users(User user, Map<String,Object> map){

        //使用日志记录
        logger.info("查询用户列表...");
        //调用dao查询所有数据
        List<User> users = userService.getUsers(user);
        //存放数据到map中响应给页面
        map.put("users",users);

        map.put("username",user.getUsername());
        return "user/list";
    }

    /**
     * 根据id查询用户信息
     * 使用type区分跳转页面是查询页面还是更新页面
     * @param id
     * @param map
     * @param type
     * @return
     */
    @GetMapping("/user/{id}")
    public String view(@PathVariable Integer id,Map<String,Object> map,@RequestParam(defaultValue = "view") String type){
        //使用日志记录信息
        logger.info("查询用户详情");
        //调用dao查询数据
        User user = userService.getUserById(id);
        //将数据存入map
        map.put("user",user);
        //跳转到页面
        return "user/"+type;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/user")
    public String update(User user){
        //使用日志记录信息
        logger.info("更新用户");
        //调用dao更新数据
        userService.updateUser(user);
        //跳转回列表页面
        return "redirect:/users";
    }

    /**
     * 跳转到用户添加页面
     * @return
     */
    @GetMapping("/user")
    public String addUI(){
        //使用日志记录信息
        logger.info("跳转到添加用户页面");
        return "user/add";
    }
    /**
     * 添加用户
     */
    @PostMapping("/user")
    public String add(User user){
        //使用日志记录信息
        logger.info("添加用户");
        //调用dao添加数据
        userService.addUser(user);
        //跳转回列表页面
        return "redirect:/users";
    }

    @DeleteMapping("/user/{id}")
    public String add(@PathVariable Integer id){
        //使用日志记录信息
        logger.info("删除用户");
        //调用dao添加数据
        userService.deleteUserById(id);
        //跳转回列表页面
        return "redirect:/users";
    }

    /**
     * 跳转到修改密码页面
     * @return
     */
    @GetMapping("/user/pwd")
    public String toPwdUpdateUI(){
        return "main/password";
    }

    /**
     * 校验用户密码
     * @return
     */
    @ResponseBody
    @GetMapping("/user/pwd/{password}")
    public Boolean checkPwd(@PathVariable String password, HttpServletRequest request){

        //获取session中的用户信息
        User user = (User) request.getSession().getAttribute("user");
        //判断用户是否为空
        if (user != null) {
            //判断密码是否一致
            if(user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    /**
     * 更新用户密码
     * @return
     */
    @PostMapping("/user/pwd")
    public String updatePwd(String password, HttpServletRequest request){
        //获取session中的用户信息
        User user = (User) request.getSession().getAttribute("user");
        //判断用户是否为空
        if (user != null) {
            //设置为新的密码
            user.setPassword(password);
            //调用service更新用户信息
            userService.updateUser(user);
        }
        //跳转到退出url
        return "redirect:/logout";
    }


}
