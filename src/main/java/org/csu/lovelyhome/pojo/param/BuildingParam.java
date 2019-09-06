package org.csu.lovelyhome.pojo.param;

import org.csu.lovelyhome.entity.Building;
import org.csu.lovelyhome.entity.Huxing;

import java.util.List;

public class BuildingParam extends Building {
    List<Huxing> huxings;

    public Building toBuilding(){
        Building building = new Building();
        building.setUserId(this.getUserId());
        building.setCity(this.getCity() != null ? this.getCity():null);
        building.setDescription(this.getDescription()!= null ? this.getDescription():null);
        building.setDistrict(this.getDistrict() != null ? this.getDistrict():null);
        building.setFloorNumber(this.getFloorNumber() != null ? this.getFloorNumber():null);
        building.setFloorsSpace(this.getFloorsSpace() != null ? this.getFloorsSpace():null);
        building.setName(this.getName() != null ? this.getName():null);
        building.setPicture(this.getPicture() != null ? this.getPicture():null);
        building.setPrice(this.getPrice() != null ? this.getPrice():null);
        building.setProvince(this.getProvince() != null ? this.getProvince():null);
        building.setPublishTime(this.getPublishTime() != null ? this.getPublishTime():null);
        building.setStreet(this.getStreet() != null ? this.getStreet():null);
        return building;
    }

    public List<Huxing> getHuxings() {
        return huxings;
    }

    public void setHuxings(List<Huxing> huxings) {
        this.huxings = huxings;
    }
}
