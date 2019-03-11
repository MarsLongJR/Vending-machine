package com.xbz.vpase.persistent.entity;

import java.io.Serializable;

public class XbzShoppingCart implements Serializable {
    private Integer id;

    private Integer storeId;

    private Short storeCount;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Short getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Short storeCount) {
        this.storeCount = storeCount;
    }
}