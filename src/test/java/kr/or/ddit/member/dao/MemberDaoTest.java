package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.member.model.MemberVO;

public class MemberDaoTest {

	@Test
	public void getMemberTest() {
		/***Given***/
		MemberDao memberDao = new MemberDao();
		String userId = "brown"; 
		
		MemberVO answerMemberVO = new MemberVO();
		answerMemberVO.setUserId("brown");
		answerMemberVO.setPassword("passBrown");
		
		
		/***When***/
		MemberVO memberVO = memberDao.getMember(userId);

		/***Then***/
		assertEquals("brown", memberVO.getUserId());
		assertEquals("passBrown", memberVO.getPassword());
		
		assertEquals(answerMemberVO, memberVO); //  객체와 객체 비교 => 객체가 가진 모든 속성을 전부 대조하여 확인해줌
												// but Fail 뜸 ∵ 주소값이 달라서
												// MemberVO 클래스에 Equals()를 구현해주면 성공함
		// java 에는 동일과 동치가 있음
		// 동일 : 같은 메모리 공간을 가리킬때
		// 		MemberVO v = new MemberVO(); : v가 가리키는 heap메모리의 영역
		//  	MemberVO p = v;  			 : v가 가리키는 heap메모리의 영역을 똑같이 가리킴
		// ==> if(p==v) : true
		// 동치 : equals
		
		
		
		
	}

}
