package org.csu.lovelyhome.service.impl;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.mapper.UserMapper;
import org.csu.lovelyhome.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public List<String> getAllPhoneList() {
        return userMapper.getAllPhoneList();
    }

    public List<Building> getCollectionBuildingByUserId(int userId){
        return userMapper.getCollectionBuildingByUserId(userId);
    }

    public List<Huxing> getCollectionHuxingByUserId(int userId){
        return userMapper.getCollectionHuxingByUserId(userId);
    }

    public List<House> getCollectionHouseByUserId(int userId){
        return userMapper.getCollectionHouseByUserId(userId);
    }

    public List<Decorate> getCollectionDecorateByUserId(int userId){
        return userMapper.getCollectionDecorateByUserId(userId);
    }

    /**
     * 默认查找的是已发布的
     */
    public List<House> getPublishHousesByUserId(int user_id){
        return userMapper.getPublishHouseByUserId(user_id);
    }

    public List<House> getHousesByUserIdAndStatus(int user_id, int status){
        return userMapper.getHousesByUserIdAndStatus(user_id, status);
    }

    public List<Question> getQuestionByUserId(int user_id){
        return userMapper.getQuestionByUserId(user_id);
    }

    public List<Question> getQuestionResponsesByUserId(int user_id){
        return userMapper.getQuestionResponsesByUserId(user_id);
    }

    public Comment getCommentByUserId(int user_id){
        List<CommentBuilding> commentBuildingList = userMapper.getCommentBuildingByUserId(user_id);
        List<CommentDecorate> commentDecorateList = userMapper.getCommentDecorateByUserId(user_id);
        List<CommentHouse> commentHouseList = userMapper.getCommentHouseByUserId(user_id);
        Comment comment = new Comment();
        comment.setCommentBuildings(commentBuildingList);
        comment.setCommentDecorates(commentDecorateList);
        comment.setCommentHouses(commentHouseList);
        return comment;
    }

    public List<CommentBuilding> getCommentBuildingByUserId(int user_id){
        return userMapper.getCommentBuildingByUserId(user_id);
    }

    public List<CommentDecorate> getCommentDecorateByUserId(int user_id){
        return userMapper.getCommentDecorateByUserId(user_id);
    }

    public List<Building> getBrowsingBuildingByUserId(int userId){
        return userMapper.getBrowsingBuildingByUserId(userId);
    }

    public List<Huxing> getBrowsingHuxingByUserId(int userId){
        return userMapper.getBrowsingHuxingByUserId(userId);
    }

    public List<House> getBrowsingHouseByUserId(int userId){
        return userMapper.getBrowsingHouseByUserId(userId);
    }

    public List<Decorate> getBrowsingDecorateByUserId(int userId){
        return userMapper.getBrowsingDecorateByUserId(userId);
    }

}
