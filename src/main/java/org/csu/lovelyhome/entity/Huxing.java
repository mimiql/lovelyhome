package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 户型信息表
 * </p>
 *
 * @author lqm
 * @since 2019-08-29
 */
@TableName("huxing")
public class Huxing implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 户型id
     */
    @TableId(value = "huxing_id", type = IdType.AUTO)
    private Integer huxingId;

    /**
     * 户型名称
     */
    private String name;

    /**
     * 客厅数目
     */
    private Integer drawingRoomNum;

    /**
     * 卧室数目
     */
    private Integer bedroomNum;

    /**
     * 卫生间数目
     */
    private Integer toiletNum;

    /**
     * 浴室数目
     */
    private Integer bathroomNum;

    /**
     * 面积
     */
    private Double floorSpace;

    /**
     * 均价
     */
    private Double price;

    /**
     * 户型图片
     */
    private String picture;

    /**
     * 1正在出售，2正在出租 0已卖或已售
     */
    private Integer status;

    /**
     * 户型剩余数
     */
    private Integer remain;

    /**
     * 描述
     */
    private String describe;

    /**
     * 楼盘id
     */
    private Integer buildingId;

    /**
     * 用户id
     */
    private Integer useId;


    public Integer getHuxingId() {
        return huxingId;
    }

    public void setHuxingId(Integer huxingId) {
        this.huxingId = huxingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDrawingRoomNum() {
        return drawingRoomNum;
    }

    public void setDrawingRoomNum(Integer drawingRoomNum) {
        this.drawingRoomNum = drawingRoomNum;
    }

    public Integer getBedroomNum() {
        return bedroomNum;
    }

    public void setBedroomNum(Integer bedroomNum) {
        this.bedroomNum = bedroomNum;
    }

    public Integer getToiletNum() {
        return toiletNum;
    }

    public void setToiletNum(Integer toiletNum) {
        this.toiletNum = toiletNum;
    }

    public Integer getBathroomNum() {
        return bathroomNum;
    }

    public void setBathroomNum(Integer bathroomNum) {
        this.bathroomNum = bathroomNum;
    }

    public Double getFloorSpace() {
        return floorSpace;
    }

    public void setFloorSpace(Double floorSpace) {
        this.floorSpace = floorSpace;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    @Override
    public String toString() {
        return "Huxing{" +
        "huxingId=" + huxingId +
        ", name=" + name +
        ", drawingRoomNum=" + drawingRoomNum +
        ", bedroomNum=" + bedroomNum +
        ", toiletNum=" + toiletNum +
        ", bathroomNum=" + bathroomNum +
        ", floorSpace=" + floorSpace +
        ", price=" + price +
        ", picture=" + picture +
        ", status=" + status +
        ", remain=" + remain +
        ", describe=" + describe +
        ", buildingId=" + buildingId +
        ", useId=" + useId +
        "}";
    }
}
