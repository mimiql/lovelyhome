package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.lovelyhome.entity.Building;
import org.csu.lovelyhome.service.impl.BuildingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <p>
 * 楼盘信息表 前端控制器
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@RestController
@CrossOrigin
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingServiceImpl buildingService;

    @GetMapping("/{building_Id}")
    private Building building(@PathVariable("building_Id") int building_Id) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<Building>().eq("building_id", building_Id);
        return buildingService.getOne(queryWrapper);
    }


}

