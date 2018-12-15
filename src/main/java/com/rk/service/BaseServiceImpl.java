package com.rk.service;

import com.rk.dao.BaseMapper;
import com.rk.dto.response.LayPage;
import com.rk.dto.response.ReturnResult;
import com.rk.dto.request.PageRequest;
import com.rk.entity.BaseEntity;
import com.rk.service.interfaces.BaseService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-10-10.
 */
public class BaseServiceImpl<T extends BaseEntity, K> implements BaseService<T, K> {
    private static final Logger logger = LogManager.getLogger(BaseServiceImpl.class);

    @Autowired
    protected BaseMapper<T, K> baseMapper;

    @Override
    public ReturnResult delete(K... keys) {
        if (keys == null || keys.length == 0)
            return ReturnResult.Error("没有提供需要删除的Id");

        if (baseMapper.delete(keys) > 0)
            return ReturnResult.Success("删除成功");
        else
            return ReturnResult.Error("删除失败");
    }

    @Override
    public ReturnResult logicalDelete(K...keys){
        if (keys == null || keys.length == 0)
            return ReturnResult.Error("没有提供需要删除的Id");

        if (baseMapper.logicalDelete(keys) > 0)
            return ReturnResult.Success("删除成功");
        else
            return ReturnResult.Error("删除失败");
    }

    @Override
    public ReturnResult insert(T tObj) {
        if (baseMapper.insert(tObj) > 0)
            return ReturnResult.Success("添加成功");
        else
            return ReturnResult.Error("添加失败");
    }

    @Override
    public T getByPrimaryKey(K key) {
        return baseMapper.getByPrimaryKey(key);
    }

    @Override
    public LayPage<List<T>> getPageList(PageRequest pageRequest) {
        List<T> pageList = baseMapper.getPageList(pageRequest);
        int totalCount = baseMapper.getPageListTotalCount(pageRequest);
        return new LayPage<>("", "", totalCount, pageList);
    }

    @Override
    public ReturnResult updateOrAdd(T tObj) {
        if (tObj.getId() != null) {  //修改
            if (baseMapper.update(tObj) > 0)
                return ReturnResult.Success("提交成功");
        } else {
            if (baseMapper.insert(tObj) > 0)
                return ReturnResult.Success("提交成功");
        }
        return ReturnResult.Error("提交失败");
    }
}
