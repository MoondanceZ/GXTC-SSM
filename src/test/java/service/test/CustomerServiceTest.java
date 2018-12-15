package service.test;

import com.rk.entity.Customer;
import com.rk.service.interfaces.CustomerService;
import controller.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//不添加此设置,测试service层的事务管理
//service层与dao层的测试时相同的,不同之处,在于service多数都在配置文件中配置了spring的事务管理
public class CustomerServiceTest extends BaseTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void insertCustomerTest() {
        try {
            Customer customer = new Customer();
            customer.setAccount("test");
            customer.setBalance(100.0);
            customer.setName("test");
            customer.setEmail("abc@qq.com");
            customer.setPhone("13812331233");
            customerService.insert(customer);
            /* 这里发生异常是不会回滚的,因为此方法并没有被纳入事务管理中 */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
