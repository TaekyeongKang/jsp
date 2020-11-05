package kr.or.ddit.multiparam.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// static import => 클래스명 없이 메소드명만으로 호출 가능
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;

public class MultiParamControllerTest extends WebTestConfig {


	@Test
	public void multiParamViewTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/multi/view")).andReturn();
		
		ModelAndView mav = result.getModelAndView();
		assertEquals("multi/view", mav.getViewName());
	
	}
	
	@Test
	public void multiParamSubmitTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/multi/submit")
								  			.param("userid","brown","sally","cony"))
								  .andDo(print())
								  .andReturn();
		
		ModelAndView mav = result.getModelAndView();
		assertEquals("multi/view", mav.getViewName());
	
	}
	
	

}
