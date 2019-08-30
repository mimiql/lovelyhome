package org.csu.lovelyhome.service;

import org.csu.lovelyhome.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
public interface IUserService extends IService<User> {
    List<String> getAllPhoneList();
}
