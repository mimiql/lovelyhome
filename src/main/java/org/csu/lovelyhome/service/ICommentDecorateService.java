package org.csu.lovelyhome.service;

import org.csu.lovelyhome.entity.CommentDecorate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 装修方案评论表 服务类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
public interface ICommentDecorateService extends IService<CommentDecorate> {
    public int insert(CommentDecorate commentDecorate);
}
