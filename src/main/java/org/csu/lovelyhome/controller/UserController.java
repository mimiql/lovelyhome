package org.csu.lovelyhome.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.common.util.UploadUtil;
import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
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
public class UserController extends BaseController {

    private static final String DESTINATION = "src/main/resources/static/images/headImage/";

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CollectionServiceImpl collectionService;
    @Autowired
    private CommentBuildingServiceImpl commentBuildingService;
    @Autowired
    private CommentDecorateServiceImpl commentDecorateService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("/login")
    public Response login(@RequestBody JSONObject account){
        String phone = (String) account.get("phone");
        String password = (String) account.get("password");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("phone", phone);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            return fail("用户名不存在");
        }else{
            if(password.equals(user.getPassword())){
                String token = JWT.create().withAudience(phone)
                        .sign(Algorithm.HMAC256(password));
                stringRedisTemplate.opsForValue().set(token, token);
                return success("登录成功！");
            }
            else{
                return success("密码错误！");
            }
        }
    }

    @PostMapping("/newUser")
    public Response newUser(@RequestBody JSONObject account, HttpSession session){
        String imageCheckCode = (String) account.get("imageCheckCode");
        String phone = (String) account.get("phone");
        String password = (String) account.get("password");
        List<String> phoneList = userService.getAllPhoneList();
        if(phoneList.contains(phone)){
            return fail("该手机号已注册！");
        }else{
            if(imageCheckCode.equals(session.getAttribute("RANDOMCODEKEY"))) {
//                password = new BCryptPasswordEncoder().encode(password);
                User user = new User();
                user.setPassword(password);
                user.setPhone(phone);
                userService.save(user);
                return success("注册成功！");
            }
            else{
                return fail("验证码错误！");
            }
        }
    }

    @GetMapping("/{user_id}")
    public User user(@PathVariable("user_id") int user_id){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_id", user_id);
        return userService.getOne(queryWrapper);
    }

    @PutMapping("/{user_id}")
    public Response userModification(@PathVariable("user_id") int user_id, @RequestParam("file") MultipartFile file, User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_id", user_id);
        if(!file.isEmpty()){
            UploadUtil.save(file, DESTINATION + user_id + "/");
            user.setHeadImage("/images/headImage/" + user_id + "/"  + file.getOriginalFilename());
        }
        userService.update(user, queryWrapper);
        return success("success");
    }

    @GetMapping("/{user_id}/collection/buildings")
    public PageInfo<Building> collectionBuildings(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Building> buildingList = userService.getCollectionBuildingByUserId(user_id);
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/collection/huxings")
    public PageInfo<Huxing> collectionHuxings(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Huxing> huxingList = userService.getCollectionHuxingByUserId(user_id);
        PageInfo<Huxing> pageInfo = new PageInfo<Huxing>(huxingList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/collection/houses")
    public PageInfo<House> collectionHouses(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<House> houseList = userService.getCollectionHouseByUserId(user_id);
        PageInfo<House> pageInfo = new PageInfo<House>(houseList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/collection/decorations")
    public PageInfo<Decorate> collectionDecorations(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Decorate> decorateList = userService.getCollectionDecorateByUserId(user_id);
        PageInfo<Decorate> pageInfo = new PageInfo<Decorate>(decorateList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/houses")
    public PageInfo<House> houses(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<House> houseList = userService.getPublishHousesByUserId(user_id);
        PageInfo<House> pageInfo = new PageInfo<House>(houseList);
        return pageInfo;
    }

    @GetMapping("/{user_id}/housesType")
    public PageInfo<House> statusHouses(@PathVariable("user_id") int user_id, @RequestParam("status") int status, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
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

    @PostMapping("/{user_id}/buildingCollection/{buildingId}")
    public void buildingCollection(@PathVariable("user_id") int user_id, @PathVariable("buildingId") int buildingId){
        Collection collection = new Collection();
        collection.setObjectId(buildingId);
        collection.setType(1);
        collection.setUserId(user_id);
        collection.setTime(new Date());

        collectionService.save(collection);
    }

    @PostMapping("/{user_id}/huxingCollection/{huxingId}")
    public void huxingCollection(@PathVariable("user_id") int user_id, @PathVariable("huxingId") int huxingId){
        Collection collection = new Collection();
        collection.setObjectId(huxingId);
        collection.setType(2);
        collection.setUserId(user_id);
        collection.setTime(new Date());

        collectionService.save(collection);
    }

    @PostMapping("/{user_id}/houseCollection/{houseId}")
    public void houseCollection(@PathVariable("user_id") int user_id, @PathVariable("houseId") int houseId){
        Collection collection = new Collection();
        collection.setObjectId(houseId);
        collection.setType(3);
        collection.setUserId(user_id);
        collection.setTime(new Date());

        collectionService.save(collection);
    }

    @PostMapping("/{user_id}/decorationCollection/{decorateId}")
    public void decorationCollection(@PathVariable("user_id") int user_id, @PathVariable("decorateId") int decorateId){
        Collection collection = new Collection();
        collection.setObjectId(decorateId);
        collection.setType(4);
        collection.setUserId(user_id);
        collection.setTime(new Date());

        collectionService.save(collection);
    }

    @PutMapping("/{user_id}/building/commentOrResponseLike/{id}")
    public void buildingCommentOrResponseLike(@PathVariable("user_id") int user_id, @PathVariable("id") int id){
        QueryWrapper<CommentBuilding> commentBuildingQueryWrapper = new QueryWrapper<CommentBuilding>().eq("comment_id", id);
        CommentBuilding commentBuilding = commentBuildingService.getOne(commentBuildingQueryWrapper);
        commentBuilding.setLikeNum(commentBuilding.getLikeNum() + 1);
        commentBuildingService.save(commentBuilding);
    }

    @PutMapping("/{user_id}/decoration/commentOrResponseLike/{id}")
    public void decorationCommentOrResponseLike(@PathVariable("user_id") int user_id, @PathVariable("id") int id){
        QueryWrapper<CommentDecorate> commentDecorateQueryWrapper = new QueryWrapper<CommentDecorate>().eq("comment_id", id);
        CommentDecorate commentDecorate = commentDecorateService.getOne(commentDecorateQueryWrapper);
        commentDecorate.setLikeNum(commentDecorate.getLikeNum() + 1);
        commentDecorateService.save(commentDecorate);
    }

    @PutMapping("/{user_id}/building/questionOrResponseLike/{id}")
    public void buildingQuestionOrResponseLike(@PathVariable("user_id") int user_id, @PathVariable("id") int id){
        QueryWrapper<Question> commentBuildingQueryWrapper = new QueryWrapper<Question>().eq("comment_id", id);
        Question question = questionService.getOne(commentBuildingQueryWrapper);
        question.setLikeNum(question.getLikeNum() + 1);
        questionService.save(question);
    }

    @PutMapping("/{user_id}/decoration/questionOrResponseLike/{id}")
    public void decorationQuestionOrResponseLike(@PathVariable("user_id") int user_id, @PathVariable("id") int id){
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<Question>().eq("comment_id", id);
        Question question = questionService.getOne(questionQueryWrapper);
        question.setLikeNum(question.getLikeNum() + 1);
        questionService.save(question);
    }

    @DeleteMapping("/{user_id}/collection/{id}")
    public void collectionCancel(@PathVariable("user_id") int user_id, @PathVariable("id") int id, @RequestParam("type") int type){
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id).eq("object_id", id).eq("type", type);
        collectionService.remove(queryWrapper);
    }
}

