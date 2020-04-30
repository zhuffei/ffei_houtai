package zhuffei.ffei.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zhuffei.ffei.Tool.Return;
import zhuffei.ffei.entity.Goods;
import zhuffei.ffei.entity.User;
import zhuffei.ffei.service.IRelationService;
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

    @Autowired
    IRelationService relationService;

    @ResponseBody
    @RequestMapping("register")
    public Map register(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", user.getName());
        User user1 = userService.getOne(queryWrapper);
        if (null != user1) {
            return Return.ret("用户名已存在");
        }
        queryWrapper.clear();
        queryWrapper.eq("phone", user.getPhone());
        user1 = userService.getOne(queryWrapper);
        if (null != user1) {
            return Return.ret("该手机号已注册");
        }
        if (userService.save(user)) {
            return Return.ok("注册成功", user);
        }
        return Return.ret("服务器异常");
    }

    @ResponseBody
    @RequestMapping("login")
    public Map login(@RequestBody User param) {
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", param.getPhone());
            User user = userService.getOne(queryWrapper);
            if (null != user && user.getPwd().equals(param.getPwd())) {
                System.out.println("用户【" + user.getName() + "】登陆成功");
                relationService.login(user.getId());
                return Return.ok("登陆成功", user);
            } else {
                return Return.error("用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Return.error("服务器异常");
        }
    }

    @ResponseBody
    @RequestMapping("searchUser")
    public Map searchUser(@RequestBody Map<String, Object> map) {
        try {
            Integer uid = (Integer) map.get("uid");
            String param = (String) map.get("param");
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("id", uid).like("name", param);
            List<User> list = userService.list(queryWrapper);
            List<User> focus = relationService.listFocus(uid);
            List<Integer> focusId = new ArrayList<>();
            for (User user : focus) {
                focusId.add(user.getId());
            }
            for (User user : list) {
                user.setIsFocused(focusId.contains(user.getId()) ? 1 : 0);
            }
            return Return.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Return.error();
        }
    }

    @ResponseBody
    @RequestMapping("getUserInfo")
    public Map getUserInfo(@RequestBody Map<String, Integer> map) {
        try {
            Integer uid = map.get("uid");
            Integer searcher = map.get("searcher");
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", uid);
            User user = userService.getOne(queryWrapper);
            user.setIsFocused(relationService.checkFocus(searcher, uid));
            return Return.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Return.error();
        }
    }

    /**
     * 用户更换头像
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @ResponseBody
    @RequestMapping("changeAvator")
    public Map changeAvator(HttpServletRequest request, HttpServletResponse response) {
        String fileName = null;
        Map<String, String> map = new HashMap<>();
        try {
            request.setCharacterEncoding("utf-8"); //设置编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取文件需要上传到的路径
//        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\avator";
        String path = "avator";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        factory.setRepository(new File(path));
        //设置 缓存的大小
        factory.setSizeThreshold(1024 * 1024);
        //文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //可以上传多个文件
            List<FileItem> list = (List<FileItem>) upload.parseRequest(new ServletRequestContext(request));
            for (FileItem item : list) {
                //获取属性名字
                String name = item.getFieldName();
                //如果获取的 表单信息是普通的 文本 信息
                if (item.isFormField()) {
                    //获取用户具体输入的字符串,因为表单提交过来的是 字符串类型的
                    String value = item.getString();
                    map.put(name, value);
                } else {
                    //获取路径名
                    String value = item.getName();
                    //索引到最后一个反斜杠
                    int start = value.lastIndexOf(".");
                    //截取 上传文件的 后缀
                    String fileType = value.substring(start);
                    request.setAttribute(name, fileType);
                    fileName = UUID.randomUUID() + fileType;
                    //写到磁盘上
                    item.write(new File(path, fileName));//第三方提供的
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败");
            return Return.error();
        }
        User user = new User();
        user.setId(Integer.valueOf(map.get("uid")));
        if (fileName == null || fileName.isEmpty()) {
            return Return.error();
        } else {
            user.setAvator(fileName);
        }
        if (userService.updateById(user)) {
            return Return.ok(fileName);
        } else {
            return Return.error();
        }
    }

    @ResponseBody
    @RequestMapping("getUidByAccid")
    public Map getUserByAccid(@RequestBody Map<String, Object> map) {
        try {
            String accid = (String) map.get("accid");
            System.out.println(accid);
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("accid", accid);
            User user = userService.getOne(queryWrapper);
            System.out.println(user.toString());
            return Return.ok(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return Return.error();
        }
    }
}
