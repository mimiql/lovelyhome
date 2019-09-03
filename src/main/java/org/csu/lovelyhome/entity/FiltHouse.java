package org.csu.lovelyhome.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 筛选出租房信息日志表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@TableName("filt_house")
public class FiltHouse implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 筛选id
     */
    @TableId(value = "filt_id", type = IdType.AUTO)
    private Integer filtId;

    /**
     * 用于id
     */
    private Integer userId;

    /**
     * 时间
     */
    private Date time;

    /**
     * 地址
     */
    private String address;

    /**
     * 价格，即租金
     */
    private BigDecimal price;

    /**
     * 房间数
     */
    private String rooms;

    /**
     * 朝向
     */
    private String orientation;


    public Integer getFiltId() {
        return filtId;
    }

    public void setFiltId(Integer filtId) {
        this.filtId = filtId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "FiltHouse{" +
        "filtId=" + filtId +
        ", userId=" + userId +
        ", time=" + time +
        ", address=" + address +
        ", price=" + price +
        ", rooms=" + rooms +
        ", orientation=" + orientation +
        "}";
    }
}
