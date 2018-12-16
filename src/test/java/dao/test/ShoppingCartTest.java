package dao.test;

import com.rk.dao.ShoppingCartMapper;
import com.rk.dto.request.PageRequest;
import com.rk.entity.ShoppingCart;
import controller.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

public class ShoppingCartTest extends BaseTest {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Test
    public void getCartTest() {
        ShoppingCart cart = shoppingCartMapper.getByPrimaryKey(1L);
        Assert.notNull(cart);
    }

    @Test
    public void cartPageListTest() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setLimit(10);
        pageRequest.setPage(1);
        List<ShoppingCart> pageList = shoppingCartMapper.getPageList(pageRequest);
        Assert.notEmpty(pageList);
    }
}
