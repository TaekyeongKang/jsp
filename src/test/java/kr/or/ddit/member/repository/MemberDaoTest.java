package kr.or.ddit.member.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.validator.internal.metadata.aggregated.rule.VoidMethodsMustNotBeReturnValueConstrained;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;


public class MemberDaoTest extends ModelTestConfig{
	
	@Resource(name = "memberRepository")
	private MemberDaoI memberDao;
	
	@Test
	public void getMemberTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		MemberVO memberVO = (MemberVO)memberDao.getMember(userid);
		
		/***Then***/
		assertEquals("브라운", memberVO.getUsernm());
	}
	
	@Test
	public void selectAllMemberTest() {
		/***Given***/
		

		/***When***/
		List<MemberVO> memberList = memberDao.selectAllMember();
		
		/***Then***/
		assertEquals(18, memberList.size());
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);
		pageVO.setPageSize(5);

		/***When***/
		List<MemberVO> memberList = memberDao.selectMemberPageList(pageVO);
		
		/***Then***/
		assertEquals(5, memberList.size());
	}
	
	@Test
	public void selectMemberTotalCntTest() {
		/***Given***/

		/***When***/
		int memberTotalCnt = memberDao.selectMemberTotalCnt();
		
		/***Then***/
		assertEquals(18, memberTotalCnt);
	}

	
	@Test
	public void insertMember_SUCCESS_Test() {
		/***Given***/
		MemberVO memberVO = new MemberVO("temp", "dditPass", "대덕인재", "개발원", 
				 							"", "", "", "", "");

		/***When***/
		int insertCnt = memberDao.insertMember(memberVO);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteMemberTest() {
		/***Given***/
		String userid = "aa"; 
		
		/***When***/
		int deleteCnt = memberDao.deleteMember(userid);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void updateMemberTest() {
		/***Given***/
		MemberVO memberVO = new MemberVO("aa", "aa", "bb", "aa", 
					"", "", "", "", "");
		
		/***When***/
		int updateCnt = memberDao.updateMember(memberVO);
		
		/***Then***/
		assertEquals(1, updateCnt);
		assertEquals("bb", memberVO.getUsernm());
	}
	
}
