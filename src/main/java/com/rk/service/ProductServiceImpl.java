package com.rk.service;

import com.rk.dao.ProductItemMapper;
import com.rk.dao.ProductMapper;
import com.rk.dto.response.ReturnResult;
import com.rk.entity.Product;
import com.rk.entity.ProductItem;
import com.rk.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Qin_Yikai on 2018-09-20.
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductItemMapper productItemMapper;

    @Override
    public Integer[] getByTypeIds(Integer[] keys) {
        return productMapper.getByTypeIds(keys);
    }

    @Override
    public ReturnResult updateOrAdd(Product product) {
        ReturnResult returnResult = super.updateOrAdd(product);
        if (!returnResult.isSuccess())
            return returnResult;
        List<ProductItem> productItems = product.getProductItems();
        if (productItems != null && productItems.size() > 0) {
            for (ProductItem productItem : productItems) {
                productItem.setProductId(product.getId());
                if (productItem.getId() == null || productItem.getId() == 0) {
                    productItemMapper.insert(productItem);
                } else {
                    productItemMapper.update(productItem);
                }
            }
        }
        return returnResult;
    }
}
