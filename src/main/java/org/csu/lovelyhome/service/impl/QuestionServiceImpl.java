package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.Question;
import org.csu.lovelyhome.mapper.QuestionMapper;
import org.csu.lovelyhome.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 楼盘提问或回复表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}
