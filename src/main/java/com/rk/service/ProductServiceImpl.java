package com.rk.service;

import com.rk.dao.ProductMapper;

import com.rk.dto.LayPage;
import com.rk.dto.ReturnResult;
import com.rk.dto.request.ProductPageRequest;
import com.rk.entity.Product;
import com.rk.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;


    /**
     * @return
     */
    @Override
    public List<Product> getAllProduct() {
        return productMapper.queryAll();
    }

    @Override
    public LayPage<List<Product>> getPageList(ProductPageRequest pageRequest) {
        List<Product> products = productMapper.getPageList(pageRequest);
        int totalCount = productMapper.getPageListTotalCount(pageRequest);
        return new LayPage<>("", "", totalCount, products);
    }

    @Override
    public Product getProduct(long id) {
        return productMapper.getProductById(id);
    }

    @Override
    public ReturnResult updateOrAdd(Product product) {
        if (product.getId() != null && product.getId() != 0) {  //修改
            product.setModifyDate(new Date());
            if (productMapper.update(product) > 0)
                return ReturnResult.Success("提交成功");
        } else {
            product.setCreateDate(new Date());
            if (productMapper.insert(product) > 0)
                return ReturnResult.Success("提交成功");
        }

        return ReturnResult.Error("提交失败");
    }
}
