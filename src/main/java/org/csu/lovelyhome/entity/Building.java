package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 楼盘信息表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@TableName("building")
public class Building implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 楼盘id
     */
    @TableId(value = "building_id", type = IdType.AUTO)
    private Integer buildingId;

    /**
     * 楼盘名称
     */
    private String name;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 街道
     */
    private String street;

    /**
     * 占地面积
     */
    private Double floorsSpace;

    /**
     * 均价
     */
    private Double price;

    /**
     * 楼层高度
     */
    private Integer floorNumber;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态：1在售 0售完 2删除
     */
    private Integer status;

    /**
     * 楼盘所属用户ID
     */
    private Integer userId;

    /**
     * 楼盘图片
     */
    private String picture;

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Double getFloorsSpace() {
        return floorsSpace;
    }

    public void setFloorsSpace(Double floorsSpace) {
        this.floorsSpace = floorsSpace;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Building{" +
        "buildingId=" + buildingId +
        ", name=" + name +
        ", province=" + province +
        ", city=" + city +
        ", district=" + district +
        ", street=" + street +
        ", floorsSpace=" + floorsSpace +
        ", price=" + price +
        ", floorNumber=" + floorNumber +
        ", desc=" + description +
        ", status=" + status +
        ", userId=" + userId +
        ", picture=" + picture +
        "}";
    }
}
