package zhuffei.ffei.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zhuffei.ffei.Tool.Return;
import zhuffei.ffei.entity.User;
import zhuffei.ffei.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zhuffei
 * @date 2020.3.4
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    IUserService userService;

    @ResponseBody
    @RequestMapping("register")
    public Map register(HttpServletRequest request) {
        String userName =  request.getParameter("userName");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", userName);
        User user = userService.getOne(queryWrapper);
        if (null != user) {
            return Return.ret("用户名已存在");
        }
        queryWrapper.clear();
        queryWrapper.eq("phone", phone);
        user = userService.getOne(queryWrapper);
        if (null != user) {
            return Return.ret("该手机号已注册");
        }
        user = new User(userName, phone, password);

        if (userService.save(user)) {
            return Return.ok("注册成功",user);
        }
        return Return.ret("服务器异常");
    }

    @ResponseBody
    @RequestMapping("login")
    public Map login(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        User user = userService.getOne(queryWrapper);
        if(null != user&&user.getPwd().equals(password) ){
            System.out.println("用户【"+user.getName()+"】登陆成功");
            return Return.ok("登陆成功",user);
        }else{
            return Return.error("用户名或密码错误");
        }
    }
}
