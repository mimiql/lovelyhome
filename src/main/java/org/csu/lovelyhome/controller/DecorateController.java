package org.csu.lovelyhome.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.config.ConstantConfig;
import org.csu.lovelyhome.entity.CommentDecorate;
import org.csu.lovelyhome.entity.Decorate;
import org.csu.lovelyhome.entity.Question;
import org.csu.lovelyhome.entity.RelateDecorate;
import org.csu.lovelyhome.pojo.param.FiltDecorateParam;
import org.csu.lovelyhome.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 装修方案表 前端控制器
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/decorate")
@CrossOrigin
@Api(value = "装修方案相关API",description = "装修模块")
public class DecorateController extends BaseController {
    @Autowired
    private IDecorateService decorateService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ICommentDecorateService commentDecorateService;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IRelateDecorateService relateDecorateService;
    @Autowired
    private IRelateQuestionService relateQuestionService;
    @Autowired
    private ConstantConfig constantConfig;
    @GetMapping("/all")
    public Response decorations(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Decorate> buildingList = decorateService.list();
        PageInfo<Decorate> pageInfo = new PageInfo<Decorate>(buildingList);
        return success(pageInfo);
    }

    @ApiOperation(value = "筛选查询",notes = "根据筛选条件查询装修方案")
    @GetMapping("/filt")
    public Response decorations(@RequestBody FiltDecorateParam param){
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        QueryWrapper<Decorate> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(param.getStyle())){
            wrapper.like("style", "%"+param.getStyle()+"%");
        }
        Double minFloorSpace = param.getMinFloorSpace();
        Double maxFloorSpace = param.getMaxFloorSpace();
        if (minFloorSpace != 0){
            wrapper.ge("floor_space", minFloorSpace);
        }
        if (maxFloorSpace != 0){
            wrapper.le("floor_space", maxFloorSpace);
        }
        Double minBudget = param.getMinBudget();
        Double maxBudget = param.getMaxBudget();
        if (minBudget != 0){
            wrapper.ge("budget", minBudget);
        }
        if (maxBudget != 0){
            wrapper.le("budget", maxBudget);
        }
        Integer rooms = param.getRooms();
        if (rooms != 0){
            wrapper.eq("rooms", rooms);
        }
        Integer roomType = param.getRoomType();
        if (roomType != 0){
            wrapper.eq("room_type", roomType);
        }
        List<Decorate> decorates = decorateService.list(wrapper);
        PageInfo<Decorate> pageInfo = new PageInfo<Decorate>(decorates);
        return success(pageInfo);
    }

    @ApiOperation(value = "上传图片",notes = "上传图片，可多张图片一起上传")
    @PostMapping("/pictures")
    public Response uploadPictures( @RequestParam("picture") List<MultipartFile> files){
        String picture = null;
        for(MultipartFile file : files){
            try {
                if(file != null){
                    String filename = file.getOriginalFilename();
                    if (!"".equals(filename.trim())){
                        System.out.println("file size:" + file.getSize());
                        String uploadUrl = constantConfig.getPREFIX() + fileService.uploadPicture(file);
                        if(picture != null){
                            picture += uploadUrl + " ";
                        }else picture = uploadUrl +" ";

                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return success(picture);
    }

    @ApiOperation(value = "新增",notes = "新增装修方案")
    @PostMapping("/")
    public Response newDecoration(@RequestBody Decorate decorate) {
        //但两个装修方案的所有图片都相同时，代表两个装修方案是一样的，不能重复插入
        QueryWrapper<Decorate> wrapper = new QueryWrapper<>();
        wrapper.eq("picture", decorate.getPicture());
        Decorate decorate1 = decorateService.getOne(wrapper);
        if (decorate1 != null) {
            return fail("该装修方案已经存在");
        } else if (decorateService.save(decorate)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation(value = "修改",notes = "修改装修方案")
    @PostMapping("/modification/{id}")
    public Response updateDecoration(@RequestBody Decorate decorate,@PathVariable int id) {
        UpdateWrapper<Decorate> wrapper = new UpdateWrapper<>();
        wrapper.eq("decorate_id", id);
        if (decorateService.update(decorate, wrapper)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation(value = "删除",notes = "删除装修方案")
    @PostMapping("/cancel/{id}")
    public Response cancelDecoration(@PathVariable int id) {
        if (decorateService.removeById(id)) {
            return success();
        }
        return fail("删除失败");
    }

    @ApiOperation(value = "查询",notes = "根据ID查询装修方案")
    @GetMapping("/{id}")
    public Response getDecoration(@PathVariable int id){
        Decorate decorate  = decorateService.getById(id);
        if (decorate == null){
            return fail("该装修方案不存在");
        }
        return success(decorate);
    }

    @ApiOperation(value = "冻结",notes = "冻结装修方案")
    @PostMapping("/freeze/{id}")
    public Response freezeDecoration(@PathVariable int id){
        Decorate decorate  = decorateService.getById(id);
        decorate.setStatus(0);
        decorateService.updateById(decorate);

        return success();
    }

    @ApiOperation(value = "解冻",notes = "解冻装修方案")
    @PostMapping("/unfreeze/{id}")
    public Response unfreezeDecoration(@PathVariable int id){
        Decorate decorate  = decorateService.getById(id);
        decorate.setStatus(1);
        decorateService.updateById(decorate);
        return success();
    }

    @ApiOperation(value = "获取装修方案评论", notes = "根据装修方案ID获取评论")
    @GetMapping("/comment/{decorateId}")
    public Response getCommentByDecorateId(@PathVariable int decorateId){
        QueryWrapper<CommentDecorate> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_id", decorateId).eq("type",1 );
        List<CommentDecorate> commentDecorates = commentDecorateService.list(wrapper);
        return success(commentDecorates);
    }

    @ApiOperation(value = "获取装修方案评论回复", notes = "根据评论ID获取评论回复")
    @GetMapping("/commentReply/{comment_id}")
    public Response getCommentReplyByDecorateId(@PathVariable int comment_id){
        QueryWrapper<RelateDecorate> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_id", comment_id);
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

    @ApiOperation(value = "获取装修方案提问", notes = "根据装修方案ID获取提问")
    @GetMapping("/question/{decorateId}")
    public Response getQuestionByDecorateId(@PathVariable int decorateId){
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.eq("", decorateId).eq("type",1 );
        List<Question> questions = questionService.list(wrapper);
        return success(questions);
    }

    @ApiOperation(value = "获取装修方案提问回复", notes = "根据提问ID获取提问回复")
    @GetMapping("/questionReply/{questionId}")
    public Response getQuestionReplyByDecorateId(@PathVariable int questionId){
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.eq("question_id", questionId).eq("type",2);
        List<Question> questions = questionService.list(wrapper);
        return success(questions);
    }

}

