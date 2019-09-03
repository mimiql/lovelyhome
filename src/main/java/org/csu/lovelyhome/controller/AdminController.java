package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.service.impl.BuildingServiceImpl;
import org.csu.lovelyhome.service.impl.DecorateServiceImpl;
import org.csu.lovelyhome.service.impl.HouseServiceImpl;
import org.csu.lovelyhome.service.impl.UserServiceImpl;
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

    @DeleteMapping("/userManage/{user_id}")
    public Response userManage(@PathVariable("user_id") int user_id){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().eq("user_id", user_id);
        userService.remove(userQueryWrapper);
        //还没写完，还要级联删除
        return success("删除成功");
    }

    @PostMapping("/buildingManage/{user_id}")
    public Response buildingPublish(@PathVariable("user_id") int user_id, Building building, List<Huxing> huxingList){
        //还不清楚怎么写
        return success("发布成功");
    }

    @DeleteMapping("/buildingManage/{building_id}")
    public Response buildingDeleting(@PathVariable("building_id") int building_id){
        //先不写
        return success("删除成功");
    }

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

    @DeleteMapping("/forumManage/commentDecorate/{id}")
    public Response commentDecorate(@PathVariable("id") int id){
        //没写
        return success("删除该评论成功！");
    }
}

