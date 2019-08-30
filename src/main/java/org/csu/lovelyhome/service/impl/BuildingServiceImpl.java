package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.Building;
import org.csu.lovelyhome.mapper.BuildingMapper;
import org.csu.lovelyhome.service.IBuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 楼盘信息表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements IBuildingService {

}