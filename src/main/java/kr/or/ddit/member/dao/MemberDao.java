package kr.or.ddit.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVO;

@Repository("memberRepository")
public class MemberDao implements MemberDaoI{

	@Override
	public MemberVO getMember(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		MemberVO memberVO = (MemberVO)sqlSession.selectOne("member.getMember",userid);
		
		sqlSession.close();
		
		return memberVO;
	}

}
