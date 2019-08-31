package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.Building;
import org.csu.lovelyhome.mapper.BuildingMapper;
import org.csu.lovelyhome.service.IBuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 楼盘信息表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements IBuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    public List<Building> getAllBuildingsByPrice(){
        return buildingMapper.getAllBuildingsByPrice();
    }

    public List<String> getBuildingNamesByKeyWords(String keywords){
        return buildingMapper.getBuildingNamesByKeyWords(keywords);
    }
}
