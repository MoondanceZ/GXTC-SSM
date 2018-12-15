package controller.test;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.ws.rs.core.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends BaseTest {
    @Test
    public void getCustomerList() {
        try {
            mockMvc.perform(
                    MockMvcRequestBuilders.get("/customer/pageList?page=1&limit=10&level=&status=&queryString=")
                            //.params()
                            //.content(JsonUtils.Serialize(customerPageRequest))
                            .session(this.session)
                            //.accept(MediaType.APPLICATION_JSON)
                            .characterEncoding("UTF-8")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
