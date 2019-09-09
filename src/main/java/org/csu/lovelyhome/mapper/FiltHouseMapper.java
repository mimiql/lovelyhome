package org.csu.lovelyhome.mapper;

import org.csu.lovelyhome.entity.FiltHouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 筛选出租房信息日志表 Mapper 接口
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
public interface FiltHouseMapper extends BaseMapper<FiltHouse> {

    List<String> getAllPositionNames();

    List<String> getAllOrientions();

    List<String> getAllAreas();

    List<String> getAllAvePrices();

    List<Integer> getAllBathRoomNum();

    List<Integer> getAllBedRoomNum();

    List<Integer> getAllDrawingRoomNum();

    List<Integer> getAllToiletNum();
}
