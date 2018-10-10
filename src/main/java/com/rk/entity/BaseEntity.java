package com.rk.entity;

import java.io.Serializable;

/**
 * Created by Qin_Yikai on 2018-10-10.
 */
public class BaseEntity<K> implements Serializable {
    private K id;

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }
}
