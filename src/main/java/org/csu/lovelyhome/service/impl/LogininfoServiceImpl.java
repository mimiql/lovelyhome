package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.Logininfo;
import org.csu.lovelyhome.mapper.LogininfoMapper;
import org.csu.lovelyhome.service.ILogininfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录信息表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@Service
public class LogininfoServiceImpl extends ServiceImpl<LogininfoMapper, Logininfo> implements ILogininfoService {

}
