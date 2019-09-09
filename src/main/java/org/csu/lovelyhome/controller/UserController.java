package org.csu.lovelyhome.controller;

import com.aliyuncs.ecs.model.v20140526.DescribeImageSharePermissionResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import jdk.nashorn.internal.runtime.regexp.joni.WarnCallback;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.common.constant.Constant;
import org.csu.lovelyhome.common.util.UploadUtil;
import org.csu.lovelyhome.common.util.VerificationUtil;
import org.csu.lovelyhome.config.ConstantConfig;
import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.pojo.param.UserParam;
import org.csu.lovelyhome.service.FileService;
import org.csu.lovelyhome.service.ILogininfoService;
import org.csu.lovelyhome.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.ValidationUtils;
import org.springframework.http.HttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
@Api(value = "用户相关API",description = "用户相关API")
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
    private CommentHouseServiceImpl commentHouseService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ILogininfoService logininfoService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ConstantConfig constantConfig;

    @ApiOperation(value = "用户登录",notes = "用户登录")
    @PostMapping("/login")
    public Response login(@RequestBody @Validated UserParam param){
        String phone = param.getPhone().trim();
        String password = param.getPassword();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("phone", phone);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            return fail("用户名不存在");
        }else{
            if(password.equals(user.getPassword())){
                if(user.getStatus() == Constant.NORMAL_STATUS){
                    String token = JWT.create().withAudience(phone)
                            .sign(Algorithm.HMAC256(password));
//                stringRedisTemplate.opsForValue().set(token, token);
                    //将登陆日志插入数据库
//                    Logininfo logininfo = new Logininfo();
//                    logininfo.setLoginTime(new Date());
//                    logininfo.setUserId(user.getUserId());
//                    logininfoService.save(logininfo);
//                    return success(token);
                    return success(user,"登录成功");
                }else{
                    return fail("对不起，你的账户已经被冻结!");
                }
            }else{
                return fail("密码错误!");
            }
        }
    }

    @ApiOperation(value = "用户注册",notes = "用户注册")
    @PostMapping("/newUser")
    public Response newUser(@RequestBody UserParam param, HttpSession session){
//        String imageCheckCode = (String) account.get("imageCheckCode");
        String phone = param.getPhone();
        String password = param.getPassword();
        String email = param.getEmail();
        List<String> phoneList = userService.getAllPhoneList();
        if(phoneList.contains(phone)){
            return fail("该手机号已注册！");
        }else{
//            if(imageCheckCode.equals(session.getAttribute("RANDOMCODEKEY"))) {
//                password = new BCryptPasswordEncoder().encode(password);
//            }
//            else{
//                return fail("验证码错误！");
//            }
            User user = new User();
            user.setPassword(password);
            user.setPhone(phone);
            userService.save(user);
            return success("注册成功");
        }
    }

    @ApiOperation(value = "修改用户密码")
    @PostMapping("/password/{userId}")
    public Response changePassword(@PathVariable Integer userId,HttpServletRequest request) throws Exception{
        String currentPassword = request.getParameter("currentPassword") ;
        String newPassword = request.getParameter("newPassword");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        User user = userService.getById(userId);
         if(!user.getPassword().equals(currentPassword)){
             return fail("初始密码错误");
         }else{
             user.setPassword(newPassword);
             userService.update(user,wrapper );
             return success("修改成功");
         }
    }

    @ApiOperation(value = "修改用户基本信息")
    @PostMapping("/profile/{userId}")
    public Response changeProfile(@PathVariable int userId,HttpServletRequest request){
        String name = request.getParameter("name");
        String nick = request.getParameter("nick");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        User user = userService.getById(userId);
        if(name != null && !name.isEmpty()){
            user.setName(name);
        }
        if(nick != null && !nick.isEmpty()){
            user.setNick(nick);
        }
        if (phone != null && !phone.isEmpty()){
            user.setPhone(phone);
        }
        if(email != null && !email.isEmpty()){
            user.setEmail(email);
        }
        userService.update(user,wrapper );
        return success("修改成功");
    }

    @ApiOperation(value = "修改用户头像")
    @PostMapping("/headImage/{userId}")
    public Response changeHeadImage(@PathVariable int userId,@RequestParam("picture") MultipartFile file){
        try {
            if(file != null){
                String filename = file.getOriginalFilename();
                if (!"".equals(filename.trim())){
                    System.out.println("file size:" + file.getSize());
                    String uploadUrl = constantConfig.getPREFIX() + fileService.uploadPicture(file);
                    if(uploadUrl != null){
                        User user = userService.getById(userId);
                        user.setHeadImage(uploadUrl);
                        QueryWrapper<User> wrapper = new QueryWrapper<>();
                        wrapper.eq("user_id",userId );
                        userService.update(user,wrapper);
                        return success(uploadUrl,"修改头像成功");
                    }else return fail("修改头像失败");

                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return fail("修改头像失败");
    }

    @ApiOperation(value = "修改用户Socials信息")
    @PostMapping("/socials/{userId}")
    public Response changeSocials(@PathVariable int userId,HttpServletRequest request){
        String wechatNum = request.getParameter("wechatNum");
        String qq_num = request.getParameter("QQ_num");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        User user = userService.getById(userId);
        if(wechatNum != null && !wechatNum.isEmpty()){
            user.setWechatNum(wechatNum);
        }
        if(qq_num != null && !qq_num.isEmpty()){
            user.setQqNum(qq_num);
        }
        if (sex != null && !sex.isEmpty()){
            user.setSex(sex);
        }
        if(address != null && !address.isEmpty()){
            user.setAddress(address);
        }
        userService.update(user,wrapper );
        return success("修改成功");
    }


    @ApiOperation(value = "根据用户Id获取用户信息",notes = "根据用户Id获取用户信息")
    @GetMapping("/{user_id}")
    public Response user(@PathVariable("user_id") int user_id){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_id", user_id);
        return success(userService.getOne(queryWrapper));
    }

    @ApiOperation(value = "获取所有用户信息",notes = "获取所有用户信息")
    @GetMapping("/all")
    public List<User> users(){
        return userService.list();
    }

    @ApiOperation(value = "修改用户信息",notes = "修改用户信息")
    @PutMapping("/{user_id}")
    public Response userModification(@PathVariable("user_id") int user_id, @RequestParam("file") MultipartFile file, User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_id", user_id);

        if(user.getBrithdate() != null && !VerificationUtil.isDate(user.getBrithdate().toString())){
            return fail("生日输入错误！");
        }
        if(user.getEmail() != null && !VerificationUtil.isEmail(user.getEmail())){
            return fail("输入邮箱错误！");
        }
        if(user.getPhone() != null && !VerificationUtil.isMobileExact(user.getPhone())){
            return fail("输入的手机号错误！");
        }
        if(user.getIdCard() != null && !VerificationUtil.isIdCard(user.getIdCard())) {
            return fail("输入的身份证号错误！");
        }

        if(!file.isEmpty()){
            UploadUtil.save(file, DESTINATION + user_id + "/");
            user.setHeadImage("/images/headImage/" + user_id + "/"  + file.getOriginalFilename());
        }
        userService.update(user, queryWrapper);
        return success("success");
    }

    @ApiOperation(value = "获取用户收藏楼盘",notes = "根据用户id获取用户所收藏楼盘")
    @GetMapping("/{user_id}/collection/buildings")
    public PageInfo<Building> collectionBuildings(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Building> buildingList = userService.getCollectionBuildingByUserId(user_id);
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户收藏户型",notes = "根据用户id获取用户所收藏户型")
    @GetMapping("/{user_id}/collection/huxings")
    public PageInfo<Huxing> collectionHuxings(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Huxing> huxingList = userService.getCollectionHuxingByUserId(user_id);
        PageInfo<Huxing> pageInfo = new PageInfo<Huxing>(huxingList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户收藏出租房",notes = "根据用户id获取用户所收藏出租房")
    @GetMapping("/{user_id}/collection/houses")
    public PageInfo<House> collectionHouses(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<House> houseList = userService.getCollectionHouseByUserId(user_id);
        PageInfo<House> pageInfo = new PageInfo<House>(houseList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户收藏装修方案",notes = "根据用户id获取用户所收藏装修方案")
    @GetMapping("/{user_id}/collection/decorations")
    public PageInfo<Decorate> collectionDecorations(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Decorate> decorateList = userService.getCollectionDecorateByUserId(user_id);
        PageInfo<Decorate> pageInfo = new PageInfo<Decorate>(decorateList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户已上市出租房",notes = "根据用户id获取用户已上市出租房")
    @GetMapping("/{user_id}/houses")
    public List<House> houses(@PathVariable("user_id") int user_id){
        return userService.getPublishHousesByUserId(user_id);
    }

    @ApiOperation(value = "获取用户出租房",notes = "根据用户id和出租房状态获取用户出租房")
    @GetMapping("/{user_id}/housesType")
    public PageInfo<House> statusHouses(@PathVariable("user_id") int user_id, @RequestParam("status") int status, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<House> houseList = userService.getHousesByUserIdAndStatus(user_id, status);
        PageInfo<House> pageInfo = new PageInfo<House>(houseList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户楼盘提问",notes = "根据用户id获取用户楼盘提问")
    @GetMapping("/{user_id}/questions")
    public PageInfo<Question> questions(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Question> questionList = userService.getQuestionByUserId(user_id);
        PageInfo<Question> pageInfo = new PageInfo<Question>(questionList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户楼盘提问回复",notes = "根据用户id获取用户楼盘提问回复")
    @GetMapping("/{user_id}/questions/responses")
    public PageInfo<Question> questionResponses(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Question> responseList = userService.getQuestionResponsesByUserId(user_id);
        PageInfo<Question> pageInfo = new PageInfo<Question>(responseList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户发出的的所有评论",notes = "根据用户id获取用户发出的所有评论")
    @GetMapping("/{user_id}/comments")
    public Comment comment(@PathVariable("user_id") int user_id){
        return userService.getCommentByUserId(user_id);
    }

    @ApiOperation(value = "获取用户发出的所有楼盘评论",notes = "根据用户id获取用户发出所有楼盘评论")
    @GetMapping("/{user_id}/commentBuilding")
    public PageInfo<CommentBuilding> commentBuilding(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<CommentBuilding> commentBuildingList = userService.getCommentBuildingByUserId(user_id);
        PageInfo<CommentBuilding> pageInfo = new PageInfo<CommentBuilding>(commentBuildingList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户发出的所有装修方案评论",notes = "根据用户id获取用户发出所有装修方案评论")
    @GetMapping("/{user_id}/commentDecorate")
    public PageInfo<CommentDecorate> commentDecorate(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<CommentDecorate> commentDecorateList = userService.getCommentDecorateByUserId(user_id);
        PageInfo<CommentDecorate> pageInfo = new PageInfo<CommentDecorate>(commentDecorateList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户发出的所有出租房评论",notes = "根据用户id获取用户发出所有出租房评论")
    @GetMapping("/{user_id}/commentHouse")
    public PageInfo<CommentHouse> commentHouse(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        QueryWrapper<CommentHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id);
        queryWrapper.eq("type", 1);
        List<CommentHouse> commentHouseList = commentHouseService.list(queryWrapper);
        PageInfo<CommentHouse> pageInfo = new PageInfo<>(commentHouseList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户浏览记录",notes = "根据userID获取用户浏览记录，分页查询")
    @GetMapping("/{user_id}/browsing")
    public PageInfo<Building> browsing(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Building> buildingList = userService.getBrowsingBuildingByUserId(user_id);
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户浏览户型记录",notes = "根据userID获取用户浏览户型记录，分页查询")
    @GetMapping("/{user_id}/browsing/huxing")
    public PageInfo<Huxing> browsingHuxing(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Huxing> huxingList = userService.getBrowsingHuxingByUserId(user_id);
        PageInfo<Huxing> pageInfo = new PageInfo<Huxing>(huxingList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户浏览出租房记录",notes = "根据userID获取用户浏览出租房记录，分页查询")
    @GetMapping("/{user_id}/browsing/house")
    public PageInfo<House> browsingHouse(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<House> houseList = userService.getBrowsingHouseByUserId(user_id);
        PageInfo<House> pageInfo = new PageInfo<House>(houseList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户浏览装修方案记录",notes = "根据userID获取用户浏览装修方案记录，分页查询")
    @GetMapping("/{user_id}/browsing/decoration")
    public PageInfo<Decorate> browsingDecorate(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Decorate> decorateList = userService.getBrowsingDecorateByUserId(user_id);
        PageInfo<Decorate> pageInfo = new PageInfo<Decorate>(decorateList);
        return pageInfo;
    }

    @ApiOperation(value = "获取用户浏览楼盘记录",notes = "根据userID获取用户浏览楼盘记录，分页查询")
    @GetMapping("/{user_id}/browsing/building")
    public PageInfo<Building> browsingBuilding(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Building> buildingList = userService.getBrowsingBuildingByUserId(user_id);
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);
        return pageInfo;
    }

    @ApiOperation(value = "点赞楼盘评论或回复",notes = "点赞楼盘评论或回复")
    @PutMapping("/{user_id}/building/commentOrResponseLike/{id}")
    public void buildingCommentOrResponseLike(@PathVariable("user_id") int user_id, @PathVariable("id") int id){
        QueryWrapper<CommentBuilding> commentBuildingQueryWrapper = new QueryWrapper<CommentBuilding>().eq("comment_id", id);
        CommentBuilding commentBuilding = commentBuildingService.getOne(commentBuildingQueryWrapper);
        commentBuilding.setLikeNum(commentBuilding.getLikeNum() + 1);
        commentBuildingService.save(commentBuilding);
    }

    @ApiOperation(value = "点赞装修方案评论或回复",notes = "点赞装修方案评论或回复")
    @PutMapping("/{user_id}/decoration/commentOrResponseLike/{id}")
    public void decorationCommentOrResponseLike(@PathVariable("user_id") int user_id, @PathVariable("id") int id){
        QueryWrapper<CommentDecorate> commentDecorateQueryWrapper = new QueryWrapper<CommentDecorate>().eq("comment_id", id);
        CommentDecorate commentDecorate = commentDecorateService.getOne(commentDecorateQueryWrapper);
        commentDecorate.setLikeNum(commentDecorate.getLikeNum() + 1);
        commentDecorateService.save(commentDecorate);
    }

    @ApiOperation(value = "点赞楼盘提问或回复",notes = "点赞楼盘提问或回复")
    @PutMapping("/{user_id}/building/questionOrResponseLike/{id}")
    public void buildingQuestionOrResponseLike(@PathVariable("user_id") int user_id, @PathVariable("id") int id){
        QueryWrapper<Question> commentBuildingQueryWrapper = new QueryWrapper<Question>().eq("comment_id", id);
        Question question = questionService.getOne(commentBuildingQueryWrapper);
        question.setLikeNum(question.getLikeNum() + 1);
        questionService.save(question);
    }

    @ApiOperation(value = "点赞装修方案提问或回复",notes = "点赞装修方案提问或回复")
    @PutMapping("/{user_id}/decoration/questionOrResponseLike/{id}")
    public void decorationQuestionOrResponseLike(@PathVariable("user_id") int user_id, @PathVariable("id") int id){
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<Question>().eq("comment_id", id);
        Question question = questionService.getOne(questionQueryWrapper);
        question.setLikeNum(question.getLikeNum() + 1);
        questionService.save(question);
    }

    @ApiOperation(value = "取消收藏",notes = "取消用户收藏，需传入取消收藏对象type")
    @DeleteMapping("/{user_id}/collection/{id}")
    public void collectionCancel(@PathVariable("user_id") int user_id, @PathVariable("id") int id, @RequestParam("type") int type){
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id).eq("object_id", id).eq("type", type);
        collectionService.remove(queryWrapper);
    }
}

