package com.rk.service;

import com.rk.dao.ProductTypeMapper;
import com.rk.dto.LayPage;
import com.rk.dto.ReturnResult;
import com.rk.dto.request.PageRequest;
import com.rk.entity.ProductType;
import com.rk.service.interfaces.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public LayPage<List<ProductType>> getPageList(PageRequest pageRequest) {
        List<ProductType> productTypes = productTypeMapper.getPageList(pageRequest);
        int totalCount = productTypeMapper.getPageListTotalCount();
        return new LayPage<>("", "", totalCount, productTypes);
    }

    @Override
    public List<ProductType> getIndexPageList() {
        return productTypeMapper.getPageList(new PageRequest(1, 10));
    }

    @Override
    public ProductType getProductType(int id) {
        return productTypeMapper.getProductTypeById(id);
    }

    @Override
    public ReturnResult update(ProductType productType) {
        ProductType dbProductType = productTypeMapper.getProductTypeByNameOrCode(productType.getTypeName(), productType.getTypeCode());
        if (dbProductType != null) {
            if (dbProductType.getTypeName().equals(productType.getTypeName())) {
                return ReturnResult.Error("类型名称已存在");
            } else {
                return ReturnResult.Error("类型代码已存在");
            }
        }else {
            if (productTypeMapper.update(productType) > 0) {
                return ReturnResult.Success("提交成功");
            }
        }
       /* if (productTypeMapper.update(productType) > 0) {
            return ReturnResult.Success("提交成功");
        }*/
        return ReturnResult.Error("提交失败");
    }


}
