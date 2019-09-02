package org.csu.lovelyhome.pojo.param;

public class FiltHouseParam {
    private String position = null;
    private Integer minPrice = null;
    private Integer maxPrice = null;
    private Integer numOfhuxing = null;
    private String orientation = null;

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
}
