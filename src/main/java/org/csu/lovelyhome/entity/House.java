package org.csu.lovelyhome.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 出租房信息表
 * </p>
 *
 * @author lqm
 * @since 2019-08-31
 */
@TableName("house")
public class House implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 出租房ID
     */
    @TableId(value = "house_id", type = IdType.AUTO)
    private Integer houseId;

    /**
     * 房屋名称
     */
    private String name;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
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
     * 楼栋号
     */
    @TableField("building_NO")
    private String buildingNo;

    /**
     * 单元
     */
    private String unit;

    /**
     * 门牌号
     */
    private String doorplate;

    /**
     * 朝向
     */
    private String orientation;

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
     * 图片
     */
    private String picture;

    /**
     * 状态：1正在出租，0已下架，2正在审核 3已出租
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 电视
     */
    @TableField("TV")
    private Integer tv;

    /**
     * 冰箱
     */
    private Integer fridge;

    /**
     * 洗衣机
     */
    private Integer washer;

    /**
     * 空凋
     */
    private Integer airCondition;

    /**
     * 热水器
     */
    private Integer waterHeater;

    /**
     * 床
     */
    private Integer bed;

    /**
     * 暖气
     */
    private Integer furnace;

    /**
     * 宽带
     */
    private Integer wifi;

    /**
     * 衣柜
     */
    private Integer wardrobe;

    /**
     * 天然气
     */
    private Integer naturalGas;

    /**
     * 标签
     */
    private String tag;


    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
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

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDoorplate() {
        return doorplate;
    }

    public void setDoorplate(String doorplate) {
        this.doorplate = doorplate;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTv() {
        return tv;
    }

    public void setTv(Integer tv) {
        this.tv = tv;
    }

    public Integer getFridge() {
        return fridge;
    }

    public void setFridge(Integer fridge) {
        this.fridge = fridge;
    }

    public Integer getWasher() {
        return washer;
    }

    public void setWasher(Integer washer) {
        this.washer = washer;
    }

    public Integer getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(Integer airCondition) {
        this.airCondition = airCondition;
    }

    public Integer getWaterHeater() {
        return waterHeater;
    }

    public void setWaterHeater(Integer waterHeater) {
        this.waterHeater = waterHeater;
    }

    public Integer getBed() {
        return bed;
    }

    public void setBed(Integer bed) {
        this.bed = bed;
    }

    public Integer getFurnace() {
        return furnace;
    }

    public void setFurnace(Integer furnace) {
        this.furnace = furnace;
    }

    public Integer getWifi() {
        return wifi;
    }

    public void setWifi(Integer wifi) {
        this.wifi = wifi;
    }

    public Integer getWardrobe() {
        return wardrobe;
    }

    public void setWardrobe(Integer wardrobe) {
        this.wardrobe = wardrobe;
    }

    public Integer getNaturalGas() {
        return naturalGas;
    }

    public void setNaturalGas(Integer naturalGas) {
        this.naturalGas = naturalGas;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "House{" +
        "houseId=" + houseId +
        ", name=" + name +
        ", province=" + province +
        ", city=" + city +
        ", district=" + district +
        ", street=" + street +
        ", buildingNo=" + buildingNo +
        ", unit=" + unit +
        ", doorplate=" + doorplate +
        ", orientation=" + orientation +
        ", drawingRoomNum=" + drawingRoomNum +
        ", bedroomNum=" + bedroomNum +
        ", toiletNum=" + toiletNum +
        ", bathroomNum=" + bathroomNum +
        ", floorSpace=" + floorSpace +
        ", price=" + price +
        ", picture=" + picture +
        ", status=" + status +
        ", description=" + description +
        ", userId=" + userId +
        ", tv=" + tv +
        ", fridge=" + fridge +
        ", washer=" + washer +
        ", airCondition=" + airCondition +
        ", waterHeater=" + waterHeater +
        ", bed=" + bed +
        ", furnace=" + furnace +
        ", wifi=" + wifi +
        ", wardrobe=" + wardrobe +
        ", naturalGas=" + naturalGas +
        ", tag=" + tag +
        "}";
    }
}
