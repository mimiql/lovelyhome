package org.csu.lovelyhome.mapper;

import org.csu.lovelyhome.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 楼盘提问或回复表 Mapper 接口
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
public interface QuestionMapper extends BaseMapper<Question> {
    List<Question> getAllQuestionsByBuildingId(int BuildingId);

    List<Question> getResponsesByQuestionId(int QuestionId);

    Question getFirstResponseByQuestionId(int QuestionId);

    int getNumOfResponseByQuestionId(int QuestionId);
}
