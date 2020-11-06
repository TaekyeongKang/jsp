package kr.or.ddit.member.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;
import kr.or.ddit.member.model.MemberVO;

public class MemberControllerTest extends WebTestConfig{

	// 멤버 상세조회 테스트 (정상적인 경우)
	@Test
	public void memberSuccessTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/member/member")
						.param("userid", "brown")).andReturn();
		ModelAndView mav = result.getModelAndView();
		MemberVO memberVO = (MemberVO)mav.getModel().get("memberVO");
		assertEquals("brown", memberVO.getUserid());
		
		
	}
	
	// 멤버 상세조회 테스트 (실패할 경우)
		@Test
		public void memberFailTest() throws Exception {
			MvcResult result = mockMvc.perform(get("/member/member")
							.param("userid", "bb")).andReturn();
			ModelAndView mav = result.getModelAndView();
			MemberVO memberVO = (MemberVO)mav.getModel().get("memberVO");
			assertNull(memberVO);
			
			
		}


}
