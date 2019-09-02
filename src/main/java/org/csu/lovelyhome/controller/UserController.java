package org.csu.lovelyhome.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.lovelyhome.common.util.UploadUtil;
import org.csu.lovelyhome.entity.User;
import org.csu.lovelyhome.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private static final String destination = "images/headImage/";

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("/login")
    public String login(@RequestBody JSONObject account){
        String phone = (String) account.get("phone");
        String password = (String) account.get("password");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("phone", phone);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            return "用户名不存在";
        }else{
            if(password.equals(user.getPassword())){
                String token = JWT.create().withAudience(phone)
                        .sign(Algorithm.HMAC256(password));
                stringRedisTemplate.opsForValue().set(token, token);
                return "登录成功！";
            }

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
            if(imageCheckCode.equals(session.getAttribute("RANDOMCODEKEY"))) {
//                password = new BCryptPasswordEncoder().encode(password);
                User user = new User();
                user.setPassword(password);
                user.setPhone(phone);
                userService.save(user);
                return "注册成功！";
            }
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

    @PostMapping("/{user_id}")
    public String userModification(@PathVariable("user_id") int user_id, @RequestParam("file") MultipartFile file){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_id", user_id);
        User user = userService.getOne(queryWrapper);
        if(!file.isEmpty()){
            UploadUtil.save(file, destination + user_id + "/");
            user.setHeadImage(destination + user_id + file.getOriginalFilename());
        }

        return "success";
//        return userService.getOne(queryWrapper);

    }
}

