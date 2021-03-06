package org.csu.lovelyhome.mapper;

import org.csu.lovelyhome.entity.CommentBuilding;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 楼盘评论及回复记录表 Mapper 接口
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
public interface CommentBuildingMapper extends BaseMapper<CommentBuilding> {
    List<CommentBuilding> getCommentsByBuildingId(int BuildingId);

    List<CommentBuilding> getResponsesByCommentId(int CommentId);
}
