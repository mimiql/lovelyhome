package org.csu.lovelyhome.mapper;

import org.csu.lovelyhome.entity.FiltDecorate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 筛选装修方案信息日志表 Mapper 接口
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
public interface FiltDecorateMapper extends BaseMapper<FiltDecorate> {

    List<String> getAllStyleNames();

    List<String> getAllBudgets();

    List<String> getAllArea();

    List<Integer> getAllType();

    List<Integer> getAllHouseNums();
}
