package org.csu.lovelyhome.pojo.param;

public class FiltDecorateParam extends PageParam{
    private String style;
    private Double minFloorSpace = 0.0;
    private Double maxFloorSpace = 0.0;
    private Double minBudget = 0.0;
    private Double maxBudget = 0.0;
    private Integer rooms = 0;
    private Integer roomType = 0;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Double getMinFloorSpace() {
        return minFloorSpace;
    }

    public void setMinFloorSpace(Double minFloorSpace) {
        this.minFloorSpace = minFloorSpace;
    }

    public Double getMaxFloorSpace() {
        return maxFloorSpace;
    }

    public void setMaxFloorSpace(Double maxFloorSpace) {
        this.maxFloorSpace = maxFloorSpace;
    }

    public Double getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(Double minBudget) {
        this.minBudget = minBudget;
    }

    public Double getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(Double maxBudget) {
        this.maxBudget = maxBudget;
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
}
