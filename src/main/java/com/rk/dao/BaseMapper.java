package com.rk.dao;

/**
 * Created by Qin_Yikai on 2018-10-10.
 */

import com.rk.dto.request.PageRequest;

import java.util.List;

/**
 * BaseMapper
 *
 * @param <T> model
 * @param <K> primary key
 */
public interface BaseMapper<T, K> {
    int insert(T tObj);

    int update(T tObj);

    int delete(K kek);

    int delete(K[] keys);

    List<T> getPageList(PageRequest pageRequest);

    int getPageListTotalCount(PageRequest pageRequest);

    T getByPrimaryKey(K key);
}
