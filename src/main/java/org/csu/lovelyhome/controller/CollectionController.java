package org.csu.lovelyhome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.entity.Collection;
import org.csu.lovelyhome.service.IBuildingService;
import org.csu.lovelyhome.service.ICollectionService;
import org.csu.lovelyhome.service.IHouseService;
import org.csu.lovelyhome.service.IHuxingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户收藏表 前端控制器
 * </p>
 *
 * @author lqm
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
    public void buildingCollection(@PathVariable("user_id") int user_id, @PathVariable("buildingId") int buildingId){
        Collection collection = new Collection();
        collection.setObjectId(buildingId);
        collection.setType(1);
        collection.setUserId(user_id);
        collection.setTime(new Date());

        collectionService.save(collection);
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

