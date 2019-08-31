package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.House;
import org.csu.lovelyhome.mapper.HouseMapper;
import org.csu.lovelyhome.service.IHouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出租房信息表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements IHouseService {

}
