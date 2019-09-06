package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
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
@RestController
@RequestMapping("/building")
@CrossOrigin
public class BuildingController extends BaseController {
    @Autowired
    private BuildingServiceImpl buildingService;
    @Autowired
    private FiltHuxingServiceImpl filtHuxingService;

    @PostMapping("/{building_Id}/comment/{commentId}/{userId}")
    public Response commentResponse(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId,
                                    @PathVariable("commentId") int commentId, @RequestBody CommentBuilding commentBuilding){
        buildingService.saveSelfResponse(building_Id, commentId, userId, commentBuilding);

        return success("回复成功！");
    }

    @PostMapping("/{building_Id}/question/{questionId}/{userId}")
    public Response questionResponse(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId,
                                 @PathVariable("questionId") int questionId, @RequestBody Question question){
        buildingService.saveOtherResponse(building_Id, questionId, userId, question);

        return success("回答成功！");
    }

    @PostMapping("/{building_Id}/question/{userId}")
    public Response question(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId, @RequestBody Question question){
        buildingService.saveQuestion(building_Id, userId, question);

        return success("提问成功，待审核！");
    }

    @PostMapping("/{building_Id}/comment/{userId}")
    public Response comment(@PathVariable("building_Id") int building_Id, @PathVariable("userId") int userId, @RequestBody CommentBuilding commentBuilding){
        buildingService.saveComment(building_Id, userId, commentBuilding);

        return success("评论成功，待审核！");
    }

    @GetMapping("/{building_Id}")
    public Building building(@PathVariable("building_Id") int building_Id) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<Building>().eq("building_id", building_Id);
        return buildingService.getOne(queryWrapper);
    }

    @GetMapping("/allList")
    public List<Building> buildings(){
        return buildingService.list();
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

    @GetMapping("/sortTimeAll")
    public List<Building> sortTimeBuilding(){
        QueryWrapper<Building> buildingQueryWrapper = new QueryWrapper<>();
        buildingQueryWrapper.orderByDesc("publish_time");
        return buildingService.list(buildingQueryWrapper);
    }

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