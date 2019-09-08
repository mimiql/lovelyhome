package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.FiltDecorate;
import org.csu.lovelyhome.mapper.FiltDecorateMapper;
import org.csu.lovelyhome.service.IFiltDecorateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 筛选装修方案信息日志表 服务实现类
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
@Service
public class FiltDecorateServiceImpl extends ServiceImpl<FiltDecorateMapper, FiltDecorate> implements IFiltDecorateService {
    @Autowired
    private FiltDecorateMapper filtDecorateMapper;

    public List<String> getAllStyleNames(){
        return filtDecorateMapper.getAllStyleNames();
    }

    public List<String> getAllBudgets(){
        return filtDecorateMapper.getAllBudgets();
    }

    public List<String> getAllArea(){
        return filtDecorateMapper.getAllArea();
    }

    public List<Integer> getAllType(){
        return filtDecorateMapper.getAllType();
    }

    public List<Integer> getAllHouseNums(){
        return filtDecorateMapper.getAllHouseNums();
    }
}
