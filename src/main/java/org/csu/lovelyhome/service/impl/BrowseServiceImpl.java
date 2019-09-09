package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.*;
import org.csu.lovelyhome.mapper.BrowseMapper;
import org.csu.lovelyhome.service.IBrowseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 浏览记录表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@Service
public class BrowseServiceImpl extends ServiceImpl<BrowseMapper, Browse> implements IBrowseService {

    @Autowired
    private BrowseMapper browseMapper;

    public List<Building> getBrowsingBuilding(){
        return browseMapper.getBrowsingBuilding();
    }

    public List<House> getBrowsingHouse(){
        return browseMapper.getBrowsingHouse();
    }

    public List<Decorate> getBrowsingDecoration(){
        return browseMapper.getBrowsingDecoration();
    }

    public List<Huxing> getBrowsingHuxing(){
        return browseMapper.getBrowsingHuxing();
    }

    public void save(int user_id, int id, int type){
        Browse browse = new Browse();
        browse.setObjectId(id);
        browse.setTime(new Date());
        browse.setType(type);
        browse.setUserId(user_id);
        browseMapper.insert(browse);
    }
}
