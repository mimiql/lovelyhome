package org.csu.lovelyhome.controller;


import io.swagger.annotations.ApiOperation;
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
 * 筛选出租户型信息日志表 前端控制器
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/filt-huxing")
@CrossOrigin
public class FiltHuxingController {

    @Autowired
    FiltHuxingServiceImpl filtHuxingService;

    @ApiOperation(value = "获取户型相应的筛选条件信息")
    @GetMapping("/position")
    public Map<String, Integer> positionCondition(){
        List<String> positionList = filtHuxingService.getAllPositionNames();
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

    @ApiOperation(value = "获取户型相应的筛选条件信息")
    @GetMapping("/avePrice")
    public Map<String, Integer> avePriceCondition(){
        List<String> avePriceList = filtHuxingService.getAllAvePrices();
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


    @ApiOperation(value = "获取户型相应的筛选条件信息")
    @GetMapping("/totalPrice")
    public Map<String, Integer> totalPriceCondition(){
        List<String> totalPriceList = filtHuxingService.getAllTotalPrices();
        Map<String, Integer> map = new HashMap<>();
        for(String totalPrice : totalPriceList){
            if(map.containsKey(totalPrice)){
                map.put(totalPrice, map.get(totalPrice) + 1);
            }else{
                map.put(totalPrice, 1);
            }
        }

        return map;
    }


    @ApiOperation(value = "获取户型相应的筛选条件信息")
    @GetMapping("/area")
    public Map<String, Integer> areaCondition(){
        List<String> areaList = filtHuxingService.getAllAreas();
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
}

