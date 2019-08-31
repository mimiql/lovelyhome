package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 筛选出租户型信息日志表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@TableName("filt_huxing")
public class FiltHuxing implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 筛选id
     */
    @TableId(value = "filt_id", type = IdType.AUTO)
    private Integer filtId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 时间
     */
    private LocalDateTime time;

    /**
     * 地址
     */
    private String address;

    /**
     * 均价
     */
    private String avePrice;

    /**
     * 总价
     */
    private String totalPrice;

    /**
     * 面积
     */
    private String floorsSpace;

    /**
     * 房间总数
     */
    private String rooms;


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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvePrice() {
        return avePrice;
    }

    public void setAvePrice(String avePrice) {
        this.avePrice = avePrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFloorsSpace() {
        return floorsSpace;
    }

    public void setFloorsSpace(String floorsSpace) {
        this.floorsSpace = floorsSpace;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "FiltHuxing{" +
        "filtId=" + filtId +
        ", userId=" + userId +
        ", time=" + time +
        ", address=" + address +
        ", avePrice=" + avePrice +
        ", totalPrice=" + totalPrice +
        ", floorsSpace=" + floorsSpace +
        ", rooms=" + rooms +
        "}";
    }
}
