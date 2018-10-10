package com.rk.service;

import com.rk.dao.ProductTypeMapper;
import com.rk.dto.ReturnResult;
import com.rk.entity.ProductType;
import com.rk.service.interfaces.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, Integer> implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    //重写父类方法
    public ReturnResult updateOrAdd(ProductType productType) {
        ProductType dbProductType = productTypeMapper.getProductTypeByNameOrCode(productType.getTypeName(), productType.getTypeCode());
        if (dbProductType != null && dbProductType.getId() != productType.getId()) {
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
