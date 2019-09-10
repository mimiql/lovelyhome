package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.pojo.vo.CollectionBuildingVO;
import org.csu.lovelyhome.pojo.vo.CollectionDecorationVO;
import org.csu.lovelyhome.pojo.vo.CollectionHouseVO;
import org.csu.lovelyhome.pojo.vo.CollectionHuxingVO;
import org.csu.lovelyhome.service.IBuildingService;
import org.csu.lovelyhome.service.ICollectionService;
import org.csu.lovelyhome.service.IHouseService;
import org.csu.lovelyhome.service.IHuxingService;
import org.csu.lovelyhome.service.impl.CollectionServiceImpl;
import org.csu.lovelyhome.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户收藏表 前端控制器
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/collection")
@CrossOrigin
@Api(value = "收藏模块相关API", description = "收藏模块相关API")
public class CollectionController extends BaseController {

    @Autowired
    private ICollectionService collectionService;
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private IHouseService houseService;
    @Autowired
    private IHuxingService huxingService;
    @Autowired
    private UserServiceImpl userService;

    @ApiOperation(value = "根据收藏ID删除收藏", notes = "根据收藏ID删除收藏")
    @DeleteMapping("/{collectionId}")
    public Response deletingCollectionId(@PathVariable("collectionId") int collectionId){
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("collect_id",collectionId);
        CollectionServiceImpl collectionService = new CollectionServiceImpl();
        collectionService.remove(queryWrapper);

        return success("删除成功!");
    }

    @ApiOperation(value = "获取楼盘的收藏视图",notes = "获取楼盘的收藏视图")
    @GetMapping("/buildingVO/{userId}")
    public PageInfo<CollectionBuildingVO> collectionBuildingVO(@PathVariable int userId, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){

        PageHelper.startPage(pageNum,4);
        List<CollectionBuildingVO> collectionBuildingVOList = new ArrayList<>();
        for(Building building : userService.getCollectionBuildingByUserId(userId)){
            QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("object_id", building.getBuildingId());
            queryWrapper.eq("type", 1);
            Collection collection = collectionService.getOne(queryWrapper);
            CollectionBuildingVO collectionBuildingVO = new CollectionBuildingVO();
            collectionBuildingVO.setBuilding(building);
            collectionBuildingVO.setCollection(collection);
            collectionBuildingVOList.add(collectionBuildingVO);
        }
        PageInfo<CollectionBuildingVO> pageInfo = new PageInfo<CollectionBuildingVO>(collectionBuildingVOList);

        return pageInfo;
    }

    @ApiOperation(value = "获取户型的收藏视图",notes = "获取户型的收藏视图")
    @GetMapping("/huxingVO/{userId}")
    public PageInfo<CollectionHuxingVO> collectionHuxingVO(@PathVariable int userId, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<CollectionHuxingVO> collectionHuxingVOList = new ArrayList<>();
        for(Huxing huxing : userService.getCollectionHuxingByUserId(userId)){
            QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("object_id", huxing.getHuxingId());
            queryWrapper.eq("type", 2);
            Collection collection = collectionService.getOne(queryWrapper);

            QueryWrapper<Building> buildingQueryWrapper = new QueryWrapper<>();
            buildingQueryWrapper.eq("building_id", huxing.getBuildingId());
            Building building = buildingService.getOne(buildingQueryWrapper);
            CollectionHuxingVO collectionHuxingVO = new CollectionHuxingVO();
            collectionHuxingVO.setHuxing(huxing);
            collectionHuxingVO.setCollection(collection);
            collectionHuxingVO.setBuilding(building);
            collectionHuxingVOList.add(collectionHuxingVO);
        }
        PageInfo<CollectionHuxingVO> pageInfo = new PageInfo<CollectionHuxingVO>(collectionHuxingVOList);

        return pageInfo;
    }

    @ApiOperation(value = "获取租房的收藏视图",notes = "获取租房的收藏视图")
    @GetMapping("/houseVO/{userId}")
    public PageInfo<CollectionHouseVO> collectionHouseVO(@PathVariable int userId, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<CollectionHouseVO> collectionHouseVOList = new ArrayList<>();
        for(House house : userService.getCollectionHouseByUserId(userId)){
            QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("object_id", house.getHouseId());
            queryWrapper.eq("type", 3);
            Collection collection = collectionService.getOne(queryWrapper);
            CollectionHouseVO collectionHouseVO = new CollectionHouseVO();
            collectionHouseVO.setHouse(house);
            collectionHouseVO.setCollection(collection);
            collectionHouseVOList.add(collectionHouseVO);
        }
        PageInfo<CollectionHouseVO> pageInfo = new PageInfo<CollectionHouseVO>(collectionHouseVOList);
        return pageInfo;
    }

    @ApiOperation(value = "获取装修方案的收藏视图",notes = "获取装修方案的收藏视图")
    @GetMapping("/decorationVO/{userId}")
    public PageInfo<CollectionDecorationVO> collectionDecorationVO(@PathVariable int userId, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<CollectionDecorationVO> collectionDecorationVOList = new ArrayList<>();
        for(Decorate decorate : userService.getCollectionDecorateByUserId(userId)){
            QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("object_id", decorate.getDecorateId());
            queryWrapper.eq("type", 4);
            Collection collection = collectionService.getOne(queryWrapper);
            CollectionDecorationVO collectionDecorationVO = new CollectionDecorationVO();
            collectionDecorationVO.setDecorate(decorate);
            collectionDecorationVO.setCollection(collection);
            collectionDecorationVOList.add(collectionDecorationVO);
        }
        PageInfo<CollectionDecorationVO> pageInfo = new PageInfo<CollectionDecorationVO>(collectionDecorationVOList);

        return pageInfo;
    }

    @ApiOperation(value = "获取楼盘的收藏时间",notes = "获取楼盘的收藏时间")
    @GetMapping("/building/{userId}/{buildingId}")
    public Collection collectionBuilding(@PathVariable int userId,@PathVariable int buildingId){
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("object_id", buildingId);
        queryWrapper.eq("type", 1);

        return collectionService.getOne(queryWrapper);
    }

    @ApiOperation(value = "获取户型的收藏时间",notes = "获取户型的收藏时间")
    @GetMapping("/huxing/{userId}/{huxingId}")
    public Collection collectionHuxing(@PathVariable int userId,@PathVariable int huxingId){
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("object_id", huxingId);
        queryWrapper.eq("type", 2);

        return collectionService.getOne(queryWrapper);
    }

    @ApiOperation(value = "获取租房的收藏时间",notes = "获取租房的收藏时间")
    @GetMapping("/house/{userId}/{houseId}")
    public Collection collectionHouse(@PathVariable int userId,@PathVariable int houseId){
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("object_id", houseId);
        queryWrapper.eq("type", 3);

        return collectionService.getOne(queryWrapper);
    }

    @ApiOperation(value = "获取装修方案的收藏时间",notes = "获取装修方案的收藏时间")
    @GetMapping("/decoration/{userId}/{decorationId}")
    public Collection collectionDecoration(@PathVariable int userId,@PathVariable int decorationId){
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("object_id", decorationId);
        queryWrapper.eq("type", 4);

        return collectionService.getOne(queryWrapper);
    }

    @ApiOperation(value = "收藏",notes = "用户收藏装修方案")
    @PostMapping("/decorate/{userId}/{decorateId}")
    public Response newCollecte(@PathVariable int userId,@PathVariable int decorateId){
        Collection collection = new Collection();
        collection.setObjectId(decorateId);
        collection.setUserId(userId);
        collection.setTime(new Date());
        collection.setType(4);
        if(collectionService.save(collection)){
            return fail("收藏失败");
        }
        return success("收藏成功");
    }

    @ApiOperation(value = "获取用户收藏装修方案",notes = "根据用户id获取用户收藏装修方案")
    @GetMapping("/decorate/{userId}")
    public Response getCollecteDecorate(@PathVariable int userId){
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).eq("type",4 );
        List<Collection> collections = collectionService.list(wrapper);
        return success(collections);
    }

    @ApiOperation(value = "某用户点击收藏了某楼盘",notes = "某用户点击收藏了某楼盘")
    @PostMapping("/{user_id}/buildingCollection/{buildingId}")
    public Response buildingCollection(@PathVariable("user_id") int user_id, @PathVariable("buildingId") int buildingId){
        Collection collection = new Collection();
        collection.setObjectId(buildingId);
        collection.setType(1);
        collection.setUserId(user_id);
        collection.setTime(new Date());

        collectionService.save(collection);
        return  success("收藏成功");
    }

    @ApiOperation(value = "某用户点击收藏了某户型",notes = "某用户点击收藏了某户型")
    @PostMapping("/{user_id}/huxingCollection/{huxingId}")
    public void huxingCollection(@PathVariable("user_id") int user_id, @PathVariable("huxingId") int huxingId){
        Collection collection = new Collection();
        collection.setObjectId(huxingId);
        collection.setType(2);
        collection.setUserId(user_id);
        collection.setTime(new Date());

        collectionService.save(collection);
    }

    @ApiOperation(value = "某用户点击收藏了某租房",notes = "某用户点击收藏了某租房")
    @PostMapping("/{user_id}/houseCollection/{houseId}")
    public void houseCollection(@PathVariable("user_id") int user_id, @PathVariable("houseId") int houseId){
        Collection collection = new Collection();
        collection.setObjectId(houseId);
        collection.setType(3);
        collection.setUserId(user_id);
        collection.setTime(new Date());

        collectionService.save(collection);
    }

    @ApiOperation(value = "取消收藏楼盘",notes = "用户取消收藏楼盘")
    @DeleteMapping("/building/{userId}/{buildingId}")
    public Response deleteBuildingDecorate(@PathVariable int userId,@PathVariable int buildingId){
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId );
        wrapper.eq("object_id", buildingId);
        wrapper.eq("type",1);
        if(collectionService.remove(wrapper)){
            return fail("取消收藏失败");
        }
        return success("取消收藏成功");
    }

    @ApiOperation(value = "取消收藏户型",notes = "用户取消收藏户型")
    @DeleteMapping("/huxing/{userId}/{huxingId}")
    public Response deleteCollecteHuxing(@PathVariable int userId,@PathVariable int huxingId){
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId );
        wrapper.eq("object_id", huxingId);
        wrapper.eq("type",2);
        if(collectionService.remove(wrapper)){
            return fail("取消收藏失败");
        }
        return success("取消收藏成功");
    }

    @ApiOperation(value = "取消收藏出租房",notes = "用户取消收藏出租房")
    @DeleteMapping("/decorahousete/{userId}/{houseId}")
    public Response deleteCollecteHouse(@PathVariable int userId,@PathVariable int houseId){
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId );
        wrapper.eq("object_id", houseId);
        wrapper.eq("type",3 );
        if(collectionService.remove(wrapper)){
            return fail("取消收藏失败");
        }
        return success("取消收藏成功");
    }

    @ApiOperation(value = "取消收藏装修方案",notes = "用户取消收藏装修方案")
    @DeleteMapping("/decorate/{userId}/{decorateId}")
    public Response deleteCollecteDecorate(@PathVariable int userId,@PathVariable int decorateId){
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId );
        wrapper.eq("object_id", decorateId);
        wrapper.eq("type",4 );
        if(collectionService.remove(wrapper)){
            return fail("取消收藏失败");
        }
        return success("取消收藏成功");
    }

}

