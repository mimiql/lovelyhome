package org.csu.lovelyhome.pojo.vo;

import org.csu.lovelyhome.entity.Collection;
import org.csu.lovelyhome.entity.Decorate;

public class CollectionDecorationVO {

    private Collection collection;

    private Decorate decorate;

    public Collection getCollection() {
        return collection;
    }

    public Decorate getDecorate() {
        return decorate;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void setDecorate(Decorate decorate) {
        this.decorate = decorate;
    }
}
