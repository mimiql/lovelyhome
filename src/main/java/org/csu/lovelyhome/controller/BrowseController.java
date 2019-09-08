package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.entity.Building;
import org.csu.lovelyhome.entity.Decorate;
import org.csu.lovelyhome.entity.House;
import org.csu.lovelyhome.entity.Huxing;
import org.csu.lovelyhome.service.impl.BrowseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 浏览记录表 前端控制器
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/browse")
@CrossOrigin
@Api(value = "获取用户浏览记录进行图表统计相关API",description = "获取用户浏览记录进行图表统计相关API")
public class BrowseController {
    @Autowired
    private BrowseServiceImpl browseService;

    @ApiOperation(value = "获取楼盘的所有浏览记录(不区分用户)", notes = "浏览类型1楼盘2户型3出租房4装修方案")
    @GetMapping("/allBuildings")
    public List<Building> browsingBuilding(){
        return browseService.getBrowsingBuilding();
    }

    @ApiOperation(value = "获取租房的所有浏览记录(不区分用户)", notes = "浏览类型1楼盘2户型3出租房4装修方案")
    @GetMapping("/allHouses")
    public List<House> browsingHouse(){
        return browseService.getBrowsingHouse();
    }

    @ApiOperation(value = "获取户型的所有浏览记录(不区分用户)", notes = "浏览类型1楼盘2户型3出租房4装修方案")
    @GetMapping("/allHuxings")
    public List<Huxing> browsingHuxing(){
        return browseService.getBrowsingHuxing();
    }

    @ApiOperation(value = "获取装修方案的所有浏览记录(不区分用户)", notes = "浏览类型1楼盘2户型3出租房4装修方案")
    @GetMapping("/allDecorates")
    public List<Decorate> browsingDecorate(){
        return browseService.getBrowsingDecoration();
    }
}

