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
 * @since 2019-08-29
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
     * 房间类型1套房2居室3客厅4厨房5浴室6卫生间7阳台
     */
    private Integer roomType;

    /**
     * 描述
     */
    private String describe;

    /**
     * 设计师名字
     */
    private String designerName;

    /**
     * 设计师联系方式
     */
    private String designerPhone;


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

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    @Override
    public String toString() {
        return "Decorate{" +
        "decorateId=" + decorateId +
        ", picture=" + picture +
        ", style=" + style +
        ", floorSpace=" + floorSpace +
        ", budget=" + budget +
        ", roomType=" + roomType +
        ", describe=" + describe +
        ", designerName=" + designerName +
        ", designerPhone=" + designerPhone +
        "}";
    }
}
