package test;
import java.net.URL;

import main.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; 

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class BankAccountControllerTest {
	
	private MockMvc mockMvc;  
	
	@Autowired  
    private WebApplicationContext webContext;  
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();  
	}
	
	@Test
	public void showAccounts() throws Exception {
		
		mockMvc.perform(get("/accounts/show"))  
        .andExpect(status().isOk())  
        .andExpect(view().name("accounts"));
    }
	
	@Test
	public void addAccount() throws Exception {
		
		mockMvc.perform(get("/accounts/add"))  
        .andExpect(status().isOk())  
        .andExpect(view().name("addAccount"));
	}
	
	@Test
	public void editAccount() throws Exception {
		
		mockMvc.perform(get("/accounts/1/edit"))  
        .andExpect(status().isOk())  
        .andExpect(view().name("addAccount"));
	}
}
