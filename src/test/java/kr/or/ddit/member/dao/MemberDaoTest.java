package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;

public class MemberDaoTest {
	
	// 테스트를 할 때 마다 데이터를 계속해서 넣을 수 없음 
	// 테스트 메소드 실행 사이클: (모든 테스트 메소드)
	// @BeforClass (static)
	// 		@Before => @Test => @After
	// @AfterClass (static)
	// -> 삭제하는 메서드를 모든 @Test 메서드 전에 일괄적으로 넣는 로직
	
	MemberDaoI memberDao;
	
	@Before  // 항상 public, void, 인자 xxx
	public void setup() {
		memberDao = new MemberDao();
		String userid = "ktk";
		memberDao.deleteMember(userid);
	}
	
	@Test
	public void getMemberTest() {
		/***Given***/
//		MemberDaoI memberDao = new MemberDao();  // filed & @Before 메서드에서 생성
		String userId = "brown"; 
		
		MemberVO answerMemberVO = new MemberVO();
		answerMemberVO.setUserid("brown");
		answerMemberVO.setPass("brownPass");
		
		
		/***When***/
		MemberVO memberVO = memberDao.getMember(userId);

		/***Then***/
		assertEquals("brown", memberVO.getUserid());
		assertEquals("brownPass", memberVO.getPass());
		
		//assertEquals(answerMemberVO, memberVO); //  객체와 객체 비교 => 객체가 가진 모든 속성을 전부 대조하여 확인해줌
												// but Fail 뜸 ∵ 주소값이 달라서
												// MemberVO 클래스에 Equals()를 구현해주면 성공함
		// java 에는 동일과 동치가 있음
		// 동일 : 같은 메모리 공간을 가리킬때
		// 		MemberVO v = new MemberVO(); : v가 가리키는 heap메모리의 영역
		//  	MemberVO p = v;  			 : v가 가리키는 heap메모리의 영역을 똑같이 가리킴
		// ==> if(p==v) : true
		// 동치 : equals
		
	}
	
	@Test
	public void selectAllMemberTest() {
		/***Given***/
//		MemberDaoI memberDao = new MemberDao(); // filed & @Before 메서드에서 생성
		
		/***When***/
		List<MemberVO> memberList = memberDao.selectAllMember();

		/***Then***/
		// list 비교하려면 
		assertEquals(15, memberList.size()); // 이것도 누군가 db에 자료를 입력하는 순간 fail -> test 코드 작성 시 db 초기화 하는 방법 필요 
		// assertEquals("brown", memberList.get(0).getUserid());  // 순서를 정의하지 않았기 때문에 올바르지는 않음
		
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
//		MemberDaoI memberDao = new MemberDao();	// filed & @Before 메서드에서 생성
		PageVO pageVO = new PageVO(1, 7);
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		//int page=1;
		
		/***When***/
		List<MemberVO> memberList = memberDao.selectMemberPageList(sqlSession,pageVO);

		/***Then***/
		// list 비교하려면 
		assertEquals(7, memberList.size()); // 이것도 누군가 db에 자료를 입력하는 순간 fail -> test 코드 작성 시 db 초기화 하는 방법 필요 
		// assertEquals("brown", memberList.get(0).getUserid());  // 순서를 정의하지 않았기 때문에 올바르지는 않음
		
	}
	
	@Test
	public void selectMemberTotalCntTest() {
		/***Given***/
//		MemberDaoI memberDao = new MemberDao();	// filed & @Before 메서드에서 생성
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		/***When***/
		int totalCnt = memberDao.selectMemberTotalCnt(sqlSession);

		/***Then***/
		assertEquals(15, totalCnt); 
		
	}
	
	@Test
	public void insertMemberDaoTest() {
		/***Given***/
		MemberDaoI memberDao = new MemberDao();	// filed & @Before 메서드에서 생성
		MemberVO memberVO = new MemberVO("ktk", "pass1234", "강태경", "pc-13", "대전 중구 중앙로 76", "영민빌딩 4층 404호", "34940", "d:\\profile\\brown.png", "brown.png");

		/***When***/
		int insertCnt = memberDao.insertMember(memberVO);
		
		/***Then***/
		assertEquals(1, insertCnt);

	}
	
	@Test
	public void updateMemberTest() {
		/***Given***/
		MemberDaoI memberDao = new MemberDao();	
		MemberVO memberVO = new MemberVO("ktk", "pass1234", "강태경", "aaa", "대전 중구 중앙로 76", "영민빌딩 4층 404호", "34940", "d:\\profile\\brown.png", "brown.png");

		/***When***/
		int updateCnt = memberDao.updateMember(memberVO);
		MemberVO updatedMemberVO = memberDao.getMember("ktk");
		
		/***Then***/
		assertEquals("aaa", updatedMemberVO.getAlias());

	}

}
