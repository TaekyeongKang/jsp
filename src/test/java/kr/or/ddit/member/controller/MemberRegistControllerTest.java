package kr.or.ddit.member.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.InputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.WebTestConfig;

public class MemberRegistControllerTest extends WebTestConfig {

	@Test
	public void memberRegistTest() throws Exception {
		mockMvc.perform(get("/member/memberRegist"))
				.andExpect(view().name("member/memberRegist"));
	}
	
	
	// 멤버등록 테스트 (정상적인 경우)
	@Test
	public void registProcessSuccessTest() throws Exception {
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile profile = new MockMultipartFile("profile","sally.png", "image/png", is);
		mockMvc.perform(fileUpload("/member/registProcess")
						.file(profile)
						.param("userid", "aa")
						.param("usernm", "aa")
						.param("alias", "aa")
						.param("pass", "aa")
						.param("addr1", "aa")
						.param("addr2", "aa")
						.param("zipcode", "11111")
						)
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/member/memberList"));
	}
	// 멤버등록 테스트 (실패할 경우)
		@Test
		public void registProcessFailTest() throws Exception {
			InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
			MockMultipartFile profile = new MockMultipartFile("profile","sally.png", "image/png", is);
			mockMvc.perform(fileUpload("/member/registProcess")
							.file(profile)
							.param("userid", "aa")
							.param("usernm", "aa")
							.param("alias", "aa")
							.param("pass", "aa")
							.param("addr1", "aa")
							.param("addr2", "aa")
							.param("zipcode", "11111")
							)
					.andExpect(view().name("member/memberRegist"));
		}


}
