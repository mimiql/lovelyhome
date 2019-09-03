package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.pojo.param.FiltBuildingParam;
import org.csu.lovelyhome.service.impl.BuildingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 楼盘信息表 前端控制器
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/building")
@CrossOrigin
public class BuildingController extends BaseController {
    @Autowired
    private BuildingServiceImpl buildingService;

    @PostMapping("/{building_Id}/comment/{commentId}/{userId}")
    public Response commentResponse(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId,
                                    @PathVariable("commentId") int commentId, @RequestParam("content") String content){
        buildingService.saveSelfResponse(building_Id, commentId, userId, content);

        return success("回复成功");
    }

    @PostMapping("/{building_Id}/question/{questionId}/{userId}")
    public Response questionResponse(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId,
                                 @PathVariable("questionId") int questionId, @RequestParam("content") String content){
        buildingService.saveOtherResponse(building_Id, questionId, userId, content);

        return success("回答成功！");
    }

    @PostMapping("/{building_Id}/question/{userId}")
    public Response question(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId, @RequestParam("content") String content){
        buildingService.saveQuestion(building_Id, userId, content);

        return success("提问成功！");
    }

    @PostMapping("/{building_Id}/comment/{userId}")
    public Response comment(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId, @RequestParam("content") String content){
        buildingService.saveComment(building_Id, userId, content);

        return success("评论成功！");
    }

    @GetMapping("/{building_Id}")
    public Building building(@PathVariable("building_Id") int building_Id) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<Building>().eq("building_id", building_Id);
        return buildingService.getOne(queryWrapper);
    }

    @GetMapping("/all")
    public PageInfo<Building> buildings(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Building> buildingList = buildingService.list();
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);

        return pageInfo;
    }

    @GetMapping("/sortPriceAll")
    public PageInfo<Building> sortPriceBuildings(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Building> buildingList = buildingService.getAllBuildingsByPrice();
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);

        return pageInfo;
    }

    @GetMapping("/filterBuildings")
    public PageInfo<Building> filterBuildings(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestBody FiltBuildingParam filtBuildingParam){
        PageHelper.startPage(pageNum,5);
        List<Building> buildingList = buildingService.getAllBuildingsByCondition(filtBuildingParam);
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);

        return pageInfo;
    }

    @GetMapping("/buildingNames/{buildingNameKeyWords}")
    public List<String> buildingNames(@PathVariable("buildingNameKeyWords") String buildingNameKeyWords) {
        return buildingService.getBuildingNamesByKeyWords(buildingNameKeyWords);
    }

    @GetMapping("/buildings/{keyWords}")
    public List<Building> buildings(@PathVariable("keyWords") String keyWords){
        QueryWrapper<Building> queryWrapper = new QueryWrapper<Building>();
        queryWrapper.like("name", keyWords).or().like("province", keyWords).or().like("city", keyWords)
        .or().like("district", keyWords).or().like("street", keyWords);
        return buildingService.list(queryWrapper);
    }

    @GetMapping("/{building_Id}/buildingDetail")
    public BuildingDetail buildingDetail(@PathVariable("building_Id") int building_Id){
        return buildingService.getBuildingDetailByBuildingId(building_Id);
    }

    @GetMapping("/{building_Id}/huxings")
    public List<Huxing> huxings(@PathVariable("building_Id") int building_Id){
        return buildingService.getAllHuXingByBuildingId(building_Id);
    }

    @GetMapping("/{building_Id}/huxing/{huxing_Id}")
    public Huxing huxing(@PathVariable("building_Id") int building_Id, @PathVariable("huxing_Id") int huxing_Id){
        return buildingService.getHuXingByBuildingIdAndHuxingId(building_Id, huxing_Id);
    }

    @GetMapping("/{building_Id}/comment/{comment_Id}/responses")
    public List<CommentBuilding> commentResponses(@PathVariable("building_Id") int building_Id, @PathVariable("comment_Id") int comment_Id){
        return buildingService.getResponsesByCommentId(comment_Id);
    }

    @GetMapping("/{building_Id}/question/{question_Id}/responses")
    public List<Question> questionResponses(@PathVariable("building_Id") int building_Id, @PathVariable("question_Id") int question_Id){
        return buildingService.getResponsesByQuestionId(question_Id);
    }
}