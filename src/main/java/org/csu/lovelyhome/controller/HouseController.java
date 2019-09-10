package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.common.util.UploadUtil;
import org.csu.lovelyhome.entity.Building;
import org.csu.lovelyhome.entity.House;
import org.csu.lovelyhome.pojo.param.FiltHouseParam;
import org.csu.lovelyhome.pojo.param.HouseParam;
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
@CrossOrigin
@Api(value = "租房的相关API",description = "租房的相关API")
public class HouseController extends BaseController {

    private static final String DESTINATION = "src/main/resources/static/images/houseImages/";

    @Autowired
    private HouseServiceImpl houseService;

    @ApiOperation(value = "根据关键字获取租房没分页", notes = "根据关键字获取租房没分页")
    @GetMapping("/houses/{keyWords}")
    public List<House> houses(@PathVariable("keyWords") String keyWords){
        QueryWrapper<House> queryWrapper = new QueryWrapper<House>();
        queryWrapper.like("name", keyWords).or().like("province", keyWords).or().like("city", keyWords)
                .or().like("district", keyWords).or().like("street", keyWords);
        return houseService.list(queryWrapper);
    }

    @ApiOperation(value = "根据关键字获取租房有分页", notes = "根据关键字获取租房有分页")
    @GetMapping("/housesList/{keyWords}")
    public PageInfo<House> houses(@PathVariable("keyWords") String keyWords, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        QueryWrapper<House> queryWrapper = new QueryWrapper<House>();
        queryWrapper.like("name", keyWords).or().like("province", keyWords).or().like("city", keyWords)
                .or().like("district", keyWords).or().like("street", keyWords);

        PageHelper.startPage(pageNum,5);
        List<House> houseList = houseService.list(queryWrapper);
        return new PageInfo<>(houseList);
    }

    @ApiOperation(value = "获取租所有房有分页", notes = "获取所有租房有分页")
    @GetMapping("/housesList")
    public PageInfo<House> houses(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<House> houseList = houseService.list();
        return new PageInfo<>(houseList);
    }

    @ApiOperation(value = "筛选租房，如果未登录user_id为0", notes = "筛选租房，如果未登录user_id为0")
    @GetMapping("/filterHouses/{user_id}")
    public PageInfo<House> filterHouses(@PathVariable("user_id") int user_id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, FiltHouseParam filtHouseParam){
        if(user_id == 0){
            PageHelper.startPage(pageNum,5);
            List<House> houseList = houseService.getHousesByCondition(filtHouseParam);
            PageInfo<House> pageInfo = new PageInfo<House>(houseList);
            return pageInfo;
        }else{
            PageHelper.startPage(pageNum,5);
            List<House> houseList = houseService.getHousesByCondition(user_id, filtHouseParam);
            PageInfo<House> pageInfo = new PageInfo<House>(houseList);
            return pageInfo;
        }
    }

    @ApiOperation(value = "根据租房Id获取租房", notes = "根据租房Id获取租房")
    @GetMapping("/{houseId}")
    public House house(@PathVariable("houseId") String houseId){
        QueryWrapper<House> queryWrapper = new QueryWrapper<House>();
        queryWrapper.eq("house_id", houseId);
        return houseService.getOne(queryWrapper);
    }

    @ApiOperation(value = "用户申请租房,成功后要获取图片还要在前面加上http到8080的前缀", notes = "用户申请租房")
    @PostMapping("/{userId}")
    public Response newHouse(@PathVariable("userId") int userId, HouseParam houseParam){
        House house = houseParam.getHouse();
        if(house == null){
            return fail("租房信息为空！");
        }
        house.setUserId(userId);
        house.setStatus(2);
        houseService.save(house);
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("house_id", house.getHouseId());
        StringBuilder imagePath = new StringBuilder();
        MultipartFile[] multipartFiles = houseParam.getFiles();
        if(multipartFiles != null){
            if(multipartFiles.length != 0){
                for(MultipartFile file : multipartFiles){
                    UploadUtil.save(file, DESTINATION + userId + "/" + house.getHouseId() + "/");
                    imagePath.append("/images/houseImages/" + userId + "/" + house.getHouseId() + "/" + file.getOriginalFilename() + " ");
                }
            }
            house.setPicture(imagePath.toString());
            houseService.update(house, queryWrapper);
        }
        return success("待审核！");
    }
}

