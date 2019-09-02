package org.csu.lovelyhome.mapper;

import org.apache.ibatis.annotations.Param;
import org.csu.lovelyhome.entity.Huxing;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 户型信息表 Mapper 接口
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
public interface HuxingMapper extends BaseMapper<Huxing> {
    List<Huxing> getAllHuxingByBuildingId(int BuildingId);

    Huxing getHuXingByBuildingIdAndHuxingId(@Param("BuildingId") int BuildingId, @Param("huxingId") int huxingId);
}
