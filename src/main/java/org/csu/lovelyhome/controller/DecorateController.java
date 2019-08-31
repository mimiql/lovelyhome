package org.csu.lovelyhome.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.csu.lovelyhome.entity.Decorate;
import org.csu.lovelyhome.service.IDecorateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/lovelyhome/decorate")
@CrossOrigin
public class DecorateController {
    @Autowired
    private IDecorateService decorateService;

    @GetMapping("/all")
    public PageInfo<Decorate> decorations(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Decorate> buildingList = decorateService.list();
        PageInfo<Decorate> pageInfo = new PageInfo<Decorate>(buildingList);

        return pageInfo;
    }
}

