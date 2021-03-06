package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 装修方案表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@TableName("decorate")
public class Decorate implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 装修方案id
     */
    @TableId(value = "decorate_id", type = IdType.AUTO)
    private Integer decorateId;

    /**
     * 图片
     */
    private String picture;

    /**
     * 风格
     */
    private String style;

    /**
     * 面积
     */
    private Double floorSpace;

    /**
     * 预算
     */
    private Double budget;

    /**
     * 房间数
     */
    private Integer rooms;

    /**
     * 房间类型1套房2居室3客厅4厨房5浴室6卫生间7阳台
     */
    private Integer roomType;

    /**
     * 描述
     */
    private String description;

    /**
     * 设计师名字
     */
    private String designerName;

    /**
     * 设计师联系方式
     */
    private String designerPhone;

    /**
     *状态：1已发布 0已冻结
     */
    private Integer status;

    public Integer getDecorateId() {
        return decorateId;
    }

    public void setDecorateId(Integer decorateId) {
        this.decorateId = decorateId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Double getFloorSpace() {
        return floorSpace;
    }

    public void setFloorSpace(Double floorSpace) {
        this.floorSpace = floorSpace;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getDesignerPhone() {
        return designerPhone;
    }

    public void setDesignerPhone(String designerPhone) {
        this.designerPhone = designerPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Decorate{" +
        "decorateId=" + decorateId +
        ", picture=" + picture +
        ", style=" + style +
        ", floorSpace=" + floorSpace +
        ", budget=" + budget +
        ", rooms=" + rooms +
        ", roomType=" + roomType +
        ", description=" + description +
        ", designerName=" + designerName +
        ", designerPhone=" + designerPhone +
        ", status" + status +
        "}";
    }
}
