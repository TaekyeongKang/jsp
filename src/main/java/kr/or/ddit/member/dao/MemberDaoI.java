package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;

public interface MemberDaoI {
	
	MemberVO getMember(String userId);
	
	List<MemberVO> selectAllMember();

	
	// 아래 두개의 메소드 를 서비스단에서는 하나의 메소드에서 호출, 즉 하나의 트렌젝션을 수행하는데 호출되는 메서드들 이다. 
	// ==> SqlSession 을 dao 구현단에서 각 메소드 안해서 각각 생성할 경우 하나의 트렌젝션이 아니게 됨!!
	// 한개의 salSession 을 가지고 그 세션을 통해서 데이터를 주고받아야 하는데 각각 따로저장되는 꼴
	// => 하나의 트렌젝션으로 묶기 위해서 SqlSession를 서비스단에서 한번 생성 한 후 , 
	// dao 메서드 를 호출할 때 인자로 이 sqlSession 을 넘겨주어 동일한 session을 사용할 수 있도록 해주어야 함
	List<MemberVO> selectMemberPageList(SqlSession sqlSession, PageVO pageVO);
	
	int selectMemberTotalCnt(SqlSession sqlSession);
	
	// 몇건의 행이 영향을 받았는지를 int 값으로 리턴받는다.
	int insertMember(MemberVO memberVO);
	
	int deleteMember(String userid);

	int updateMember(MemberVO memberVO);
	
}
