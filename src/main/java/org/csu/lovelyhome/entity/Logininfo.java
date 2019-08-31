package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户登录信息表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@TableName("logininfo")
public class Logininfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 信息主键
     */
    @TableId(value = "login_info_id", type = IdType.AUTO)
    private Integer loginInfoId;

    /**
     * 登录用户id
     */
    private Integer userId;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;


    public Integer getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(Integer loginInfoId) {
        this.loginInfoId = loginInfoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "Logininfo{" +
        "loginInfoId=" + loginInfoId +
        ", userId=" + userId +
        ", loginTime=" + loginTime +
        "}";
    }
}
