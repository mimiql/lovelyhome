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
     * 用户id
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
    private String price;

    /**
     * 房间数
     */
    private Integer rooms;

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
    private String floorSpace;


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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
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

    public Integer getBedroomNum() {
        return bedroomNum;
    }

    public Integer getToiletNum() {
        return toiletNum;
    }

    public Integer getBathroomNum() {
        return bathroomNum;
    }

    public String getFloorSpace() {
        return floorSpace;
    }

    public void setDrawingRoomNum(Integer drawingRoomNum) {
        this.drawingRoomNum = drawingRoomNum;
    }

    public void setBedroomNum(Integer bedroomNum) {
        this.bedroomNum = bedroomNum;
    }

    public void setToiletNum(Integer toiletNum) {
        this.toiletNum = toiletNum;
    }

    public void setBathroomNum(Integer bathroomNum) {
        this.bathroomNum = bathroomNum;
    }

    public void setFloorSpace(String floorSpace) {
        this.floorSpace = floorSpace;
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
        ", drawingRoomNum=" + drawingRoomNum +
        ", bedroomNum=" + bedroomNum +
        ", toiletNum=" + toiletNum +
        ", bathroomNum=" + bathroomNum +
        ", floorSpace=" + floorSpace +
        "}";
    }
}
