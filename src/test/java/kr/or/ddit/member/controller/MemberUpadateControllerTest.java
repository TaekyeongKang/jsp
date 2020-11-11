package kr.or.ddit.member.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.InputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.WebTestConfig;

public class MemberUpadateControllerTest extends WebTestConfig{

	@Test
	public void memberUpdateTest() throws Exception {
		mockMvc.perform(get("/member/memberUpdate")
						.param("userid", "aa"))
				.andExpect(view().name("tiles/member/memberUpdateContent"));
	}

	// 멤버 업데이트 테스트 (정상적인 경우)
	@Test
	public void updateProcessSuccessTest() throws Exception {
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile profile = new MockMultipartFile("profile","sally.png", "image/png", is);
		
		mockMvc.perform(fileUpload("/member/updateProcess")
						.file(profile)
						.param("userid", "aa")
						.param("usernm", "bb")
						.param("alias", "aa")
						.param("pass", "aa")
						.param("addr1", "aa")
						.param("addr2", "aa")
						.param("zipcode", "11111")
				)
		.andExpect(view().name("redirect:/member/memberUpdate?userid=aa"))
		.andExpect(status().is(302));
	}
	
	// 멤버 업데이트 테스트 (실패한 경우)
	@Test
	public void updateProcessFailTest() throws Exception {
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile profile = new MockMultipartFile("profile","sally.png", "image/png", is);

		mockMvc.perform(fileUpload("/member/updateProcess")
				.file(profile)
				.param("userid", "bb")
				.param("usernm", "bb")
				.param("alias", "aa")
				.param("pass", "aa")
				.param("addr1", "aa")
				.param("addr2", "aa")
				.param("zipcode", "11111"))
		.andExpect(view().name("tiles/member/memberUpdateContent"));
	}
}
