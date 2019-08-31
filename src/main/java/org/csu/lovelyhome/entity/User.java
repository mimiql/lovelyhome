package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
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
     * 电话
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String name;

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
     * 邮箱
     */
    private String email;

    /**
     * 性别 1男 2女
     */
    private String sex;

    /**
     * 出生日期
     */
    private LocalDateTime brithdata;

    /**
     * 微信账号
     */
    private String wechatNum;

    /**
     * QQ账号
     */
    @TableField("QQ_num")
    private String qqNum;

    /**
     * 地址
     */
    private String address;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDateTime getBrithdata() {
        return brithdata;
    }

    public void setBrithdata(LocalDateTime brithdata) {
        this.brithdata = brithdata;
    }

    public String getWechatNum() {
        return wechatNum;
    }

    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", phone=" + phone +
        ", password=" + password +
        ", name=" + name +
        ", nick=" + nick +
        ", idCard=" + idCard +
        ", email=" + email +
        ", sex=" + sex +
        ", brithdata=" + brithdata +
        ", wechatNum=" + wechatNum +
        ", qqNum=" + qqNum +
        ", address=" + address +
        "}";
    }
}
