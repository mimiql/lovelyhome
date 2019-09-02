package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.CommentDecorate;
import org.csu.lovelyhome.mapper.CommentDecorateMapper;
import org.csu.lovelyhome.service.ICommentDecorateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 装修方案评论表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@Service
public class CommentDecorateServiceImpl extends ServiceImpl<CommentDecorateMapper, CommentDecorate> implements ICommentDecorateService {

    @Autowired
    private CommentDecorateMapper commentDecorateMapper;

    @Override
    public int insert(CommentDecorate commentDecorate) {
        int id = commentDecorateMapper.insert(commentDecorate);
        return id;
    }
}
