package org.csu.lovelyhome.mapper;

import org.apache.ibatis.annotations.Param;
import org.csu.lovelyhome.entity.Building;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.lovelyhome.pojo.param.FiltBuildingParam;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 楼盘信息表 Mapper 接口
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
public interface BuildingMapper extends BaseMapper<Building> {

    List<Building> getAllBuildingsByPrice();

    List<String> getBuildingNamesByKeyWords(@Param("keywords") String keywords);

    List<Building> getAllBuildingsByCondition(FiltBuildingParam filtBuildingParam);

    Integer getTagNum(String tag);

    List<Building> getTagBuildings(String tag);
}
