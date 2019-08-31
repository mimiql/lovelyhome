package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.Admin;
import org.csu.lovelyhome.mapper.AdminMapper;
import org.csu.lovelyhome.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员信息表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
