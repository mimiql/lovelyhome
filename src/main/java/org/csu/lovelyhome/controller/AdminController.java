package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
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
 * @author lqm
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/admin")
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
}

