package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.Collection;
import org.csu.lovelyhome.mapper.CollectionMapper;
import org.csu.lovelyhome.service.ICollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收藏表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements ICollectionService {

}
