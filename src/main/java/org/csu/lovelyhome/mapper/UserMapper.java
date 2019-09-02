package org.csu.lovelyhome.mapper;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.csu.lovelyhome.entity.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
public interface UserMapper extends BaseMapper<User> {
    List<String> getAllPhoneList();

    List<Building> getCollectionBuildingByUserId(int userId);

    List<Huxing> getCollectionHuxingByUserId(int userId);

    List<House> getCollectionHouseByUserId(int userId);

    List<Decorate> getCollectionDecorateByUserId(int userId);

    List<House> getPublishHouseByUserId(int user_id);

    List<House> getHousesByUserIdAndStatus(@Param("user_id") int user_id, @Param("status") int status);

    List<Question> getQuestionByUserId(int user_id);

    List<Question> getQuestionResponsesByUserId(int user_id);

    List<CommentBuilding> getCommentBuildingByUserId(int user_id);

    List<CommentDecorate> getCommentDecorateByUserId(int user_id);

    List<Building> getBrowsingBuildingByUserId(int userId);

    List<Huxing> getBrowsingHuxingByUserId(int userId);

    List<House> getBrowsingHouseByUserId(int userId);

    List<Decorate> getBrowsingDecorateByUserId(int userId);
}
