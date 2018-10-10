package com.rk.entity;

/**
 * Created by Qin_Yikai on 2018-10-10.
 */
public class BaseEntity<K> {
    private K id;

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }
}
