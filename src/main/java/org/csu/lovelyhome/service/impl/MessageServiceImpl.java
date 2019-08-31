package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.Message;
import org.csu.lovelyhome.mapper.MessageMapper;
import org.csu.lovelyhome.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天记录表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
