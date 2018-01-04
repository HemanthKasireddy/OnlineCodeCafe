package com.bridgelabz.onlinecoadingcafe;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineCoadingCafeApplicationTests {

	/*@Autowired
	WebApplicationContext wsc;
	
	private MockMvc mok;
	@Before
	public void setUp() {
		this.mok=MockMvcBuilders.webAppContextSetup(this.wsc).build();
	}
	@Test
	public void contextLoads() throws Exception {
		ResultActions result=mok.perform(get("/getcode").contentType(MediaType.ALL));
		.andExpect(MockMvcResultMatchers.content().contentType("application/json;char"))
		
		
	}*/

}
