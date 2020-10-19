package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;

public class MemberServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
	
	@Test
	public void getMemberTest() {
		/***Given***/
		MemberServiceI memberService = new MemberService();
		String userId = "brown"; 
		
		MemberVO answerMemberVO = new MemberVO();
		answerMemberVO.setUserid("brown");
		answerMemberVO.setPass("brownPass");
		
		
		/***When***/
		MemberVO memberVO = memberService.getMember(userId);

		/***Then***/
		assertEquals("brown", memberVO.getUserid());
		assertEquals("brownPass", memberVO.getPass());
		
		//assertEquals(answerMemberVO, memberVO);
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		MemberServiceI memberService = new MemberService();
		//int page = 1;
		PageVO pageVO = new PageVO(1, 7);
		
		/***When***/
		// memberList 확인
		Map<String, Object> map = memberService.selectMemberPageList(pageVO);
		List<MemberVO> memberList = (List<MemberVO>)map.get("memberList");
		
		// 생성해야 할 page 수
		int pages = (int)map.get("pages");
		
		/***Then***/
		// list 비교하려면 
		assertEquals(7, memberList.size()); // 이것도 누군가 db에 자료를 입력하는 순간 fail -> test 코드 작성 시 db 초기화 하는 방법 필요 
		// assertEquals("brown", memberList.get(0).getUserid());  // 순서를 정의하지 않았기 때문에 올바르지는 않음
		assertEquals(3, pages);
	}
	
	@Test
	public void localeListTest() {
		Locale[]  locales = SimpleDateFormat.getAvailableLocales();
		for(Locale locale : locales) {
			//logger.debug(locale.toString());
			logger.debug("{}", locale);
		}
	}
	

}
