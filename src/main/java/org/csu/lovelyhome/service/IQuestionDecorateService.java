package org.csu.lovelyhome.service;

import org.csu.lovelyhome.entity.QuestionDecorate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 装修方案提问及回复表 服务类
 * </p>
 *
 * @author lqm
 * @since 2019-09-02
 */
public interface IQuestionDecorateService extends IService<QuestionDecorate> {
    int insert(QuestionDecorate questionDecorate);
}
