package org.csu.lovelyhome.mapper;

import org.csu.lovelyhome.entity.FiltHuxing;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 筛选出租户型信息日志表 Mapper 接口
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
public interface FiltHuxingMapper extends BaseMapper<FiltHuxing> {

    List<String> getAllPositionNames();

    List<String> getAllAvePrices();

    List<String> getAllTotalPrices();

    List<String> getAllAreas();

}
