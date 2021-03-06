package org.csu.lovelyhome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.lovelyhome.common.constant.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.mapper.*;
import org.csu.lovelyhome.pojo.param.FiltBuildingParam;
import org.csu.lovelyhome.pojo.vo.BuildingInformationVo;
import org.csu.lovelyhome.service.IBuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.NewThreadAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 楼盘信息表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements IBuildingService {

    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private CommentBuildingMapper commentBuildingMapper;
    @Autowired
    private HuxingMapper huxingMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private RelateBuildingMapper relateBuildingMapper;
    @Autowired
    private RelateQuestionMapper relateQuestionMapper;

    public void saveOtherResponse(int buildingId, int questionId, int userId, Question question){
        question.setTime(new Date());
        question.setType(0);
        question.setStatus(Constant.STATUS_PUBLISHED);
        question.setLikeNum(0);
        question.setUserId(userId);
        question.setBuildingId(buildingId);
        questionMapper.insert(question);

        RelateQuestion relateQuestion = new RelateQuestion();
        relateQuestion.setQuestionId(questionId);
        relateQuestion.setResponseId(question.getQuestionId()); //同下
        relateQuestionMapper.insert(relateQuestion);
    }

    public void saveSelfResponse(int buildingId, int commentId, int userId, CommentBuilding commentBuilding){
        commentBuilding.setBuildingId(buildingId);
        commentBuilding.setLikeNum(0);
        commentBuilding.setTime(new Date());
        commentBuilding.setType(0);
        commentBuilding.setStatus(Constant.STATUS_PUBLISHED);
        commentBuilding.setUserId(userId);
        commentBuildingMapper.insert(commentBuilding);

        RelateBuilding relateBuilding = new RelateBuilding();
        relateBuilding.setCommentId(commentId);
        relateBuilding.setResponseId(commentBuilding.getCommentId());   //这不知道有没有自增，可能造成bug
        relateBuildingMapper.insert(relateBuilding);
    }

    public void saveComment(int buildingId, int userId, CommentBuilding commentBuilding){
        commentBuilding.setBuildingId(buildingId);
        commentBuilding.setLikeNum(0);
        commentBuilding.setTime(new Date());
        commentBuilding.setType(1);
        commentBuilding.setStatus(Constant.STATUS_VERIFICATION);
        commentBuilding.setUserId(userId);

        commentBuildingMapper.insert(commentBuilding);
    }

    public void saveQuestion(int buildingId, int userId, Question question){
        question.setBuildingId(buildingId);
        question.setLikeNum(0);
        question.setUserId(userId);
        question.setType(1);
        question.setStatus(Constant.STATUS_VERIFICATION);
        question.setTime(new Date());

        questionMapper.insert(question);
    }

    public List<Question> getResponsesByQuestionId(int questionId){
        return questionMapper.getResponsesByQuestionId(questionId);
    }

    public List<CommentBuilding> getResponsesByCommentId(int commentId){
        return commentBuildingMapper.getResponsesByCommentId(commentId);
    }

    public Huxing getHuXingByBuildingIdAndHuxingId(int buildingId, int huxingId){
        return huxingMapper.getHuXingByBuildingIdAndHuxingId(buildingId, huxingId);
    }

    public List<Huxing> getAllHuXingByBuildingId(int buildingId){
        return huxingMapper.getAllHuxingByBuildingId(buildingId);
    }

    public List<Building> getAllBuildingsByPrice(){
        return buildingMapper.getAllBuildingsByPrice();
    }

    public List<String> getBuildingNamesByKeyWords(String keywords){
        return buildingMapper.getBuildingNamesByKeyWords(keywords);
    }

    public BuildingDetail getBuildingDetailByBuildingId(int buildingId){
        List<CommentBuilding> commentBuildingList = commentBuildingMapper.getCommentsByBuildingId(buildingId);
        List<Huxing> huxingList = huxingMapper.getAllHuxingByBuildingId(buildingId);
        List<Question> questionList = questionMapper.getAllQuestionsByBuildingId(buildingId);
        List<Question> responseList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();

        BuildingDetail buildingDetail = new BuildingDetail();
        QueryWrapper<Building> queryWrapper = new QueryWrapper<Building>().eq("building_id", buildingId);
        buildingDetail.setBuilding(buildingMapper.selectOne(queryWrapper));
        buildingDetail.setCommentBuildings(commentBuildingList);
        buildingDetail.setHuxings(huxingList);
        buildingDetail.setQuestionBuildings(questionList);

        for(Question question : questionList){
            responseList.add(questionMapper.getFirstResponseByQuestionId(question.getQuestionId()));
        }
        buildingDetail.setResponses(responseList);

        for(Question question : questionList){
            numList.add(questionMapper.getNumOfResponseByQuestionId(question.getQuestionId()));
        }
        buildingDetail.setAllNumsOfQuestion(numList);

        return buildingDetail;
    }

    public List<Building> getAllBuildingsByCondition(FiltBuildingParam filtBuildingParam){
        return buildingMapper.getAllBuildingsByCondition(filtBuildingParam);
    }

    public BuildingInformationVo getBuildingInformationById(int building_Id){
        QueryWrapper<Building> buildingQueryWrapper = new QueryWrapper<>();
        buildingQueryWrapper.eq("building_id", building_Id);
        Building building = buildingMapper.selectOne(buildingQueryWrapper);
        QueryWrapper<Huxing> huxingQueryWrapper = new QueryWrapper<>();
        huxingQueryWrapper.eq("building_id", building_Id);
        List<Huxing> huxingList = huxingMapper.selectList(huxingQueryWrapper);

        BuildingInformationVo buildingInformationVo = new BuildingInformationVo();
        buildingInformationVo.setBuilding(building);
        buildingInformationVo.setHuxingList(huxingList);

        return buildingInformationVo;
     }

     public Integer getTagNum(String tag){
        return buildingMapper.getTagNum(tag);
     }

     public List<Building>  getTagBuildings(String tag){
        return buildingMapper.getTagBuildings(tag);
     }
}
