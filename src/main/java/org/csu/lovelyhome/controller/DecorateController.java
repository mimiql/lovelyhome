package org.csu.lovelyhome.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.entity.Decorate;
import org.csu.lovelyhome.pojo.param.DecorationParam;
import org.csu.lovelyhome.service.FileService;
import org.csu.lovelyhome.service.IDecorateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
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
public class DecorateController extends BaseController {
    @Autowired
    private IDecorateService decorateService;
    @Autowired
    private FileService fileService;

    @GetMapping("/all")
    public Response decorations(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Decorate> buildingList = decorateService.list();
        PageInfo<Decorate> pageInfo = new PageInfo<Decorate>(buildingList);
        return success(pageInfo);
    }

    @PostMapping("/pictures")
    public Response uploadPictures( @RequestParam("pictures") List<MultipartFile> files){
        String pictures = null;
        for(MultipartFile file : files){
            try {
                if(file != null){
                    String filename = file.getOriginalFilename();
                    if (!"".equals(filename.trim())){
                        System.out.println("file size:" + file.getSize());
                        String uploadUrl = fileService.uploadPicture(file);
                        pictures += uploadUrl + " ";
                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return success();
    }

//    @PostMapping("/")
//    public Response newDecoration(@RequestBody DecorationParam decorationParam)
}

