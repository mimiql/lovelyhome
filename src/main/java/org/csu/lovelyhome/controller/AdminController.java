package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.common.constant.Constant;
import org.csu.lovelyhome.common.util.CoordinateUtil;
import org.csu.lovelyhome.common.util.VerificationUtil;
import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.pojo.param.BuildingParam;
import org.csu.lovelyhome.pojo.vo.PointVO;
import org.csu.lovelyhome.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 管理员信息表 前端控制器
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
@Api(value = "后台管理相关API",description = "后台管理模块")
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController extends BaseController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BuildingServiceImpl buildingService;
    @Autowired
    private HouseServiceImpl houseService;
    @Autowired
    private DecorateServiceImpl decorateService;
    @Autowired
    private HuxingServiceImpl huxingService;
    @Autowired
    private CommentBuildingServiceImpl commentBuildingService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private CommentHouseServiceImpl commentHouseService;
    @Autowired
    private QuestionHouseServiceImpl questionHouseService;
    @Autowired
    private CommentDecorateServiceImpl commentDecorateService;
    @Autowired
    private QuestionDecorateServiceImpl questionDecorateService;
    @Autowired
    private AdminServiceImpl adminService;

    @ApiOperation(value = "返回所有租房", notes = "返回所有租房")
    @GetMapping("/houseManage/house/all")
    public List<House> housesAll(){
        return houseService.list();
    }

    @ApiOperation(value = "批量审批租房", notes = "批量审批租房")
    @PutMapping("/houseManage/house/patchVerificationIds")
    public Response housespatchVerificationIds(@RequestBody Integer[] ids){
        for(int id : ids){
            QueryWrapper<House> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("house_id", id);
            House house = houseService.getOne(queryWrapper);
            house.setStatus(Constant.STATUS_PUBLISHED);
            houseService.update(house, queryWrapper);
        }
        return success("审核成功!");
    }

    @ApiOperation(value = "批量删除租房", notes = "批量删除租房")
    @DeleteMapping("/houseManage/house/patchDeletingIds")
    public Response housespatchDeletingIds(@RequestBody Integer[] ids){
        for(int id : ids){
            QueryWrapper<House> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("house_id", id);
            houseService.remove(queryWrapper);
        }
        return success("批量删除成功!");
    }

    @ApiOperation(value = "根据id删除租房", notes = "根据id删除租房")
    @DeleteMapping("/houseManage/house/{id}")
    public Response houseDeletingIds(@PathVariable Integer id){
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("house_id", id);
        houseService.remove(queryWrapper);
        return success("删除成功!");
    }

    @ApiOperation(value = "批量删除用户信息", notes = "根据数组ID批量删除用户信息")
    @DeleteMapping("/userManage/patchDeletingIds")
    public Response patchDeletingIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("user_id", id);
            userService.remove(userQueryWrapper);
        }
        return success("删除成功!");
    }

    @ApiOperation(value = "管理员登录")
    @PostMapping("/login")
    public Response adminLogin(HttpServletRequest request){
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPass");
        if(email == null || !VerificationUtil.isEmail(email)){
            return fail("邮箱为空或错误!");
        }else{
            QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email", email);
            Admin admin = adminService.getOne(queryWrapper);
            if(admin != null){
                if(admin.getPassword().equals(password)){
                    return success("登录成功!");
                }else{
                    return fail("密码错误!");
                }
            }else{
                    return fail("邮箱错误!");
            }
        }
    }

    @ApiOperation(value = "批量删除楼盘信息", notes = "根据数组ID批量删除楼盘信息")
    @DeleteMapping("/buildingManage/patchDeletingIds")
    public Response patchDeletingbuildingIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<Building> buildingQueryWrapper = new QueryWrapper<Building>().eq("building_id", id);
            buildingService.remove(buildingQueryWrapper);
        }
        return success("删除成功!");
    }

    @ApiOperation(value = "冻结用户", notes = "根据ID冻结用户")
    @PutMapping("/userManage/freezingUser/{user_id}")
    public Response freezingUser(@PathVariable("user_id") int user_id){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("user_id", user_id);
        User user = userService.getOne(userQueryWrapper);
        user.setStatus(Constant.FREEZING_STATUS);
        userService.update(user, userQueryWrapper);

        return success("成功冻结该用户！");
    }

    @ApiOperation(value = "解冻用户", notes = "根据ID解冻用户")
    @PutMapping("/userManage/freezedUser/{user_id}")
    public Response freezedUser(@PathVariable("user_id") int user_id){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("user_id", user_id);
        User user = userService.getOne(userQueryWrapper);
        user.setStatus(Constant.NORMAL_STATUS);
        userService.update(user, userQueryWrapper);

        return success("成功解冻该用户！");
    }

    @ApiOperation(value = "删除用户信息",notes = "根据用户ID删除用户信息")
    @DeleteMapping("/userManage/{user_id}")
    public Response userManage(@PathVariable("user_id") int user_id){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("user_id", user_id);
        userService.remove(userQueryWrapper);
        return success("删除成功");
    }

    @ApiOperation(value = "发布楼盘信息",notes = "发布楼盘信息")
    @PostMapping("/buildingManage/{user_id}")
    public Response buildingPublish(@PathVariable("user_id") int user_id,BuildingParam buildingParam){
        Building building = buildingParam.toBuilding();
        List<Huxing> huxingList =buildingParam.getHuxings();
        building.setUserId(user_id);
        building.setPublishTime(new Date());
        building.setStatus(1);
        PointVO pointVO = CoordinateUtil.getCoordinate(building.getCity() + building.getDistrict() + building.getStreet());
        PointVO districtPointVO = CoordinateUtil.getCoordinate(building.getCity() + building.getDistrict());
        building.setLongitude(pointVO.getLongitude());
        building.setLatitude(pointVO.getLatitude());
        building.setDistrictLongitude(districtPointVO.getLongitude());
        building.setDistrictLatitude(districtPointVO.getLatitude());
        buildingService.save(building);

        for(Huxing huxing : huxingList){
            huxing.setBuildingId(building.getBuildingId());
            huxing.setUseId(building.getUserId());
            huxing.setStatus(1);
            huxing.setUseId(user_id);
            huxingService.save(huxing);
        }
        return success("发布成功");
    }

    @ApiOperation(value = "删除楼盘信息",notes = "根据楼盘ID删除楼盘信息")
    @DeleteMapping("/buildingManage/{building_id}")
    public Response buildingDeleting(@PathVariable("building_id") int building_id){
        buildingService.remove(new QueryWrapper<Building>().eq("building_id", building_id));
        return success("删除成功");
    }

    @ApiOperation(value = "修改楼盘信息",notes = "根据楼盘ID修改楼盘信息")
    @PutMapping("/buildingManage/modification/{building_id}")
    public Response buildingModification(@PathVariable("building_id") int building_id , BuildingParam buildingParam){
        Building building = buildingParam.toBuilding();
        List<Huxing> huxingList = buildingParam.getHuxings();
        QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("building_id", building_id);
        buildingService.update(building, queryWrapper);

        for(Huxing huxing : huxingList){
            QueryWrapper<Huxing> huxingQueryWrapper = new QueryWrapper<>();
            huxingQueryWrapper.eq("huxing_id", huxing.getHuxingId());
            huxingService.update(huxing, huxingQueryWrapper);
        }
        return success("楼盘更改成功！");
    }

    @ApiOperation(value = "审核租房",notes = "根据户型ID审核租房")
    @PutMapping("/houseManage/{house_id}")
    public Response housePublish(@PathVariable("house_id") int house_id){
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("house_id", house_id);
        House house = houseService.getOne(queryWrapper);
        house.setStatus(1);
        houseService.update(house, queryWrapper);

        return success("审核通过！");
    }

    @ApiOperation(value = "发布装修方案",notes = "发布装修方案")
    @PostMapping("/decorateManage/publish")
    public Response decorationPublish(@RequestBody Decorate decorate){
        decorateService.save(decorate);
        return success("发布装修方案成功！");
    }

    @ApiOperation(value = "删除评论",notes = "根据ID删除评论")
    @DeleteMapping("/forumManage/commentBuilding/{id}")
    public Response commentBuilding(@PathVariable("id") int id){
        commentBuildingService.remove(new QueryWrapper<CommentBuilding>().eq("comment_id", id));
        return success("删除该评论成功！");
    }

    @ApiOperation(value = "批量删除楼盘评论信息", notes = "根据数组ID批量删除楼盘评论信息")
    @DeleteMapping("/forumManage/commentBuilding/patchDeletingIds")
    public Response patchDeletingCommentBuildingIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<CommentBuilding> commentBuildingQueryWrapper = new QueryWrapper<CommentBuilding>().eq("comment_id", id);
            commentBuildingService.remove(commentBuildingQueryWrapper);
        }
        return success("删除成功!");
    }

    @ApiOperation(value = "查看楼盘", notes = "查看楼盘的评论论坛")
    @GetMapping("/forumManage/commentBuilding")
    public List<CommentBuilding> forumCommentBuilding(@RequestParam("status") int status){
        QueryWrapper<CommentBuilding> queryWrapper = new QueryWrapper<>();
        if (Constant.STATUS_PUBLISHED.equals(status) || Constant.STATUS_VERIFICATION.equals(status)){
            queryWrapper.eq("status", status);
        }

        queryWrapper.eq("type", Constant.COMMENT_TYPE);

        return commentBuildingService.list(queryWrapper);
    }

    @ApiOperation(value = "查看楼盘", notes = "查看楼盘的问题论坛")
    @GetMapping("/forumManage/questionBuilding")
    public List<Question> forumQuestionBuilding(@RequestParam("status") int status){
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        if (Constant.STATUS_PUBLISHED.equals(status) || Constant.STATUS_VERIFICATION.equals(status)){
            queryWrapper.eq("status", status);
        }

        queryWrapper.eq("type", Constant.QUESTION_TYPE);
        return questionService.list(queryWrapper);
    }

    @ApiOperation(value = "查看出租房", notes = "查看出租房的评论论坛")
    @GetMapping("/forumManage/commentHouse")
    public List<CommentHouse> forumCommentHouse(@RequestParam("status") int status){
        QueryWrapper<CommentHouse> queryWrapper = new QueryWrapper<>();
        if (Constant.STATUS_PUBLISHED.equals(status) || Constant.STATUS_VERIFICATION.equals(status)){
            queryWrapper.eq("status", status);
        }

        queryWrapper.eq("type", Constant.COMMENT_TYPE);
        return commentHouseService.list(queryWrapper);
    }

    @ApiOperation(value = "查看出租房", notes = "查看出租房的问题论坛")
    @GetMapping("/forumManage/questionHouse")
    public List<QuestionHouse> forumQuestionHouse(@RequestParam("status") int status){
        QueryWrapper<QuestionHouse> queryWrapper = new QueryWrapper<>();
        if (Constant.STATUS_PUBLISHED.equals(status) || Constant.STATUS_VERIFICATION.equals(status)){
            queryWrapper.eq("status", status);
        }

        queryWrapper.eq("type", Constant.QUESTION_TYPE);
        return questionHouseService.list(queryWrapper);
    }

    @ApiOperation(value = "查看装修方案", notes = "查看装修方案的评论论坛")
    @GetMapping("/forumManage/commentDecoration")
    public List<CommentDecorate> forumCommentDecoration(@RequestParam("status") int status){
        QueryWrapper<CommentDecorate> queryWrapper = new QueryWrapper<>();
        if (Constant.STATUS_PUBLISHED.equals(status) || Constant.STATUS_VERIFICATION.equals(status)){
            queryWrapper.eq("status", status);
        }

        queryWrapper.eq("type", Constant.COMMENT_TYPE);
        return commentDecorateService.list(queryWrapper);
    }

    @ApiOperation(value = "查看装修方案", notes = "查看装修方案的问题的论坛")
    @GetMapping("/forumManage/questionDecoration")
    public List<QuestionDecorate> forumQuestionDecoration(@RequestParam("status") int status){
        QueryWrapper<QuestionDecorate> queryWrapper = new QueryWrapper<>();
        if (Constant.STATUS_PUBLISHED.equals(status) || Constant.STATUS_VERIFICATION.equals(status)){
            queryWrapper.eq("status", status);
        }

        queryWrapper.eq("type", Constant.QUESTION_TYPE);
        return questionDecorateService.list(queryWrapper);
    }

    @ApiOperation(value = "审核楼盘", notes = "审核楼盘的评论论坛")
    @PutMapping("/forumManage/commentBuilding//{id}")
    public void forumCommentBuildingVerification(@PathVariable("id") int id){
        QueryWrapper<CommentBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", id);
        CommentBuilding commentBuilding = commentBuildingService.getOne(queryWrapper);
        commentBuilding.setStatus(Constant.STATUS_PUBLISHED);
        commentBuildingService.update(commentBuilding, queryWrapper);
    }

    @ApiOperation(value = "批量审核楼盘的评论", notes = "根据数组ID批量批量审核楼盘的评论")
    @DeleteMapping("/forumManage/commentBuilding/patchVerificationIds")
    public void patchCommentBuildingVerificationIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<CommentBuilding> queryWrapper = new QueryWrapper<CommentBuilding>().eq("comment_id", id);
            CommentBuilding commentBuilding = commentBuildingService.getOne(queryWrapper);
            commentBuilding.setStatus(Constant.STATUS_PUBLISHED);
            commentBuildingService.update(commentBuilding, queryWrapper);
        }
    }

    @ApiOperation(value = "审核楼盘", notes = "审核楼盘的问题论坛")
    @PutMapping("/forumManage/questionBuilding/Verification/{id}")
    public void forumQuestionBuildingVerification(@PathVariable("id") int id){
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", id);
        Question question = questionService.getOne(queryWrapper);
        question.setStatus(Constant.STATUS_PUBLISHED);
        questionService.update(question, queryWrapper);
    }

    @ApiOperation(value = "批量审核楼盘的问题", notes = "根据数组ID批量批量审核楼盘的问题")
    @DeleteMapping("/forumManage/questionBuilding/patchVerificationIds")
    public void patchQuestionBuildingVerificationIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<Question> queryWrapper = new QueryWrapper<Question>().eq("question_id", id);
            Question question = questionService.getOne(queryWrapper);
            question.setStatus(Constant.STATUS_PUBLISHED);
            questionService.update(question, queryWrapper);
        }
    }

    @ApiOperation(value = "审核出租房", notes = "审核出租房的评论论坛")
    @PutMapping("/forumManage/commentHouse/Verification/{id}")
    public void forumCommentHouseVerification(@PathVariable("id") int id){
        QueryWrapper<CommentHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", id);
        CommentHouse commentHouse = commentHouseService.getOne(queryWrapper);
        commentHouse.setStatus(Constant.STATUS_PUBLISHED);
        commentHouseService.update(commentHouse, queryWrapper);
    }

    @ApiOperation(value = "批量审核出租房的评论", notes = "根据数组ID批量批量审核出租房的评论")
    @DeleteMapping("/forumManage/commentHouse/patchVerificationIds")
    public void patchCommentHouseVerificationIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<CommentHouse> queryWrapper = new QueryWrapper<CommentHouse>().eq("comment_id", id);
            CommentHouse commentHouse = commentHouseService.getOne(queryWrapper);
            commentHouse.setStatus(Constant.STATUS_PUBLISHED);
            commentHouseService.update(commentHouse, queryWrapper);
        }
    }

    @ApiOperation(value = "审核出租房", notes = "审核出租房的问题论坛")
    @PutMapping("/forumManage/questionHouse/Verification/{id}")
    public void forumQuestionHouseVerification(@PathVariable("id") int id){
        QueryWrapper<QuestionHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", id);
        QuestionHouse questionHouse = questionHouseService.getOne(queryWrapper);
        questionHouse.setStatus(Constant.STATUS_PUBLISHED);
        questionHouseService.update(questionHouse, queryWrapper);
    }

    @ApiOperation(value = "批量审核出租房的问题", notes = "根据数组ID批量批量审核出租房的问题")
    @DeleteMapping("/forumManage/questionHouse/patchVerificationIds")
    public void patchQuestionHouseVerificationIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<QuestionHouse> queryWrapper = new QueryWrapper<QuestionHouse>().eq("question_id", id);
            QuestionHouse questionHouse = questionHouseService.getOne(queryWrapper);
            questionHouse.setStatus(Constant.STATUS_PUBLISHED);
            questionHouseService.update(questionHouse, queryWrapper);
        }
    }

    @ApiOperation(value = "审核装修方案", notes = "审核装修方案的评论论坛")
    @PutMapping("/forumManage/commentDecoration/Verification/{id}")
    public void forumCommentDecorationVerification(@PathVariable("id") int id){
        QueryWrapper<CommentDecorate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", id);
        CommentDecorate commentDecorate = commentDecorateService.getOne(queryWrapper);
        commentDecorate.setStatus(Constant.STATUS_PUBLISHED);
        commentDecorateService.update(commentDecorate, queryWrapper);
    }

    @ApiOperation(value = "批量审核装修方案的评论", notes = "根据数组ID批量批量审核装修方案的评论")
    @DeleteMapping("/forumManage/commentDecoration/patchVerificationIds")
    public void patchCommentDecorationVerificationIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<CommentDecorate> queryWrapper = new QueryWrapper<CommentDecorate>().eq("comment_id", id);
            CommentDecorate commentDecorate = commentDecorateService.getOne(queryWrapper);
            commentDecorate.setStatus(Constant.STATUS_PUBLISHED);
            commentDecorateService.update(commentDecorate, queryWrapper);
        }
    }

    @ApiOperation(value = "审核装修方案", notes = "审核装修方案的问题论坛")
    @PutMapping("/forumManage/questionDecoration/Verification/{id}")
    public void forumQuestionDecorationVerification(@PathVariable("id") int id){
        QueryWrapper<QuestionDecorate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", id);
        QuestionDecorate questionDecorate = questionDecorateService.getOne(queryWrapper);
        questionDecorate.setStatus(Constant.STATUS_PUBLISHED);
        questionDecorateService.update(questionDecorate, queryWrapper);
    }

    @ApiOperation(value = "批量审核装修方案的问题", notes = "根据数组ID批量批量审核装修方案的问题")
    @DeleteMapping("/forumManage/questionDecoration/patchVerificationIds")
    public void patchQuestionDecorationVerificationIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<QuestionDecorate> queryWrapper = new QueryWrapper<QuestionDecorate>().eq("question_id", id);
            QuestionDecorate questionDecorate = questionDecorateService.getOne(queryWrapper);
            questionDecorate.setStatus(Constant.STATUS_PUBLISHED);
            questionDecorateService.update(questionDecorate, queryWrapper);
        }
    }

    @ApiOperation(value = "删除楼盘的评论")
    @DeleteMapping("/forumManage/commentBuilding/deleting/{id}")
    public Response forumCommentBuildingDeleting(@PathVariable("id") int id){
        QueryWrapper<CommentBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", id);
        commentBuildingService.remove(queryWrapper);
        return success("删除成功！");
    }

    @ApiOperation(value = "删除楼盘的提问")
    @DeleteMapping("/forumManage/questionBuilding/deleting/{id}")
    public Response forumQuestionBuildingDeleting(@PathVariable("id") int id){
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", id);
        questionService.remove(queryWrapper);
        return success("删除成功！");
    }

    @ApiOperation(value = "批量删除楼盘提问", notes = "根据数组ID批量删除楼盘提问")
    @DeleteMapping("/forumManage/questionBuilding/patchDeletingIds")
    public Response patchDeletingQuestionBuildingIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<Question>().eq("question_id", id);
            questionService.remove(questionQueryWrapper);
        }
        return success("批量删除成功!");
    }

    @ApiOperation(value = "删除租房的评论")
    @DeleteMapping("/forumManage/commentHouse/deleting/{id}")
    public Response forumCommentHouseDeleting(@PathVariable("id") int id){
        QueryWrapper<CommentHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", id);
        commentHouseService.remove(queryWrapper);
        return success("删除成功！");
    }

    @ApiOperation(value = "批量删除租房的评论", notes = "根据数组ID批量删除租房的评论")
    @DeleteMapping("/forumManage/commentHouse/patchDeletingIds")
    public Response patchDeletingCommentHouseIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<CommentHouse> queryWrapper = new QueryWrapper<CommentHouse>().eq("comment_id", id);
            commentHouseService.remove(queryWrapper);
        }
        return success("批量删除成功!");
    }

    @ApiOperation(value = "删除租房的提问")
    @DeleteMapping("/forumManage/questionHouse/deleting/{id}")
    public Response forumQuestionHouseDeleting(@PathVariable("id") int id){
        QueryWrapper<QuestionHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", id);
        questionHouseService.remove(queryWrapper);
        return success("删除成功！");
    }

    @ApiOperation(value = "批量删除租房的提问", notes = "根据数组ID批量删除租房的提问")
    @DeleteMapping("/forumManage/questionHouse/patchDeletingIds")
    public Response patchDeletingQuestionHouseIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<QuestionHouse> queryWrapper = new QueryWrapper<QuestionHouse>().eq("question_id", id);
            questionHouseService.remove(queryWrapper);
        }
        return success("批量删除成功!");
    }

    @ApiOperation(value = "删除装修方案的评论")
    @DeleteMapping("/forumManage/commentDecoration/deleting/{id}")
    public Response forumCommentDecorationDeleting(@PathVariable("id") int id){
        QueryWrapper<CommentDecorate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", id);
        commentDecorateService.remove(queryWrapper);
        return success("删除成功！");
    }

    @ApiOperation(value = "批量删除装修方案的评论", notes = "根据数组ID批量删除装修方案的评论")
    @DeleteMapping("/forumManage/commentDecoration/patchDeletingIds")
    public Response patchDeletingCommentDecorationIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<CommentDecorate> queryWrapper = new QueryWrapper<CommentDecorate>().eq("comment_id", id);
            commentDecorateService.remove(queryWrapper);
        }
        return success("批量删除成功!");
    }

    @ApiOperation(value = "删除装修方案的提问")
    @DeleteMapping("/forumManage/questionDecoration/deleting/{id}")
    public Response forumQuestionDecorationDeleting(@PathVariable("id") int id){
        QueryWrapper<QuestionDecorate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", id);
        questionDecorateService.remove(queryWrapper);
        return success("删除成功！");
    }

    @ApiOperation(value = "批量删除装修方案的提问", notes = "根据数组ID批量删除装修方案的提问")
    @DeleteMapping("/forumManage/questionDecoration/patchDeletingIds")
    public Response patchDeletingQuestionDecorationIds(@RequestBody Integer[] deleteIdArray){
        for(int id : deleteIdArray){
            QueryWrapper<QuestionDecorate> queryWrapper = new QueryWrapper<QuestionDecorate>().eq("question_id", id);
            questionDecorateService.remove(queryWrapper);
        }
        return success("批量删除成功!");
    }
}

