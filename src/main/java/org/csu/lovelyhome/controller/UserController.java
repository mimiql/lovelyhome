package org.csu.lovelyhome.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import org.csu.lovelyhome.entity.User;
import org.csu.lovelyhome.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public String login(@RequestBody JSONObject account){
        String phone = (String) account.get("phone");
        String password = (String) account.get("password");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("phone", phone);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            return "用户名不存在";
        }else{
            if(password.equals(user.getPassword()))
                return "登录成功！";
            else{
                return "密码错误！";
            }
        }
    }

    @PostMapping("/newUser")
    public String newUser(@RequestBody JSONObject account, HttpSession session){
        String imageCheckCode = (String) account.get("imageCheckCode");
        String phone = (String) account.get("phone");
        String password = (String) account.get("password");
        List<String> phoneList = userService.getAllPhoneList();
        if(phoneList.contains(phone)){
            return "该手机号已注册！";
        }else{
            if(imageCheckCode.equals(session.getAttribute("RANDOMCODEKEY")))
                return "注册成功！";
            else{
                return "验证码错误！";
            }
        }
    }

    @GetMapping("/{user_id}")
    public User user(@PathVariable("user_id") int user_id){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_id", user_id);
        return userService.getOne(queryWrapper);
    }
}

