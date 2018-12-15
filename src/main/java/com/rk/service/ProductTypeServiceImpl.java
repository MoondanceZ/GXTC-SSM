package com.rk.service;

import com.rk.dao.ProductTypeMapper;
import com.rk.dto.response.ReturnResult;
import com.rk.entity.ProductType;
import com.rk.service.interfaces.ProductService;
import com.rk.service.interfaces.ProductTypeService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, Integer> implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private ProductService productService;

    //重写父类删除方法
    public ReturnResult delete(Integer... keys) {
        if (ArrayUtils.isEmpty(keys))
            return ReturnResult.Error("没有提供需要删除的Id");

        Integer[] typeIds = productService.getByTypeIds(keys);
        if (ArrayUtils.isEmpty(typeIds)) {
            return delete(keys);
        }

        List<Integer> unDelKeys = new ArrayList<>();
        List<Integer> delKeys = new ArrayList<>();
        for (Integer key : keys) {
            if (ArrayUtils.contains(typeIds, key)) {
                unDelKeys.add(key);
            } else {
                delKeys.add(key);
            }
        }

        Integer[] delKeysAry = delKeys.toArray(new Integer[delKeys.size()]);

        if (unDelKeys.size() == keys.length) {
            return ReturnResult.Error("删除失败, 选中的记录已被使用");
        }

        if (baseMapper.delete(delKeysAry) > 0)
            return ReturnResult.Success("成功删除" + delKeysAry.length + "条记录, Id 为 "
                    + unDelKeys.toString() + "的记录已被使用, 不能删除");
        else
            return ReturnResult.Error("删除失败");
    }

    //重写父类方法
    public ReturnResult updateOrAdd(ProductType productType) {
        ProductType dbProductType = productTypeMapper.getProductTypeByNameOrCode(productType.getTypeName(), productType.getTypeCode());
        if (dbProductType != null && !dbProductType.getId().equals(productType.getId())) {
            if (dbProductType.getTypeName().equals(productType.getTypeName())) {
                return ReturnResult.Error("类型名称已存在");
            } else {
                return ReturnResult.Error("类型代码已存在");
            }

        } else {
            if (productType.getId() != null && productType.getId() != 0) {  //修改
                if (productTypeMapper.update(productType) > 0) {
                    return ReturnResult.Success("提交成功");
                }
            } else {  //新增
                if (productTypeMapper.insert(productType) > 0) {
                    return ReturnResult.Success("提交成功");
                }
            }
        }
        return ReturnResult.Error("提交失败");
    }

    @Override
    public List<ProductType> getEnableTypes() {
        return productTypeMapper.getEnableTypes();
    }
}
