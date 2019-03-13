package com.rk.service;

import com.rk.dao.ProductItemMapper;
import com.rk.dao.ProductMapper;
import com.rk.dto.response.ReturnResult;
import com.rk.entity.Product;
import com.rk.entity.ProductItem;
import com.rk.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        Long productId = product.getId();
        ReturnResult returnResult = super.updateOrAdd(product);
        if (!returnResult.isSuccess())
            return returnResult;
        List<ProductItem> productItems = product.getProductItems();
        if (productItems != null && productItems.size() > 0) {
            List<ProductItem> existProductItems = new ArrayList<>();
            if (productId != null && productId != 0) {
                existProductItems = productItemMapper.getByProductId(productId);
            }
            //需要删除的
            List<Long> itemIds = productItems.stream().filter(m -> m.getId() != null && m.getId() != 0)
                    .map(ProductItem::getId).collect(Collectors.toList());
            if (itemIds.size() > 0) {
                List<Long> delItemIds = existProductItems.stream().filter(m -> !itemIds.contains(m.getId()))
                        .map(ProductItem::getId).collect(Collectors.toList());
                if(delItemIds.size()>0){
                    productItemMapper.delete(delItemIds.toArray(new Long[delItemIds.size()]));
                }
            }

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
