package org.csu.lovelyhome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.lovelyhome.entity.FiltHouse;
import org.csu.lovelyhome.entity.House;
import org.csu.lovelyhome.mapper.FiltHouseMapper;
import org.csu.lovelyhome.mapper.HouseMapper;
import org.csu.lovelyhome.pojo.param.FiltHouseParam;
import org.csu.lovelyhome.service.IHouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 出租房信息表 服务实现类
 * </p>
 *
 * @author lqm、zjx
 * @since 2019-08-31
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements IHouseService {

    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private FiltHouseMapper filtHouseMapper;

    public List<House> getHousesByCondition(FiltHouseParam filtHouseParam){
        QueryWrapper<House> queryWrapper = new QueryWrapper<House>();
        if(filtHouseParam.getPosition() != null){
            queryWrapper.eq("district", filtHouseParam.getPosition());
        }

        if(filtHouseParam.getMinPrice() != null){
            queryWrapper.ge("price", filtHouseParam.getMinPrice());
        }

        if(filtHouseParam.getMaxPrice() != null){
            queryWrapper.le("price", filtHouseParam.getMaxPrice());
        }

        if(filtHouseParam.getNumOfhuxing() != null){
            int num = filtHouseParam.getNumOfhuxing();
            if(num == 4){
                queryWrapper.ge("bedroom_num", num);
            }else{
                queryWrapper.eq("bedroom_num", num);
            }
        }

        if(filtHouseParam.getBathRoomNum() != null){
            queryWrapper.eq("bathroom_num", filtHouseParam.getBathRoomNum());
        }

        if(filtHouseParam.getBedRoomNum() != null){
            queryWrapper.eq("bedroom_num", filtHouseParam.getBedRoomNum());
        }

        if(filtHouseParam.getLivingRoomNum() != null){
            queryWrapper.eq("drawing_room_num", filtHouseParam.getLivingRoomNum());
        }

        if(filtHouseParam.getToiletNum() != null){
            queryWrapper.eq("toilet_num", filtHouseParam.getToiletNum());
        }

        if(filtHouseParam.getMinArea() != null){
            queryWrapper.ge("floor_space", filtHouseParam.getMinArea());
        }

        if(filtHouseParam.getMaxArea() != null){
            queryWrapper.le("floor_space", filtHouseParam.getMaxArea());
        }

        return houseMapper.selectList(queryWrapper);
    }

    public List<House> getHousesByCondition(int user_id, FiltHouseParam filtHouseParam){
        FiltHouse filtHouse = new FiltHouse();
        filtHouse.setTime(new Date());
        filtHouse.setUserId(user_id);
        QueryWrapper<House> queryWrapper = new QueryWrapper<House>();
        if(filtHouseParam.getPosition() != null){
            queryWrapper.eq("district", filtHouseParam.getPosition());
            filtHouse.setAddress(filtHouseParam.getPosition());
        }

        String str = "";
        if(filtHouseParam.getMinPrice() != null){
            queryWrapper.ge("price", filtHouseParam.getMinPrice());
            str += filtHouseParam.getMinPrice() + "%";
        }

        if(filtHouseParam.getMaxPrice() != null){
            queryWrapper.le("price", filtHouseParam.getMaxPrice());
            str += filtHouseParam.getMaxPrice();
        }

        filtHouse.setPrice(str);

        if(filtHouseParam.getNumOfhuxing() != null){
            int num = filtHouseParam.getNumOfhuxing();
            if(num == 4){
                queryWrapper.ge("bedroom_num", num);
            }else{
                queryWrapper.eq("bedroom_num", num);
            }
            filtHouse.setRooms(filtHouseParam.getNumOfhuxing());
        }

        if(filtHouseParam.getBathRoomNum() != null){
            queryWrapper.eq("bathroom_num", filtHouseParam.getBathRoomNum());
            filtHouse.setBathroomNum(filtHouseParam.getBathRoomNum());
        }

        if(filtHouseParam.getBedRoomNum() != null){
            queryWrapper.eq("bedroom_num", filtHouseParam.getBedRoomNum());
            filtHouse.setBedroomNum(filtHouseParam.getBathRoomNum());
        }

        if(filtHouseParam.getLivingRoomNum() != null){
            queryWrapper.eq("drawing_room_num", filtHouseParam.getLivingRoomNum());
            filtHouse.setDrawingRoomNum(filtHouseParam.getLivingRoomNum());
        }

        if(filtHouseParam.getToiletNum() != null){
            queryWrapper.eq("toilet_num", filtHouseParam.getToiletNum());
            filtHouse.setToiletNum(filtHouseParam.getToiletNum());
        }

        str = "";
        if(filtHouseParam.getMinArea() != null){
            queryWrapper.ge("floor_space", filtHouseParam.getMinArea());
            str += filtHouseParam.getMinArea() + "%";
        }

        if(filtHouseParam.getMaxArea() != null){
            queryWrapper.le("floor_space", filtHouseParam.getMaxArea());
            str += filtHouseParam.getMaxArea() + "%";
        }
        filtHouse.setFloorSpace(str);

        filtHouseMapper.insert(filtHouse);
        return houseMapper.selectList(queryWrapper);
    }
}
