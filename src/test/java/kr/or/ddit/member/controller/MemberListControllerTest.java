package kr.or.ddit.member.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.WebTestConfig;
import kr.or.ddit.member.model.PageVO;

public class MemberListControllerTest extends WebTestConfig{

	@Test
	public void memberListTest() throws Exception {
		PageVO pageVO = new PageVO();
		mockMvc.perform(get("/member/memberList")
						.param("page", "1")
						.param("pageSize", "7"))
				.andExpect(view().name("member/memberList"))
				.andExpect(status().isOk());
	}

}
