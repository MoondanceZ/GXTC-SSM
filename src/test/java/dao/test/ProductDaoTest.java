package dao.test;

import com.rk.dao.ProductMapper;
import com.rk.dto.request.ProductPageRequest;
import com.rk.entity.Product;
import controller.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void getPageListTest() {
        ProductPageRequest pageRequest = new ProductPageRequest();
        pageRequest.setLimit(10);
        pageRequest.setPage(1);
        List<Product> pageList = productMapper.getPageList(pageRequest);
        Assert.notEmpty(pageList);
    }
}
