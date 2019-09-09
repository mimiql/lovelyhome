package org.csu.lovelyhome.pojo.vo;

import org.csu.lovelyhome.entity.Building;
import org.csu.lovelyhome.entity.Collection;

public class CollectionBuildingVO {
    private Collection collection;

    private Building building;

    public Collection getCollection() {
        return collection;
    }

    public Building getBuilding() {
        return building;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
