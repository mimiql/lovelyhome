package org.csu.lovelyhome.pojo.vo;

import org.csu.lovelyhome.entity.Building;
import org.csu.lovelyhome.entity.Huxing;

import java.util.List;

public class BuildingInformationVo {

    private Building building;
    private List<Huxing> huxingList;


    public Building getBuilding() {
        return building;
    }

    public List<Huxing> getHuxingList() {
        return huxingList;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setHuxingList(List<Huxing> huxingList) {
        this.huxingList = huxingList;
    }
}
