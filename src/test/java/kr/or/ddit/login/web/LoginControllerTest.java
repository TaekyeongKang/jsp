package kr.or.ddit.login.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;


public class LoginControllerTest extends WebTestConfig { // 공통된 어노테이션 설정이 공통 -> 부모로 따로 빼서 상속받도록

	
	
	@Test
	public void getViewTest() throws Exception {
		mockMvc.perform(get("/login/view")).andExpect(status().isOk()).andExpect(view().name("login/view")); 
		// get방식으로 uri로 요청을 보낸다
		// status().isOk() : 요청을 보냈을 때 상태코드 200인지
		// view().name("login/view") : 요청을 했을 때 뷰의 이름이 "원하는 이름" 인가 
	}
	
	
	
	// 로그인 요청 테스트 (정상적인 경우)
	@Test
	public void processSuccessTest() throws Exception {
		mockMvc.perform(post("/login/process")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)	// 인자로 받는 @Requestbody 때문에 헤더 정보를 명확하게 명시해주기 위해 설정
						.param("userid", "brown")
						.param("pass", "brownPass"))
			   .andExpect(status().is(200))
			   .andExpect(view().name("main"))
			   .andExpect(model().attributeExists("to_day"));
	}
	
	// 로그인 요청 테스트 (실패하는 경우)
	@Test
	public void processFailTest() throws Exception {
		MvcResult result = mockMvc.perform(post("/login/process")
											.contentType(MediaType.APPLICATION_FORM_URLENCODED)
											.param("userid", "brown")
											.param("pass", "brownPassFail"))
								  .andReturn();  
		ModelAndView mav = result.getModelAndView(); // 모델객체와 view 객체를 관리해주는 클래스
		
		assertEquals("login/view",mav.getViewName());
		assertEquals("fail", mav.getModel().get("msg"));
		
	}

}
