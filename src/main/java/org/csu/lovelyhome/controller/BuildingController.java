package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.apache.commons.collections4.Get;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.config.TagConstant;
import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.pojo.param.FiltBuildingParam;
import org.csu.lovelyhome.pojo.vo.BuildingInformationVo;
import org.csu.lovelyhome.pojo.vo.BuildingTagNumVO;
import org.csu.lovelyhome.service.impl.BrowseServiceImpl;
import org.csu.lovelyhome.service.impl.BuildingServiceImpl;
import org.csu.lovelyhome.service.impl.FiltHuxingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 楼盘信息表 前端控制器
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
@Api(value = "楼盘相关API",description = "楼盘模块")
@RestController
@RequestMapping("/building")
@CrossOrigin
public class BuildingController extends BaseController {
    @Autowired
    private BuildingServiceImpl buildingService;
    @Autowired
    private FiltHuxingServiceImpl filtHuxingService;
    @Autowired
    private BrowseServiceImpl browseService;

    @ApiOperation(value = "回复楼盘评论", notes = "用户对某楼盘下的评论进行回复")
    @PostMapping("/{building_Id}/comment/{commentId}/{userId}")
    public Response commentResponse(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId,
                                    @PathVariable("commentId") int commentId, @RequestBody CommentBuilding commentBuilding){
        buildingService.saveSelfResponse(building_Id, commentId, userId, commentBuilding);

        return success("回复成功！");
    }

    @ApiOperation(value = "回答楼盘问题", notes = "用户对某楼盘下的问题进行回答")
    @PostMapping("/{building_Id}/question/{questionId}/{userId}")
    public Response questionResponse(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId,
                                 @PathVariable("questionId") int questionId, @RequestBody Question question){
        buildingService.saveOtherResponse(building_Id, questionId, userId, question);

        return success("回答成功！");
    }

    @ApiOperation(value = "在楼盘提问", notes = "用户在楼盘提问")
    @PostMapping("/{building_Id}/question/{userId}")
    public Response question(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId, @RequestBody Question question){
        buildingService.saveQuestion(building_Id, userId, question);

        return success("提问成功，待审核！");
    }

    @ApiOperation(value = "在楼盘评论", notes = "用户在楼盘评论")
    @PostMapping("/{building_Id}/comment/{userId}")
    public Response comment(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId, @RequestBody CommentBuilding commentBuilding){
        buildingService.saveComment(building_Id, userId, commentBuilding);

        return success("评论成功，待审核！");
    }

    @ApiOperation(value = "根据楼盘Id获取楼盘信息", notes = "根据楼盘Id获取楼盘信息")
    @GetMapping("/{building_Id}")
    public Building building(@PathVariable("building_Id") int building_Id, @RequestParam(defaultValue = "0",value = "user_id") int user_id) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<Building>().eq("building_id", building_Id);
        Building building = buildingService.getOne(queryWrapper);
        //用户有登录就记录下它的浏览
        if(user_id != 0){
            browseService.save(user_id, building.getBuildingId(), 1);
        }
        return building;
    }

    @ApiOperation(value = "获取地区名所有楼盘", notes = "根据地区名获取所有楼盘")
    @GetMapping("/allDistricts")
    public List<Building> buildingDistricts(@RequestParam("city") String city){
        QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city", city);

        return buildingService.list(queryWrapper);
    }

    @ApiOperation(value = "获取所有楼盘信息后端不分页", notes = "获取所有楼盘信息后端不分页")
    @GetMapping("/allList")
    public List<Building> buildings(){
        return buildingService.list();
    }

    @ApiOperation(value = "获取所有楼盘信息后端分页", notes = "获取所有楼盘信息后端分页")
    @GetMapping("/all")
    public PageInfo<Building> buildings(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Building> buildingList = buildingService.list();
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);

        return pageInfo;
    }

    @ApiOperation(value = "根据价格对楼盘进行排序", notes = "根据价格对楼盘进行排序")
    @GetMapping("/sortPriceAll")
    public PageInfo<Building> sortPriceBuildings(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Building> buildingList = buildingService.getAllBuildingsByPrice();
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);

        return pageInfo;
    }

    @ApiOperation(value = "根据时间对楼盘进行排序", notes = "根据时间对楼盘进行排序")
    @GetMapping("/sortTimeAll")
    public List<Building> sortTimeBuilding(){
        QueryWrapper<Building> buildingQueryWrapper = new QueryWrapper<>();
        buildingQueryWrapper.orderByDesc("publish_time");
        return buildingService.list(buildingQueryWrapper);
    }

    @ApiOperation(value = "对某用户在楼盘下的筛选条件进行记录，若未登录user_id传个0", notes = "对某用户在楼盘下的筛选条件进行记录，若未登录user_id传个0")
    @GetMapping("/filterBuildings/{user_id}")
    public PageInfo<Building> filterBuildings(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,FiltBuildingParam filtBuildingParam){
        FiltHuxing filtHuxing = new FiltHuxing();
        filtHuxing.setTime(new Date());
        filtHuxing.setUserId(user_id);
        if(filtBuildingParam.getPosition() != null){
            filtHuxing.setAddress(filtBuildingParam.getPosition());
        }

        String str = "";
        if(filtBuildingParam.getMinUnitPrice() != null){
            str += filtBuildingParam.getMinUnitPrice() + "%";
        }

        if(filtBuildingParam.getMaxUnitPrice() != null){
            str += filtBuildingParam.getMaxUnitPrice();
        }

        filtHuxing.setAvePrice(str);
        str = "";
        if(filtBuildingParam.getMinTotalPrice() != null){
            str += filtBuildingParam.getMinTotalPrice() + "%";
        }

        if(filtBuildingParam.getMaxTotalPrice() != null){
            str += filtBuildingParam.getMaxTotalPrice();
        }

        filtHuxing.setTotalPrice(str);

        str = "";
        if(filtBuildingParam.getMinArea() != null){
            str += filtBuildingParam.getMinArea() + "%";
        }

        if(filtBuildingParam.getMaxArea() != null){
            str += filtBuildingParam;
        }

        filtHuxing.setFloorsSpace(str);

        if(filtBuildingParam.getNumOfhuxing() != null){
            filtHuxing.setRooms(filtBuildingParam.getNumOfhuxing());
        }

        if(user_id != 0){
            filtHuxingService.save(filtHuxing); //用户有登录才记录
        }

        PageHelper.startPage(pageNum,5);
        List<Building> buildingList = buildingService.getAllBuildingsByCondition(filtBuildingParam);
        PageInfo<Building> pageInfo = new PageInfo<Building>(buildingList);

        return pageInfo;
    }

    @ApiOperation(value = "根据关键词自动补全提示所有相关内容", notes = "根据关键词自动补全提示所有相关内容")
    @GetMapping("/buildingNames/{buildingNameKeyWords}")
    public List<String> buildingNames(@PathVariable("buildingNameKeyWords") String buildingNameKeyWords) {
        return buildingService.getBuildingNamesByKeyWords(buildingNameKeyWords);
    }

    @ApiOperation(value = "根据关键词搜索所有楼盘信息", notes = "根据关键词搜索所有楼盘信息")
    @GetMapping("/buildings/{keyWords}")
    public List<Building> buildings(@PathVariable("keyWords") String keyWords){
        QueryWrapper<Building> queryWrapper = new QueryWrapper<Building>();
        queryWrapper.like("name", keyWords).or().like("province", keyWords).or().like("city", keyWords)
        .or().like("district", keyWords).or().like("street", keyWords);
        return buildingService.list(queryWrapper);
    }

    @ApiOperation(value = "根据楼盘ID获取整个楼盘页面的展示信息", notes = "根据楼盘ID获取整个楼盘页面的展示信息")
    @GetMapping("/{building_Id}/buildingDetail")
    public BuildingDetail buildingDetail(@PathVariable("building_Id") int building_Id){
        return buildingService.getBuildingDetailByBuildingId(building_Id);
    }

    @ApiOperation(value = "根据楼盘ID获取该楼盘的所有户型", notes = "根据楼盘ID获取该楼盘的所有户型")
    @GetMapping("/{building_Id}/huxings")
    public List<Huxing> huxings(@PathVariable("building_Id") int building_Id){
        return buildingService.getAllHuXingByBuildingId(building_Id);
    }

    @ApiOperation(value = "根据楼盘ID和户型ID获取该户型信息", notes = "根据楼盘ID和户型ID获取该户型信息")
    @GetMapping("/{building_Id}/huxing/{huxing_Id}")
    public Huxing huxing(@PathVariable("building_Id") int building_Id, @PathVariable("huxing_Id") int huxing_Id){
        return buildingService.getHuXingByBuildingIdAndHuxingId(building_Id, huxing_Id);
    }

    @ApiOperation(value = "获取某楼盘某评论下的所有回复", notes = "获取某楼盘某评论下的所有回复")
    @GetMapping("/{building_Id}/comment/{comment_Id}/responses")
    public List<CommentBuilding> commentResponses(@PathVariable("building_Id") int building_Id, @PathVariable("comment_Id") int comment_Id){
        return buildingService.getResponsesByCommentId(comment_Id);
    }

    @ApiOperation(value = "获取某楼盘某问题下的所有回复", notes = "获取某楼盘某问题下的所有回复")
    @GetMapping("/{building_Id}/question/{question_Id}/responses")
    public List<Question> questionResponses(@PathVariable("building_Id") int building_Id, @PathVariable("question_Id") int question_Id){
        return buildingService.getResponsesByQuestionId(question_Id);
    }

    @ApiOperation(value = "发布的楼盘信息", notes = "获取发布的楼盘信息")
    @GetMapping("/{building_Id}/buildingInformation}")
    public BuildingInformationVo buildingInformationVo(@PathVariable("building_Id") int building_Id){
        return buildingService.getBuildingInformationById(building_Id);
    }

    @ApiOperation(value = "返回楼盘六个标签的各个数目",notes = "返回楼盘六个标签的各个数目")
    @GetMapping("tag/num")
    public  Response  getTagNum(){
        List<BuildingTagNumVO> buildingTagNumVOS = new ArrayList<>();
        BuildingTagNumVO buildingTagNumVO1 = new BuildingTagNumVO();
        buildingTagNumVO1.setTag(TagConstant.getCONVENIENTTRAFFIC());
        buildingTagNumVO1.setNum(buildingService.getTagNum(buildingTagNumVO1.getTag()));
        buildingTagNumVOS.add(buildingTagNumVO1);

        BuildingTagNumVO buildingTagNumVO2 = new BuildingTagNumVO();
        buildingTagNumVO2.setTag(TagConstant.getDININGANDDREWING());
        buildingTagNumVO2.setNum(buildingService.getTagNum(buildingTagNumVO2.getTag()));
        buildingTagNumVOS.add(buildingTagNumVO2);

        BuildingTagNumVO buildingTagNumVO3 = new BuildingTagNumVO();
        buildingTagNumVO3.setTag(TagConstant.getFULLENTERAINMENT());
        buildingTagNumVO3.setNum(buildingService.getTagNum(buildingTagNumVO3.getTag()));
        buildingTagNumVOS.add(buildingTagNumVO3);

        BuildingTagNumVO buildingTagNumVO4 = new BuildingTagNumVO();
        buildingTagNumVO4.setTag(TagConstant.getINANYTIME());
        buildingTagNumVO4.setNum(buildingService.getTagNum(buildingTagNumVO4.getTag()));
        buildingTagNumVOS.add(buildingTagNumVO4);

        BuildingTagNumVO buildingTagNumVO5 = new BuildingTagNumVO();
        buildingTagNumVO5.setTag(TagConstant.getNOPARKING());
        buildingTagNumVO5.setNum(buildingService.getTagNum(buildingTagNumVO5.getTag()));
        buildingTagNumVOS.add(buildingTagNumVO5);

        BuildingTagNumVO buildingTagNumVO6 = new BuildingTagNumVO();
        buildingTagNumVO6.setTag(TagConstant.getTWOLIESOUTH());
        buildingTagNumVO6.setNum(buildingService.getTagNum(buildingTagNumVO6.getTag()));
        buildingTagNumVOS.add(buildingTagNumVO6);

        return success(buildingTagNumVOS);
    }

    @ApiOperation(value = "根据户型标签获取楼盘",notes = "返回户型存在该标签的楼盘,注意标签时字符串传进来的")
    @GetMapping("Building/tag/{tag}")
    public Response getTagBuildings(@PathVariable String tag){
        return success(buildingService.getTagBuildings(tag));
    }
}