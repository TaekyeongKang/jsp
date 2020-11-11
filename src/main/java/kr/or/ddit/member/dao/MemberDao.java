package kr.or.ddit.member.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;

@Repository("memberRepository")
public class MemberDao implements MemberDaoI{

	@Resource(name = "sqlSessionTemplate") // datasource.xml에서 생성한 빈객체 주입
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberVO getMember(String userid) {
//		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		MemberVO memberVO = (MemberVO)sqlSession.selectOne("member.getMember",userid);
		
//		sqlSession.close();
		
		return memberVO;
	}
	
	@Override
	public List<MemberVO> selectAllMember() {
		
//		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<MemberVO> memberList = sqlSession.selectList("member.selectAllMember"); // sql id 랑 method 이름 동일하게 
	
		
		/* select 제외 다른 쿼리 실행시에는 db 데이터에 변경이 있으므로  commit / rollback 해주어야 함.
		sqlSession.commit();
		sqlSession.rollback();
		*/
		
//		sqlSession.close();
		
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

	@Override
	public int insertMember(MemberVO memberVO) {
		
//		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		// insert  query로 인해 영향을 받은 행의 값을 리턴
//		int insertCnt = 0; 
//		insertCnt = sqlSession.insert("member.insertMember", memberVO); 
//		
//		// insert 쿼리는 select 쿼리와 다르게 테이블을 변경하는 쿼리
//		// -> 조건으로  commit / rollback 처리 해주어야 함
//		if(insertCnt == 1) { // 한건이 삽입되었을 때 commit
////			sqlSession.commit();
//		}
//		else { // 한건이 아니라면 정상적이지 않음 -> rollback
////			sqlSession.rollback();
//		}
		
//		sqlSession.close();
		
		return sqlSession.insert("member.insertMember", memberVO); 
	}

	@Override
	public int deleteMember(String userid) {
//		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int deleteCnt = sqlSession.delete("member.deleteMember",userid);
		if(deleteCnt == 1) {
//			sqlSession.commit();
		}
		else {
//			sqlSession.rollback();
		}
//		sqlSession.close();
		return deleteCnt;
	}

	@Override
	public int updateMember(MemberVO memberVO) {
//		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("member.updateMember",memberVO);
		if(updateCnt == 1) {
//			sqlSession.commit();
		}
		else {
//			sqlSession.rollback();
		}
//		sqlSession.close();
		return updateCnt;
	}
	
	

}
