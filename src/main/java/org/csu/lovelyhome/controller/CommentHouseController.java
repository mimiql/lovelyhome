package org.csu.lovelyhome.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.common.constant.Constant;
import org.csu.lovelyhome.entity.CommentHouse;
import org.csu.lovelyhome.entity.RelateHouse;
import org.csu.lovelyhome.service.impl.BuildingServiceImpl;
import org.csu.lovelyhome.service.impl.CommentHouseServiceImpl;
import org.csu.lovelyhome.service.impl.HouseServiceImpl;
import org.csu.lovelyhome.service.impl.RelateHouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 出租房评论及回复记录表 前端控制器
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-09-03
 */
@RestController
@RequestMapping("/comment-house")
@CrossOrigin
@Api(value = "租房评论相关API",description = "租房评论及回复相关API")
public class CommentHouseController extends BaseController {
    @Autowired
    private CommentHouseServiceImpl commentHouseService;
    @Autowired
    private RelateHouseServiceImpl relateHouseService;

    @ApiOperation(value = "回复租房评论", notes = "某UserId的用户在houseId下的commentId发表评论")
    @PostMapping("/{house_Id}/{commentId}/{userId}")
    public Response commentResponse(@PathVariable("house_Id") int house_Id, @PathVariable("userId") int userId,
                                    @PathVariable("commentId") int commentId, @RequestBody CommentHouse response){
        response.setHouseId(house_Id);
        response.setUserId(userId);
        response.setLikeNum(0);
        response.setTime(new Date());
        response.setType(Constant.RESPONSE_TYPE);
        response.setStatus(Constant.STATUS_PUBLISHED);
        commentHouseService.save(response);

        RelateHouse relateHouse = new RelateHouse();
        relateHouse.setCommentId(commentId);
        relateHouse.setResponseId(response.getCommentId());
        relateHouseService.save(relateHouse);

        return success("回复成功,待审核！");
    }

    @ApiOperation(value = "发表租房评论", notes = "某UserId的用户在houseId下发表评论")
    @PostMapping("/{house_Id}/{userId}")
    public Response comment(@PathVariable("house_Id") int house_Id, @PathVariable("userId") int userId, @RequestBody CommentHouse commentHouse){
        commentHouse.setHouseId(house_Id);
        commentHouse.setUserId(userId);
        commentHouse.setTime(new Date());
        commentHouse.setLikeNum(0);
        commentHouse.setType(Constant.COMMENT_TYPE);
        commentHouse.setStatus(Constant.STATUS_VERIFICATION);
        commentHouseService.save(commentHouse);

        return success("评论成功,待审核！");
    }
}

