package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;

public class MemberDao implements MemberDaoI{

	@Override
	public MemberVO getMember(String userId) {
		// 원래는 db에서 데이터를 조회하는 로직이 있어야 하나
		// 우리는 controller 기능에 집중 => 하드 코딩을 통해 dao, service는 간략하게 넘어간다
		// = Dao의 기능을 흉내내는 Mock(가짜)객체 생성
		
		/*MemberVO memberVO = new MemberVO();
		memberVO.setUserId("brown");
		memberVO.setPassword("passBrown");*/
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();

		// select
		// 한 건  : selectOne
		// 여러 건 : selectList
		
		MemberVO memberVO = (MemberVO)sqlSession.selectOne("member.getMember",userId);
		
		sqlSession.close();	// session 객체 사용 완료시 닫아주어 반납하기
		
		return memberVO;
	}

	@Override
	public List<MemberVO> selectAllMember() {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<MemberVO> memberList = sqlSession.selectList("member.selectAllMember"); // sql id 랑 method 이름 동일하게 
	
		
		/* select 제외 다른 쿼리 실행시에는 db 데이터에 변경이 있으므로  commit / rollback 해주어야 함.
		sqlSession.commit();
		sqlSession.rollback();
		*/
		
		sqlSession.close();
		
		return memberList;
	}

	@Override
	public List<MemberVO> selectMemberPageList(SqlSession sqlSession, PageVO pageVO) {
		
		//SqlSession sqlSession = MybatisUtil.getSqlSession();	
		
		//List<MemberVO> memberList = sqlSession.selectList("member.selectMemberPageList", pageVO);
		
		//sqlSession.close();
		
		return sqlSession.selectList("member.selectMemberPageList", pageVO);
	}

	@Override
	public int selectMemberTotalCnt(SqlSession sqlSession) {
		
		//SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		//int totalCnt = sqlSession.selectOne("member.selectMemberTotalCnt");
		
		//sqlSession.close();

		return sqlSession.selectOne("member.selectMemberTotalCnt");
	}
	
	

}
