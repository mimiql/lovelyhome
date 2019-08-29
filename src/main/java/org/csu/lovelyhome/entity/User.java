package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户唯一标识
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 身份证号码
     */
    @TableField("Id_card")
    private String idCard;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", name=" + name +
        ", password=" + password +
        ", nick=" + nick +
        ", idCard=" + idCard +
        ", phone=" + phone +
        ", email=" + email +
        "}";
    }
}
