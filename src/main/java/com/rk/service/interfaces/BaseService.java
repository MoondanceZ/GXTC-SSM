package com.rk.service.interfaces;

import com.rk.dto.LayPage;
import com.rk.dto.ReturnResult;
import com.rk.dto.request.PageRequest;
import com.rk.entity.BaseEntity;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-10-10.
 */
public interface BaseService<T extends BaseEntity, K> {
    ReturnResult delete(K... keys);
    ReturnResult logicalDelete(K...keys);
    ReturnResult insert(T tObj);
    T getByPrimaryKey(K key);
    LayPage<List<T>> getPageList(PageRequest pageRequest);
    ReturnResult updateOrAdd(T tObj);
}
