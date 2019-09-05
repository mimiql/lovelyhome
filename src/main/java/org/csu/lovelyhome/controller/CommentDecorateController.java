package org.csu.lovelyhome.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.entity.CommentDecorate;
import org.csu.lovelyhome.entity.RelateDecorate;
import org.csu.lovelyhome.service.ICommentDecorateService;
import org.csu.lovelyhome.service.IRelateDecorateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 装修方案评论表 前端控制器
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/commentDecorate")
@Api(value = "装修方案评论相关API",description = "装修模块论坛模块会回复模块")
public class CommentDecorateController extends BaseController {

    @Autowired
    private ICommentDecorateService commentDecorateService;
    @Autowired
    private IRelateDecorateService relateDecorateService;

    @ApiOperation(value = "新增装修方案评论", notes = "插入装修方案评论")
    @PostMapping("/")
    public Response newCommentForDecorate(@RequestBody CommentDecorate commentDecorate){
        commentDecorate.setType(1);
        commentDecorate.setStatus(0);
        commentDecorate.setTime(new Date());
        if (commentDecorateService.save(commentDecorate)){
            return fail("评论失败");
        }
        return success("评论成功");
    }

    @ApiOperation(value = "发布装修方案评论或回复", notes = "发布装修方案评论或回复")
    @PutMapping("/publish/{commentId}")
    public Response publishCommentForDecorate(@PathVariable int commentId){
        CommentDecorate commentDecorate = commentDecorateService.getById(commentId);
        commentDecorate.setStatus(1);
        if (commentDecorateService.save(commentDecorate)){
            return fail("发布失败");
        }
        return success("发布成功");
    }

    @ApiOperation(value = "删除装修方案评论或回复", notes = "删除装修方案评论或回复")
    @DeleteMapping("/{commentId}")
    public Response deleteCommentForDecorate(@PathVariable int commentId){
        if (commentDecorateService.removeById(commentId)){
            return fail("删除失败");
        }
        return success("删除成功");
    }

    @ApiOperation(value = "装修方案评论回复", notes = "装修方案评论回复插入")
    @PostMapping("/commentReply/{commentId}")
    public Response newCommentForDecorate(@RequestBody CommentDecorate commentDecorate, @PathVariable int commentId){
        commentDecorate.setType(0);
        commentDecorate.setTime(new Date());
        commentDecorateService.save(commentDecorate);

        RelateDecorate relateDecorate = new RelateDecorate();
        relateDecorate.setCommentId(commentId);
        relateDecorate.setResponseId(commentDecorate.getCommentId());
        relateDecorateService.save(relateDecorate);

        return success("回复成功");
    }

    @ApiOperation(value = "获取装修方案评论回复", notes = "根据评论ID获取评论回复")
    @GetMapping("/commentReply/{commentId}")
    public Response getCommentReplyByCommentId(@PathVariable int commentId){
        QueryWrapper<RelateDecorate> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_id", commentId);
        List<RelateDecorate> relateDecorates = relateDecorateService.list(wrapper);
        List<CommentDecorate> commentDecorates = new ArrayList<>();
        for (RelateDecorate relateDecorate : relateDecorates){
            CommentDecorate commentDecorate = commentDecorateService.getById(relateDecorate.getResponseId());
            if(commentDecorate != null){
                commentDecorates.add(commentDecorate);
            }
        }
        return success(commentDecorates);
    }

    @ApiOperation(value = "获取装修方案评论", notes = "根据装修方案ID获取评论")
    @GetMapping("/comment/{decorateId}")
    public Response getCommentByDecorateId(@PathVariable int decorateId){
        QueryWrapper<CommentDecorate> wrapper = new QueryWrapper<>();
        wrapper.eq("decorate_id", decorateId).eq("type",1 );
        List<CommentDecorate> commentDecorates = commentDecorateService.list(wrapper);
        return success(commentDecorates);
    }

    @ApiOperation(value = "点赞装修方案评论或评论回复", notes = "修改装修方案评论或回复点赞数目")
    @PutMapping("/commentLike/{commentId}")
    public Response likeCommentForDecorate(@PathVariable int commentId){
        CommentDecorate commentDecorate = commentDecorateService.getById(commentId);
        commentDecorate.setLikeNum(commentDecorate.getLikeNum() + 1);
        commentDecorateService.updateById(commentDecorate);
        return success("点赞成功");
    }

    @ApiOperation(value = "获取用户发出的装修方案的评论", notes = "根据UserID获取用户发出的对装修方案所有的评论")
    @GetMapping("/comment/user/{userId}")
    public Response getCommentByUserId(@PathVariable int userId){
        QueryWrapper<CommentDecorate> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("type",1 );
        List<CommentDecorate> commentDecorates = commentDecorateService.list(wrapper);
        return success(commentDecorates);
    }

    @ApiOperation(value = "获取用户发出的装修方案的评论回复", notes = "根据UserID获取用户发出的对装修方案所有的评论回复")
    @GetMapping("/commentReply/user/{userId}")
    public Response getCommentReplyByUserId(@PathVariable int userId){
        QueryWrapper<CommentDecorate> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("type",0);
        List<CommentDecorate> commentDecorates = commentDecorateService.list(wrapper);
        return success(commentDecorates);
    }

}

