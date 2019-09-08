package org.csu.lovelyhome.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.config.ConstantConfig;
import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.pojo.param.FiltDecorateParam;
import org.csu.lovelyhome.service.*;
import org.csu.lovelyhome.service.impl.FiltDecorateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 装修方案表 前端控制器
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/decorate")
@CrossOrigin
@Api(value = "装修方案相关API",description = "装修模块")
public class DecorateController extends BaseController {
    @Autowired
    private IDecorateService decorateService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ConstantConfig constantConfig;
    @Autowired
    private FiltDecorateServiceImpl filtDecorateService;

    @ApiOperation(value = "获取所有装修方案有分页",notes = "获取所有装修方案有分页")
    @GetMapping("/all")
    public Response decorations(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Decorate> buildingList = decorateService.list();
        PageInfo<Decorate> pageInfo = new PageInfo<Decorate>(buildingList);
        return success(pageInfo);
    }

    @ApiOperation(value = "获取所有装修方案没分页",notes = "获取所有装修方案没分页")
    @GetMapping("/allList")
    public List<Decorate> decorations(){
        return decorateService.list();
    }

    @ApiOperation(value = "筛选查询有分页，如果用户未登录user_id传个0",notes = "根据筛选条件查询装修方案")
    @GetMapping("/filt/{user_id}")
    public Response decorations(@PathVariable("user_id") int user_id, @RequestBody FiltDecorateParam param){
        FiltDecorate filtDecorate = new FiltDecorate();
        filtDecorate.setUserId(user_id);
        filtDecorate.setTime(new Date());
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        QueryWrapper<Decorate> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(param.getStyle())){
            wrapper.like("style", "%"+param.getStyle()+"%");
            filtDecorate.setStyle(param.getStyle());
        }
        Double minFloorSpace = param.getMinFloorSpace();
        Double maxFloorSpace = param.getMaxFloorSpace();
        String str = "";
        if (minFloorSpace != 0){
            wrapper.ge("floor_space", minFloorSpace);
            str += minFloorSpace + "%";
        }
        if (maxFloorSpace != 0){
            wrapper.le("floor_space", maxFloorSpace);
            str += maxFloorSpace;
        }

        filtDecorate.setFloorsSpace(str);
        Double minBudget = param.getMinBudget();
        Double maxBudget = param.getMaxBudget();
        str = "";
        if (minBudget != 0){
            wrapper.ge("budget", minBudget);
            str += minBudget + "%";
        }
        if (maxBudget != 0){
            wrapper.le("budget", maxBudget);
            str += maxBudget;
        }

        filtDecorate.setBudget(str);
        Integer rooms = param.getRooms();
        if (rooms != 0){
            wrapper.eq("rooms", rooms);
            filtDecorate.setRooms(rooms);
        }
        Integer roomType = param.getRoomType();
        if (roomType != 0){
            wrapper.eq("room_type", roomType);
            filtDecorate.setRoomType(roomType);
        }
        List<Decorate> decorates = decorateService.list(wrapper);
        PageInfo<Decorate> pageInfo = new PageInfo<Decorate>(decorates);

        if(user_id != 0){
            filtDecorateService.save(filtDecorate); //用户有登录才记录
        }

        return success(pageInfo);
    }

    @ApiOperation(value = "上传图片",notes = "上传图片，可多张图片一起上传")
    @PostMapping("/pictures")
    public Response uploadPictures( @RequestParam("picture") List<MultipartFile> files){
        String picture = null;
        for(MultipartFile file : files){
            try {
                if(file != null){
                    String filename = file.getOriginalFilename();
                    if (!"".equals(filename.trim())){
                        System.out.println("file size:" + file.getSize());
                        String uploadUrl = constantConfig.getPREFIX() + fileService.uploadPicture(file);
                        if(picture != null){
                            picture += uploadUrl + "$";
                        }else picture = uploadUrl +"$";

                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        if (picture == null){
            return fail("上传失败");
        }
        return success(picture);
    }

    @ApiOperation(value = "新增",notes = "新增装修方案")
    @PostMapping("/")
    public Response newDecoration(@RequestBody Decorate decorate) {
        //但两个装修方案的所有图片都相同时，代表两个装修方案是一样的，不能重复插入
        QueryWrapper<Decorate> wrapper = new QueryWrapper<>();
        wrapper.eq("picture", decorate.getPicture());
        Decorate decorate1 = decorateService.getOne(wrapper);
        if (decorate1 != null) {
            return fail("该装修方案已经存在");
        } else if (decorateService.save(decorate)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation(value = "修改",notes = "修改装修方案")
    @PutMapping("/modification/{id}")
    public Response updateDecoration(@RequestBody Decorate decorate,@PathVariable int id) {
        UpdateWrapper<Decorate> wrapper = new UpdateWrapper<>();
        wrapper.eq("decorate_id", id);
        if (decorateService.update(decorate, wrapper)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation(value = "删除",notes = "删除装修方案")
    @DeleteMapping("/{id}")
    public Response cancelDecoration(@PathVariable int id) {
        if (decorateService.removeById(id)) {
            return success();
        }
        return fail("删除失败");
    }

    @ApiOperation(value = "查询",notes = "根据ID查询装修方案")
    @GetMapping("/{id}")
    public Response getDecoration(@PathVariable int id){
        Decorate decorate  = decorateService.getById(id);
        if (decorate == null){
            return fail("该装修方案不存在");
        }
        return success(decorate);
    }

    @ApiOperation(value = "冻结",notes = "冻结装修方案")
    @PutMapping("/freeze/{id}")
    public Response freezeDecoration(@PathVariable int id){
        Decorate decorate  = decorateService.getById(id);
        decorate.setStatus(0);
        decorateService.updateById(decorate);

        return success("冻结成功");
    }

    @ApiOperation(value = "解冻",notes = "解冻装修方案")
    @PutMapping("/unfreeze/{id}")
    public Response unfreezeDecoration(@PathVariable int id){
        Decorate decorate  = decorateService.getById(id);
        decorate.setStatus(1);
        decorateService.updateById(decorate);
        return success("解冻成功");
    }

}

