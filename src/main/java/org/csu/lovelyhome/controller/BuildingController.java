package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.csu.lovelyhome.entity.Building;
import org.csu.lovelyhome.service.impl.BuildingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 楼盘信息表 前端控制器
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/building")
@CrossOrigin
public class BuildingController {
    @Autowired
    private BuildingServiceImpl buildingService;

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

    @GetMapping("/buildingNames/{buildingNameKeyWords}")
    public List<String> buildingNames(@PathVariable("buildingNameKeyWords") String buildingNameKeyWords) {
        return buildingService.getBuildingNamesByKeyWords(buildingNameKeyWords);
    }
}

