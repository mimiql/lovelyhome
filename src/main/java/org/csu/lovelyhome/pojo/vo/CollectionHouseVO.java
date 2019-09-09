package org.csu.lovelyhome.pojo.vo;

import org.csu.lovelyhome.entity.Collection;
import org.csu.lovelyhome.entity.House;
import org.csu.lovelyhome.entity.Huxing;

public class CollectionHouseVO {

    private Collection collection;

    private House house;

    public Collection getCollection() {
        return collection;
    }

    public House getHouse() {
        return house;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
