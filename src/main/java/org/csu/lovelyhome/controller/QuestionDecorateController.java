package org.csu.lovelyhome.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.entity.QuestionDecorate;
import org.csu.lovelyhome.entity.RelateQuestionDecorate;
import org.csu.lovelyhome.pojo.vo.QuestionDecorateVO;
import org.csu.lovelyhome.service.IQuestionDecorateService;
import org.csu.lovelyhome.service.IRelateQuestionDecorateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 装修方案提问及回复表 前端控制器
 * </p>
 *
 * @author lqm
 * @since 2019-09-02
 */
@RestController
@RequestMapping("questionDecorate")
@Api(value = "装修方案提问相关API",description = "装修模块论坛模块提问模块")
public class QuestionDecorateController extends BaseController {

    @Autowired
    private IQuestionDecorateService questionDecorateService;
    @Autowired
    private IRelateQuestionDecorateService relateQuestionDecorateService;

    @ApiOperation(value = "新增装修方案提问", notes = "插入装修方案提问")
    @PostMapping("/question")
    public Response newQuestionForDecorate(@RequestBody QuestionDecorate questionDecorate){
        questionDecorate.setType(1);
        questionDecorate.setTime(new Date());
        if (questionDecorateService.save(questionDecorate)){
            success("评论成功");
        }
        return fail("评论失败");
    }

    @ApiOperation(value = "装修方案提问回复", notes = "装修方案提问回复插入")
    @PostMapping("/questionReply/{questionId}")
    public Response newCommentForDecorate(@RequestBody QuestionDecorate questionDecorate, @PathVariable int questionId){
        questionDecorate.setType(0);
        questionDecorate.setTime(new Date());
        questionDecorateService.save(questionDecorate);

        RelateQuestionDecorate relateQuestionDecorate = new RelateQuestionDecorate();
        relateQuestionDecorate.setQuestionId(questionId);
        relateQuestionDecorate.setResponseId(questionDecorate.getQuestionId());
        relateQuestionDecorateService.save(relateQuestionDecorate);

        return success("回复成功");
    }

    @ApiOperation(value = "发布装修方案提问或回复", notes = "插入装修方案提问或回复")
    @PutMapping("/publish/{commentId}")
    public Response publishQusetionForDecorate(@PathVariable int commentId){
        QuestionDecorate questionDecorate = questionDecorateService.getById(commentId);
        questionDecorate.setStatus(1);
        if (questionDecorateService.save(questionDecorate)){
            return fail("发布失败");
        }
        return success("发布成功");
    }

    @ApiOperation(value = "删除装修方案提问或回复", notes = "删除装修方案提问或回复")
    @DeleteMapping("/{commentId}")
    public Response deleteQestionForDecorate(@PathVariable int commentId){
        if (questionDecorateService.removeById(commentId)){
            return fail("删除失败");
        }
        return success("删除成功");
    }

    @ApiOperation(value = "获取装修方案提问", notes = "根据装修方案ID获取提问")
    @GetMapping("/question/{decorateId}")
    public Response getQuestionByDecorateId(@PathVariable int decorateId){
        QueryWrapper<QuestionDecorate> wrapper = new QueryWrapper<>();
        wrapper.eq("decorate_id", decorateId).eq("type",1 );
        List<QuestionDecorate> questionDecorates = questionDecorateService.list(wrapper);
        List<QuestionDecorateVO> questionDecorateVOS = new ArrayList<>();
        for (QuestionDecorate questionDecorate : questionDecorates){
            QuestionDecorateVO questionDecorateVO = new QuestionDecorateVO();
            questionDecorateVO.setQuestionDecorate(questionDecorate);
            //找到提问的最新一条回复
            QueryWrapper<QuestionDecorate> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("question_id", questionDecorate.getQuestionId());
            wrapper1.orderByDesc("time");
            QuestionDecorate answerDecorate = questionDecorateService.getOne(wrapper1);
            questionDecorateVO.setAnswerDecorate(answerDecorate);

            questionDecorateVOS.add(questionDecorateVO);
        }
        return success(questionDecorateVOS);
    }

    @ApiOperation(value = "获取装修方案提问回复", notes = "根据提问ID获取提问回复")
    @GetMapping("/questionReply/{questionId}")
    public Response getQuestionReplyByQuestionId(@PathVariable int questionId){
        QueryWrapper<RelateQuestionDecorate> wrapper = new QueryWrapper<>();
        wrapper.eq("question_id", questionId);
        List<RelateQuestionDecorate> relateQuestionDecorates = relateQuestionDecorateService.list(wrapper);
        List<QuestionDecorate> questionDecorates = new ArrayList<>();
        for(RelateQuestionDecorate relateQuestionDecorate : relateQuestionDecorates){
            QuestionDecorate questionDecorate = questionDecorateService.getById(relateQuestionDecorate.getResponseId());
            if(questionDecorate != null){
                questionDecorates.add(questionDecorate);
            }
        }
        return success(questionDecorates);
    }

    @ApiOperation(value = "获取用户发出的装修方案的提问回复", notes = "根据UserID获取用户发出的对装修方案所有的提问回复")
    @GetMapping("/questionReply/user/{userId}")
    public Response getQuestionReplyByUserId(@PathVariable int userId){
        QueryWrapper<QuestionDecorate> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("type",0 );
        List<QuestionDecorate> questionDecorates = questionDecorateService.list(wrapper);
        return success(questionDecorates);
    }

    @ApiOperation(value = "获取用户发出的装修方案的提问", notes = "根据UserID获取用户发出的对装修方案所有的提问")
    @GetMapping("/question/user/{userId}")
    public Response getQuestionByUserId(@PathVariable int userId){
        QueryWrapper<QuestionDecorate> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("type",1 );
        List<QuestionDecorate> questionDecorates = questionDecorateService.list(wrapper);
        return success(questionDecorates);
    }

    @ApiOperation(value = "点赞装修方案提问或提问回复", notes = "修改装修方案提问或回复点赞数目")
    @PutMapping("/questionLike/{questionId}")
    public Response likeQuestionForDecorate(@PathVariable int questionId){
        QuestionDecorate questionDecorate = questionDecorateService.getById(questionId);
        if (questionDecorate == null){
            return fail("提问ID" + questionId + "不存在");
        }
        questionDecorate.setLikeNum(questionDecorate.getLikeNum() + 1);
        questionDecorateService.updateById(questionDecorate);
        return success("点赞成功");
    }

}

