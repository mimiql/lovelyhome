package org.csu.lovelyhome.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.service.impl.FiltHouseServiceImpl;
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
 * 筛选出租房信息日志表 前端控制器
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/filt-house")
@CrossOrigin
@Api(value = "过滤租房条件的相关API",description = "过滤租房条件的相关API")
public class FiltHouseController {
    @Autowired
    FiltHouseServiceImpl filtHouseService;

    @ApiOperation(value = "获取租房相应的筛选条件信息")
    @GetMapping("/position")
    public Map<String, Integer> positionCondition(){
        List<String> positionList = filtHouseService.getAllPositionNames();
        Map<String, Integer> map = new HashMap<>();
        for(String name : positionList){
            if(map.containsKey(name)){
                map.put(name, map.get(name) + 1);
            }else{
                map.put(name, 1);
            }
        }

        return map;
    }

    @ApiOperation(value = "获取租房相应的筛选条件信息")
    @GetMapping("/orientation")
    public Map<String, Integer> orientationCondition(){
        List<String> orientationList = filtHouseService.getAllOrientions();
        Map<String, Integer> map = new HashMap<>();
        for(String orientation : orientationList){
            if(map.containsKey(orientation)){
                map.put(orientation, map.get(orientation) + 1);
            }else{
                map.put(orientation, 1);
            }
        }

        return map;
    }


    @ApiOperation(value = "获取租房相应的筛选条件信息")
    @GetMapping("/area")
    public Map<String, Integer> areaCondition(){
        List<String> areaList = filtHouseService.getAllAreas();
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


    @ApiOperation(value = "获取租房相应的筛选条件信息")
    @GetMapping("/avePrice")
    public Map<String, Integer> avePriceCondition(){
        List<String> avePriceList = filtHouseService.getAllAvePrices();
        Map<String, Integer> map = new HashMap<>();
        for(String avePrice : avePriceList){
            if(map.containsKey(avePrice)){
                map.put(avePrice, map.get(avePrice) + 1);
            }else{
                map.put(avePrice, 1);
            }
        }

        return map;
    }

    @ApiOperation(value = "获取租房相应的筛选条件信息")
    @GetMapping("/config")
    public Map<String, Integer> configCondition(){
        List<String> configList = filtHouseService.getAllConfigs();
        Map<String, Integer> map = new HashMap<>();
        for(String config : configList){
            if(map.containsKey(config)){
                map.put(config, map.get(config) + 1);
            }else{
                map.put(config, 1);
            }
        }
        return map;
    }
}

