package org.csu.lovelyhome.controller;


import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.common.constant.Constant;
import org.csu.lovelyhome.entity.QuestionHouse;
import org.csu.lovelyhome.entity.RelateQuestionHouse;
import org.csu.lovelyhome.service.impl.QuestionHouseServiceImpl;
import org.csu.lovelyhome.service.impl.RelateQuestionHouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 出租房提问及回复记录表 前端控制器
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-09-03
 */
@RestController
@RequestMapping("/question-house")
public class QuestionHouseController extends BaseController {
    @Autowired
    QuestionHouseServiceImpl questionHouseService;
    @Autowired
    RelateQuestionHouseServiceImpl relateQuestionHouseService;

    @ApiOperation(value = "在租房回复提问", notes = "在house_Id租房userId回复提问questionId")
    @PostMapping("/{house_Id}/{questionId}/{userId}")
    public Response questionResponse(@PathVariable("house_Id") int house_Id, @PathVariable("userId") int userId,
                                     @PathVariable("questionId") int questionId, @RequestBody QuestionHouse questionHouse){
        questionHouse.setHouseId(house_Id);
        questionHouse.setUserId(userId);
        questionHouse.setLikeNum(0);
        questionHouse.setStatus(Constant.STATUS_VERIFICATION);
        questionHouse.setTime(new Date());
        questionHouse.setType(Constant.RESPONSE_TYPE);

        RelateQuestionHouse relateQuestionHouse = new RelateQuestionHouse();
        relateQuestionHouse.setQuestionId(questionId);
        relateQuestionHouse.setResponseId(questionHouse.getQuestionId());
        relateQuestionHouseService.save(relateQuestionHouse);

        return success("回答成功,待审核！");
    }

    @ApiOperation(value = "在租房提问", notes = "在house_Id租房userId提问")
    @PostMapping("/{house_Id}/{userId}")
    public Response question(@PathVariable("house_Id") int house_Id, @PathVariable("userId") int userId, @RequestBody QuestionHouse questionHouse){
        questionHouse.setHouseId(house_Id);
        questionHouse.setUserId(userId);
        questionHouse.setLikeNum(0);
        questionHouse.setStatus(Constant.STATUS_VERIFICATION);
        questionHouse.setTime(new Date());
        questionHouse.setType(Constant.QUESTION_TYPE);
        questionHouseService.save(questionHouse);

        return success("提问成功,待审核！");
    }
}

