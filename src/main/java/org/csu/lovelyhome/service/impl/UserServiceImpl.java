package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.User;
import org.csu.lovelyhome.mapper.UserMapper;
import org.csu.lovelyhome.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper UserMapper;

    public List<String> getAllPhoneList() {
        return UserMapper.getAllPhoneList();
    }
}
