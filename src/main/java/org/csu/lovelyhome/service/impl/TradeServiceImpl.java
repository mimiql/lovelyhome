package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.Trade;
import org.csu.lovelyhome.mapper.TradeMapper;
import org.csu.lovelyhome.service.ITradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 交易信息表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@Service
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade> implements ITradeService {

}
