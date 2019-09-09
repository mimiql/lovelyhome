package org.csu.lovelyhome.pojo.vo;

import org.csu.lovelyhome.entity.Building;
import org.csu.lovelyhome.entity.Collection;
import org.csu.lovelyhome.entity.Huxing;

public class CollectionHuxingVO {
    private Collection collection;

    private Huxing huxing;

    private Building building;

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void setHuxing(Huxing huxing) {
        this.huxing = huxing;
    }

    public Huxing getHuxing() {
        return huxing;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
