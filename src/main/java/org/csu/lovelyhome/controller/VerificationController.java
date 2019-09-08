package org.csu.lovelyhome.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.common.util.RandomValidateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码
 * @author zjx
 */
@RestController
@CrossOrigin
@RequestMapping("/verification")
@Api(value = "验证码相关API",description = "验证码相关API")
public class VerificationController {

    /**
     * 生成验证码
     */
    @ApiOperation(value = "生成验证码",notes = "生成验证码")
    @RequestMapping(value = "/")
    public void verification(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            final Logger logger = LoggerFactory.getLogger(RandomValidateCodeUtil.class);
            logger.error("获取验证码失败>>>>   ", e);
        }
    }
}
