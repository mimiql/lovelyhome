package org.csu.lovelyhome.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.csu.lovelyhome.common.util.UploadUtil;
import org.csu.lovelyhome.entity.*;
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
@RequestMapping("/lovelyhome/user")
@CrossOrigin
public class UserController {

    private static final String DESTINATION = "src/main/resources/static/images/headImage/";

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

    @PutMapping("/{user_id}")
    public String userModification(@PathVariable("user_id") int user_id, @RequestParam("file") MultipartFile file){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_id", user_id);
        User user = userService.getOne(queryWrapper);
        if(!file.isEmpty()){
            UploadUtil.save(file, DESTINATION + user_id + "/");
            UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<User>().set("head_image", DESTINATION + user_id + file.getOriginalFilename());
            userService.update(user, userUpdateWrapper);
        }

        return "success";
//        return userService.getOne(queryWrapper);
    }

    @GetMapping("/{user_id}/collection/buildings")
    public PageInfo<Building> collectionBuildings(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Building> buildingList = userService.getCollectionBuildingByUserId(user_id);
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/collection/huxings")
    public PageInfo<Huxing> collectionHuxings(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Huxing> huxingList = userService.getCollectionHuxingByUserId(user_id);
        PageInfo<Huxing> pageInfo = new PageInfo<Huxing>(huxingList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/collection/houses")
    public PageInfo<House> collectionHouses(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<House> houseList = userService.getCollectionHouseByUserId(user_id);
        PageInfo<House> pageInfo = new PageInfo<House>(houseList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/collection/decorations")
    public PageInfo<Decorate> collectionDecorations(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Decorate> decorateList = userService.getCollectionDecorateByUserId(user_id);
        PageInfo<Decorate> pageInfo = new PageInfo<Decorate>(decorateList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/houses")
    public PageInfo<House> houses(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<House> houseList = userService.getPublishHousesByUserId(user_id);
        PageInfo<House> pageInfo = new PageInfo<House>(houseList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/housesType")
    public PageInfo<House> statusHouses(@PathVariable("user_id") int user_id, @RequestParam("status") int status, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<House> houseList = userService.getHousesByUserIdAndStatus(user_id, status);
        PageInfo<House> pageInfo = new PageInfo<House>(houseList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/questions")
    public PageInfo<Question> questions(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Question> questionList = userService.getQuestionByUserId(user_id);
        PageInfo<Question> pageInfo = new PageInfo<Question>(questionList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/questions/responses")
    public PageInfo<Question> questionResponses(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Question> responseList = userService.getQuestionResponsesByUserId(user_id);
        PageInfo<Question> pageInfo = new PageInfo<Question>(responseList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/comments")
    public Comment comment(@PathVariable("user_id") int user_id){
        return userService.getCommentByUserId(user_id);
    }

    @GetMapping("/{user_id}/commentBuilding")
    public PageInfo<CommentBuilding> commentBuilding(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<CommentBuilding> commentBuildingList = userService.getCommentBuildingByUserId(user_id);
        PageInfo<CommentBuilding> pageInfo = new PageInfo<CommentBuilding>(commentBuildingList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/commentDecorate")
    public PageInfo<CommentDecorate> commentDecorate(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<CommentDecorate> commentDecorateList = userService.getCommentDecorateByUserId(user_id);
        PageInfo<CommentDecorate> pageInfo = new PageInfo<CommentDecorate>(commentDecorateList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/browsing")
    public PageInfo<Building> browsing(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Building> buildingList = userService.getBrowsingBuildingByUserId(user_id);
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/browsing/huxing")
    public PageInfo<Huxing> browsingHuxing(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Huxing> huxingList = userService.getBrowsingHuxingByUserId(user_id);
        PageInfo<Huxing> pageInfo = new PageInfo<Huxing>(huxingList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/browsing/house")
    public PageInfo<House> browsingHouse(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<House> houseList = userService.getBrowsingHouseByUserId(user_id);
        PageInfo<House> pageInfo = new PageInfo<House>(houseList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/browsing/decoration")
    public PageInfo<Decorate> browsingDecorate(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Decorate> decorateList = userService.getBrowsingDecorateByUserId(user_id);
        PageInfo<Decorate> pageInfo = new PageInfo<Decorate>(decorateList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/browsing/building")
    public PageInfo<Building> browsingBuilding(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "3",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Building> buildingList = userService.getBrowsingBuildingByUserId(user_id);
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);
        return pageInfo;
    }

}

