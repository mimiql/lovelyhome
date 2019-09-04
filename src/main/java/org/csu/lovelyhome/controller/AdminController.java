package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.common.constant.Constant;
import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 管理员信息表 前端控制器
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
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


    @ApiOperation(value = "删除用户信息",notes = "根据用户ID删除用户信息")
    @DeleteMapping("/userManage/{user_id}")
    public Response userManage(@PathVariable("user_id") int user_id){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("user_id", user_id);
        userService.remove(userQueryWrapper);
        //还没写完，还要级联删除
        return success("删除成功");
    }

    @PostMapping("/buildingManage/{user_id}")
    public Response buildingPublish(@PathVariable("user_id") int user_id, @RequestBody Building building, @RequestBody List<Huxing> huxingList){
        building.setUserId(user_id);
        buildingService.save(building);

        for(Huxing huxing : huxingList){
            huxing.setBuildingId(building.getBuildingId());
            huxingService.save(huxing);
        }
        return success("发布成功");
    }

    @DeleteMapping("/buildingManage/{building_id}")
    public Response buildingDeleting(@PathVariable("building_id") int building_id){
        //先不写
        return success("删除成功");
    }

    @ApiOperation(value = "修改楼盘信息",notes = "根据楼盘ID修改楼盘信息")
    @PutMapping("/buildingManage/modification/{building_id}")
    public Response buildingModification(@PathVariable("building_id") int building_id, @RequestBody Building building, @RequestBody List<Huxing> huxingList){
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

    @PostMapping("/decorateManage/publish")
    public Response decorationPublish(@RequestBody Decorate decorate){
        decorateService.save(decorate);
        return success("发布装修方案成功！");
    }

    @ApiOperation(value = "这是个空的实现",notes = "lqm写的为主")
    @PutMapping("/decorateManage/{decorate_id}/modification")
    public Response decorationModification(@PathVariable("decorate_id") int decorate_id){
        //先别写
        return success("修改装修方案成功！");
    }

    @DeleteMapping("/forumManage/commentBuilding/{id}")
    public Response commentBuilding(@PathVariable("id") int id){
        //没写
        return success("删除该评论成功！");
    }

    @ApiOperation(value = "这是个空的实现",notes = "lqm写的为主")
    @DeleteMapping("/forumManage/commentDecorate/{id}")
    public Response commentDecorate(@PathVariable("id") int id){
        //没写
        return success("删除该评论成功！");
    }

//    @ApiOperation(value = "审核楼盘", notes = "")
////    @PutMapping("/forumManage/commentDecorate/{id}")

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
    @PutMapping("/forumManage/commentBuilding/Verification/{id}")
    public void forumCommentBuildingVerification(@PathVariable("id") int id){
        QueryWrapper<CommentBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", id);
        CommentBuilding commentBuilding = commentBuildingService.getOne(queryWrapper);
        commentBuilding.setStatus(Constant.STATUS_PUBLISHED);
        commentBuildingService.update(commentBuilding, queryWrapper);
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

    @ApiOperation(value = "审核出租房", notes = "审核出租房的评论论坛")
    @PutMapping("/forumManage/commentHouse/Verification/{id}")
    public void forumCommentHouseVerification(@PathVariable("id") int id){
        QueryWrapper<CommentHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", id);
        CommentHouse commentHouse = commentHouseService.getOne(queryWrapper);
        commentHouse.setStatus(Constant.STATUS_PUBLISHED);
        commentHouseService.update(commentHouse, queryWrapper);
    }

    @ApiOperation(value = "审核出租房", notes = "审核出租房的问题论坛")
    @PutMapping("/forumManage/questionHouse/Verification/{id}")
    public void forumQuestionHouseVerification(@PathVariable("id") int id){
        QueryWrapper<QuestionHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", id);
        QuestionHouse questionHouse = questionHouseService.getOne(queryWrapper);
        questionHouse.setStatus(Constant.STATUS_PUBLISHED);
        questionHouseService.update(questionHouse, queryWrapper);
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

    @ApiOperation(value = "审核装修方案", notes = "审核装修方案的问题论坛")
    @PutMapping("/forumManage/questionDecoration/Verification/{id}")
    public void forumQuestionDecorationVerification(@PathVariable("id") int id){
        QueryWrapper<QuestionDecorate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", id);
        QuestionDecorate questionDecorate = questionDecorateService.getOne(queryWrapper);
        questionDecorate.setStatus(Constant.STATUS_PUBLISHED);
        questionDecorateService.update(questionDecorate, queryWrapper);
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

    @ApiOperation(value = "删除租房的评论")
    @DeleteMapping("/forumManage/commentHouse/deleting/{id}")
    public Response forumCommentHouseDeleting(@PathVariable("id") int id){
        QueryWrapper<CommentHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", id);
        commentHouseService.remove(queryWrapper);
        return success("删除成功！");
    }

    @ApiOperation(value = "删除租房的提问")
    @DeleteMapping("/forumManage/questionHouse/deleting/{id}")
    public Response forumQuestionHouseDeleting(@PathVariable("id") int id){
        QueryWrapper<QuestionHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", id);
        questionHouseService.remove(queryWrapper);
        return success("删除成功！");
    }

    @ApiOperation(value = "删除装修方案的评论")
    @DeleteMapping("/forumManage/commentDecoration/deleting/{id}")
    public Response forumCommentDecorationDeleting(@PathVariable("id") int id){
        QueryWrapper<CommentDecorate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", id);
        commentDecorateService.remove(queryWrapper);
        return success("删除成功！");
    }

    @ApiOperation(value = "删除装修方案的提问")
    @DeleteMapping("/forumManage/questionDecoration/deleting/{id}")
    public Response forumQuestionDecorationDeleting(@PathVariable("id") int id){
        QueryWrapper<QuestionDecorate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", id);
        questionDecorateService.remove(queryWrapper);
        return success("删除成功！");
    }
}

