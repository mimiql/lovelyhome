package org.csu.lovelyhome.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.csu.lovelyhome.service.impl.FiltDecorateServiceImpl;
import org.csu.lovelyhome.service.impl.FiltHuxingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 筛选装修方案信息日志表 前端控制器
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/filt-decorate")
@CrossOrigin
@Api(value = "过滤装修方案条件的相关API",description = "过滤装修方案条件的相关API")
public class FiltDecorateController {
    @Autowired
    FiltDecorateServiceImpl filtDecorateService;

    @ApiOperation(value = "获取装修方案相应的筛选条件信息")
    @GetMapping("/style")
    public Map<String, Integer> styleCondition(){
        List<String> styleList = filtDecorateService.getAllStyleNames();
        Map<String, Integer> map = new HashMap<>();
        for(String name : styleList){
            if(map.containsKey(name)){
                map.put(name, map.get(name) + 1);
            }else{
                map.put(name, 1);
            }
        }

        return map;
    }

    @ApiOperation(value = "获取装修方案相应的筛选条件信息")
    @GetMapping("/budget")
    public Map<String, Integer> budgetCondition(){
        List<String> budgetList = filtDecorateService.getAllBudgets();
        Map<String, Integer> map = new HashMap<>();
        for(String avePrice : budgetList){
            if(map.containsKey(avePrice)){
                map.put(avePrice, map.get(avePrice) + 1);
            }else{
                map.put(avePrice, 1);
            }
        }

        return map;
    }


    @ApiOperation(value = "获取装修方案相应的筛选条件信息")
    @GetMapping("/area")
    public Map<String, Integer> areaCondition(){
        List<String> areaList = filtDecorateService.getAllArea();
        Map<String, Integer> map = new HashMap<>();
        for(String area : areaList){
            if(map.containsKey(area)){
                map.put(area, map.get(area) + 1);
            }else{
                map.put(area, 1);
            }
        }
        return map;
    }


    @ApiOperation(value = "获取装修方案相应的筛选条件信息")
    @GetMapping("/houseType")
    public Map<String, Integer> houseTypeCondition(){
        String[] type = {"", "套房", "居室","客厅", "厨房", "浴室", "卫生间", "阳台"};
        List<Integer> houseTypeList = filtDecorateService.getAllType();
        Map<String, Integer> map = new HashMap<>();
        for(Integer t : houseTypeList){
            if(map.containsKey(type[t])){
                map.put(type[t], map.get(type[t]) + 1);
            }else{
                map.put(type[t], 1);
            }
        }

        return map;
    }

    @ApiOperation(value = "获取装修方案相应的筛选条件信息")
    @GetMapping("/rooms")
    public Map<Integer, Integer> roomsCondition(){
        List<Integer> roomsList = filtDecorateService.getAllHouseNums();
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer rooms : roomsList){
            if(map.containsKey(rooms)){
                map.put(rooms, map.get(rooms) + 1);
            }else{
                map.put(rooms, 1);
            }
        }

        return map;
    }
}

