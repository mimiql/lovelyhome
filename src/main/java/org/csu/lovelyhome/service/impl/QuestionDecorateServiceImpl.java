package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.QuestionDecorate;
import org.csu.lovelyhome.mapper.QuestionDecorateMapper;
import org.csu.lovelyhome.service.IQuestionDecorateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 装修方案提问及回复表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-09-02
 */
@Service
public class QuestionDecorateServiceImpl extends ServiceImpl<QuestionDecorateMapper, QuestionDecorate> implements IQuestionDecorateService {

    @Autowired
    private QuestionDecorateMapper questionDecorateMapper;
    @Override
    public int insert(QuestionDecorate questionDecorate) {
        int id = questionDecorateMapper.insert(questionDecorate);
        return id;
    }
}
