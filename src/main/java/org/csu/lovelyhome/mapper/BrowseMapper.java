package org.csu.lovelyhome.mapper;

import org.csu.lovelyhome.entity.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 浏览记录表 Mapper 接口
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
public interface BrowseMapper extends BaseMapper<Browse> {

    List<Building> getBrowsingBuilding();

    List<House> getBrowsingHouse();

    List<Decorate> getBrowsingDecoration();

    List<Huxing> getBrowsingHuxing();
}
