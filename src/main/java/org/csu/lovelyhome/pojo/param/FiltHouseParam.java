package org.csu.lovelyhome.pojo.param;

public class FiltHouseParam {
    private String position = null;
    private Integer minPrice = null;
    private Integer maxPrice = null;
    private Integer numOfhuxing = null;
    private String orientation = null;
    private Integer minArea = null;
    private Integer maxArea = null;
    private Integer bedRoomNum = null;
    private Integer livingRoomNum = null;
    private Integer toiletNum = null;
    private Integer bathRoomNum = null;

    public String getPosition() {
        return position;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public Integer getNumOfhuxing() {
        return numOfhuxing;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setNumOfhuxing(Integer numOfhuxing) {
        this.numOfhuxing = numOfhuxing;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Integer getMinArea() {
        return minArea;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public Integer getBedRoomNum() {
        return bedRoomNum;
    }

    public Integer getLivingRoomNum() {
        return livingRoomNum;
    }

    public Integer getToiletNum() {
        return toiletNum;
    }

    public Integer getBathRoomNum() {
        return bathRoomNum;
    }

    public void setMinArea(Integer minArea) {
        this.minArea = minArea;
    }

    public void setMaxArea(Integer maxArea) {
        this.maxArea = maxArea;
    }

    public void setBedRoomNum(Integer bedRoomNum) {
        this.bedRoomNum = bedRoomNum;
    }

    public void setLivingRoomNum(Integer livingRoomNum) {
        this.livingRoomNum = livingRoomNum;
    }

    public void setToiletNum(Integer toiletNum) {
        this.toiletNum = toiletNum;
    }

    public void setBathRoomNum(Integer bathRoomNum) {
        this.bathRoomNum = bathRoomNum;
    }
}
