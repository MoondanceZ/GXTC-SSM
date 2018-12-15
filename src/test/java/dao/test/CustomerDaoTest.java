package dao.test;

import com.rk.dao.CustomerMapper;
import com.rk.entity.Customer;
import controller.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class CustomerDaoTest extends BaseTest {
    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void insertCustomerTest(){
        try {
            Customer customer = new Customer();
            customer.setAccount("testDao");
            customer.setBalance(100.0);
            customer.setName("testDao");
            customer.setEmail("abc@qq.com");
            customer.setPhone("13812331233");
            customerMapper.insert(customer);
            Assert.notNull(customer.getId());
            /* 这里发生异常是不会回滚的,因为此方法并没有被纳入事务管理中 */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
