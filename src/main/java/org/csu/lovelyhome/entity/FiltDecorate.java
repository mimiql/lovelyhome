package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 筛选装修方案信息日志表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@TableName("filt_decorate")
public class FiltDecorate implements Serializable {

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
    private Date time;

    /**
     * 房间类型
     */
    private Integer roomType;

    /**
     * 房间数
     */
    private Integer rooms;

    /**
     * 面积
     */
    private String floorsSpace; //用%分隔开最小值和最大值

    /**
     * 风格
     */
    private String style;

    /**
     * 预算
     */
    private String budget;  //用%分隔开最小值和最大值


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

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public String getFloorsSpace() {
        return floorsSpace;
    }

    public void setFloorsSpace(String floorsSpace) {
        this.floorsSpace = floorsSpace;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "FiltDecorate{" +
        "filtId=" + filtId +
        ", userId=" + userId +
        ", time=" + time +
        ", roomType=" + roomType +
        ", rooms=" + rooms +
        ", floorsSpace=" + floorsSpace +
        ", style=" + style +
        ", budget=" + budget +
        "}";
    }
}
