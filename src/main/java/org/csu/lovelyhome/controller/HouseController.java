package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.common.util.UploadUtil;
import org.csu.lovelyhome.entity.House;
import org.csu.lovelyhome.pojo.param.FiltHouseParam;
import org.csu.lovelyhome.service.impl.HouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 出租房信息表 前端控制器
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/house")
public class HouseController extends BaseController {

    private static final String DESTINATION = "src/main/resources/static/images/houseImages/";

    @Autowired
    private HouseServiceImpl houseService;

    @GetMapping("/houses/{keyWords}")
    public List<House> houses(@PathVariable("keyWords") String keyWords){
        QueryWrapper<House> queryWrapper = new QueryWrapper<House>();
        queryWrapper.like("name", keyWords).or().like("province", keyWords).or().like("city", keyWords)
                .or().like("district", keyWords).or().like("street", keyWords);
        return houseService.list(queryWrapper);
    }

    @GetMapping("/filterHouses/{user_id}")
    public List<House> filterHouses(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, FiltHouseParam filtHouseParam){
        if(user_id == 0){
            return houseService.getHousesByCondition(filtHouseParam);
        }else{
            return houseService.getHousesByCondition(user_id, filtHouseParam);
        }
    }

    @GetMapping("/{houseId}")
    public House house(@PathVariable("houseId") String houseId){
        QueryWrapper<House> queryWrapper = new QueryWrapper<House>();
        queryWrapper.eq("house_id", houseId);
        return houseService.getOne(queryWrapper);
    }

    @PostMapping("/{userId}")
    public Response newHouse(@PathVariable("userId") int userId, @RequestParam("files") MultipartFile[] multipartFiles, @RequestBody House house){
        house.setStatus(2);
        houseService.save(house);
        QueryWrapper<House> queryWrapper = new QueryWrapper<House>();
        queryWrapper.eq("house_id", house.getHouseId());
        StringBuilder imagePath = new StringBuilder();
        if(multipartFiles.length != 0){
            for(MultipartFile file : multipartFiles){
                UploadUtil.save(file, DESTINATION + userId + "/" + house.getHouseId() + "/");
                imagePath.append("/images/houseImages/" + userId + "/" + house.getHouseId() + "/" + file.getOriginalFilename() + " ");
            }
        }
        house.setPicture(imagePath.toString());
        houseService.update(house, queryWrapper);
        return success("待审核！");
    }
}

