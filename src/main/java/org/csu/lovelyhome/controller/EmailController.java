package org.csu.lovelyhome.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin
@Api(value = "邮件模块",description = "发送邮件")
public class EmailController extends BaseController {

    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "出租房成功出租，发送邮件", notes = "出租房成功出租，发送邮件")
    @PostMapping("/houseOut/{userId}/{houseId}")
    public Response houseOutToUser(@PathVariable int userId,@PathVariable int houseId){
        emailService.houseOutToUser(userId,houseId );
        return success("发送邮件成功");
    }

}
