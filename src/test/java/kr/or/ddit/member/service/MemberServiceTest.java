package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;

public class MemberServiceTest extends ModelTestConfig{

	@Resource(name = "memberService")
	private MemberServiceI memberService;
	
	@Test
	public void getMemberTest() {
		/***Given***/
		String userId = "brown"; 
		
		MemberVO answerMemberVO = new MemberVO();
		answerMemberVO.setUserid("brown");
		answerMemberVO.setPass("brownPass");
		
		
		/***When***/
		MemberVO memberVO = memberService.getMember(userId);

		/***Then***/
		assertEquals("brown", memberVO.getUserid());
		assertEquals("brownPass", memberVO.getPass());
		
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		PageVO pageVO = new PageVO(1, 5);
		
		/***When***/
		Map<String, Object> map = memberService.selectMemberPageList(pageVO);
		List<MemberVO> memberList = (List<MemberVO>)map.get("memberList");
		
		int pages = (int)map.get("pages");
		
		/***Then***/
		assertEquals(5, memberList.size());  
		assertEquals(4, pages);
	}
	
	
	@Test
	public void insertMember_SUCCESS_Test() {
		/***Given***/
		MemberVO memberVO = new MemberVO("temp", "dditPass", "대덕인재", "개발원", 
										 "", "", "", "", "");
		
		/***When***/
		int insertCnt = memberService.insertMember(memberVO);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
//	//@Test
//	public void insertMember_FAIL_Test() {
//		/***Given***/
//		MemberVO memberVO = new MemberVO("ddit", "dditPass", "대덕인재", "개발원", 
//										 "", "", "", "", "");
//		
//		/***When***/
//		int insertCnt = memberService.insertMember(memberVO);
//
//		/***Then***/
//		assertEquals(1, insertCnt);
//	}
	
	@Test
	public void deleteMemberTest() {
		/***Given***/
		String userid = "aa";
		
		/***When***/
		int deleteCnt = memberService.deleteMember(userid);

		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	
	@Test
	public void updateMemberTest() {
		/***Given***/
		MemberVO memberVO = new MemberVO("ktk1", "pass1234", "강태경", "bbb", "대전 중구 중앙로 76", "영민빌딩 4층 404호", "34940", "d:\\profile\\brown.png", "brown.png");

		/***When***/
		int updateCnt = memberService.updateMember(memberVO);
		MemberVO updatedMemberVO = memberService.getMember("ktk1");
		
		/***Then***/
		assertEquals("bbb", updatedMemberVO.getAlias());

	}

}
