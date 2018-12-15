package controller.test;

import com.rk.dto.request.SignInRequest;
import com.rk.util.JsonUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.ws.rs.core.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring/applicationContext-*.xml"})
public class BaseTest {
    protected MockHttpServletRequest request;
    protected MockHttpSession session;
    protected MockHttpServletResponse response;
    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Before
    public void singIn() {
        try {
            SignInRequest signInRequest = new SignInRequest();
            signInRequest.setAccount("qqqq");
            signInRequest.setPassword("1111");
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/signin")
                    .content(JsonUtils.Serialize(signInRequest))
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            this.session = (MockHttpSession) mvcResult.getRequest().getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
