package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVO;

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
		
		return memberVO;
	}

	@Override
	public List<MemberVO> selectAllMember() {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<MemberVO> memberList = sqlSession.selectList("member.selectAllMember"); // sql id 랑 method 이름 동일하게 
		
		return memberList;
	}
	
	

}
