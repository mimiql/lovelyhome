package org.csu.lovelyhome.pojo.param;

/**
 * 筛选楼盘参数
 * @author zjx
 */
public class FiltBuildingParam {
    private String position = null;
    private Integer minUnitPrice = null;
    private Integer maxUnitPrice = null;
    private Integer minTotalPrice = null;
    private Integer maxTotalPrice = null;
    private Integer minArea = null;
    private Integer maxArea = null;
    private Integer numOfhuxing = null;
    private Integer type = null;   //0默认的排序、1按均价排序、2按时间排序

    public String getPosition() {
        return position;
    }

    public Integer getMinUnitPrice() {
        return minUnitPrice;
    }

    public Integer getMaxUnitPrice() {
        return maxUnitPrice;
    }

    public Integer getMinTotalPrice() {
        return minTotalPrice;
    }

    public Integer getMaxTotalPrice() {
        return maxTotalPrice;
    }

    public Integer getMinArea() {
        return minArea;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public Integer getNumOfhuxing() {
        return numOfhuxing;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setMinUnitPrice(Integer minUnitPrice) {
        this.minUnitPrice = minUnitPrice;
    }

    public void setMaxUnitPrice(Integer maxUnitPrice) {
        this.maxUnitPrice = maxUnitPrice;
    }

    public void setMinTotalPrice(Integer minTotalPrice) {
        this.minTotalPrice = minTotalPrice;
    }

    public void setMaxTotalPrice(Integer maxTotalPrice) {
        this.maxTotalPrice = maxTotalPrice;
    }

    public void setMinArea(Integer minArea) {
        this.minArea = minArea;
    }

    public void setMaxArea(Integer maxArea) {
        this.maxArea = maxArea;
    }

    public void setNumOfhuxing(Integer numOfhuxing) {
        this.numOfhuxing = numOfhuxing;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
