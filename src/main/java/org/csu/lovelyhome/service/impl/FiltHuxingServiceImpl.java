package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.FiltHuxing;
import org.csu.lovelyhome.mapper.FiltHuxingMapper;
import org.csu.lovelyhome.service.IFiltHuxingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 筛选出租户型信息日志表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@Service
public class FiltHuxingServiceImpl extends ServiceImpl<FiltHuxingMapper, FiltHuxing> implements IFiltHuxingService {

    @Autowired
    private FiltHuxingMapper filtHuxingMapper;

    public List<String> getAllPositionNames(){
        return filtHuxingMapper.getAllPositionNames();
    }

    public List<String> getAllAvePrices(){
        return filtHuxingMapper.getAllAvePrices();
    }

    public List<String> getAllTotalPrices(){
        return filtHuxingMapper.getAllTotalPrices();
    }

    public List<String> getAllAreas(){
        return filtHuxingMapper.getAllAreas();
    }
}
