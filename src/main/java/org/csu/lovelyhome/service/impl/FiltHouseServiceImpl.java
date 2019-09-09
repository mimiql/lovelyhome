package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.FiltHouse;
import org.csu.lovelyhome.mapper.FiltHouseMapper;
import org.csu.lovelyhome.service.IFiltHouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 筛选出租房信息日志表 服务实现类
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
@Service
public class FiltHouseServiceImpl extends ServiceImpl<FiltHouseMapper, FiltHouse> implements IFiltHouseService {

    @Autowired
    private FiltHouseMapper filtHouseMapper;

    public List<String> getAllPositionNames(){
        return filtHouseMapper.getAllPositionNames();
    }

    public List<String> getAllOrientions(){
        return filtHouseMapper.getAllOrientions();
    }

    public List<String> getAllAreas(){
        return filtHouseMapper.getAllAreas();
    }

    public List<String> getAllAvePrices(){
        return filtHouseMapper.getAllAvePrices();
    }

    public List<String> getAllConfigs(){
        List<FiltHouse> filtHouses = filtHouseMapper.selectList(null);
        List<String> config = new ArrayList<>();
        for(FiltHouse filtHouse : filtHouses){
            StringBuilder stringBuilder = new StringBuilder();
            if(filtHouse.getBedroomNum() != null){
                stringBuilder.append(filtHouse.getBedroomNum()).append("室");
            }

            if(filtHouse.getDrawingRoomNum() != null){
                stringBuilder.append(filtHouse.getDrawingRoomNum()).append("厅");
            }

            if(filtHouse.getToiletNum() != null){
                stringBuilder.append(filtHouse.getToiletNum()).append("卫");
            }

            if(filtHouse.getBathroomNum() != null){
                stringBuilder.append(filtHouse.getBathroomNum()).append("浴");
            }

            config.add(stringBuilder.toString());
        }

        return config;
    }
}
