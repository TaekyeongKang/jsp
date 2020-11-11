package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;


@Service("memberService")
public class MemberService implements MemberServiceI {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Resource(name = "memberRepository")
	private MemberDaoI memberDao;
	
	public MemberService() {
//		memberDao = new MemberDao();
		// 이 방식은 새롭게 객체를 생성한것, 스프링 빈이 아님! => @Repository 어노테이션을 붙여 스프링 빈으로 생성해 준 것을 재사용 하지 못한 것

	}
	
	@Override
	public MemberVO getMember(String userid) {
		return memberDao.getMember(userid);
	}
	
	@Override
	public List<MemberVO> selectAllMember() {
		return memberDao.selectAllMember();
	}

	@Override
	public Map<String, Object> selectMemberPageList(PageVO pageVO) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberList", memberDao.selectMemberPageList(sqlSession,pageVO));
		
		//15건, 페이지 사이즈를 7로 가정했을 때 3개의 페이지가 나와야 한다. 
		int totalCnt = memberDao.selectMemberTotalCnt(sqlSession);
		int pageSize = pageVO.getPageSize();
		int pages = (int)(Math.ceil((double)totalCnt/pageSize));
		map.put("pages", pages);
		
		sqlSession.close();
		return map;
	}

	@Override
	public int insertMember(MemberVO memberVO) {
		
//		logger.debug("첫번째 insert 시작 전");
//		memberDao.insertMember(memberVO);
//		logger.debug("첫번째 insert 종료 후");
//		
		// 첫번째 쿼리는 정상적으로 실행되지만
		// 두번째 쿼리에서 동일한 데이터를 입력하여  PRIMARY KEY  제약조건에 의해 
		// SQL 실행 실패
		// 첫번째 쿼리는 성공했지만 트랜잭션 설정을 service 레벨에 설정을 하였기 때문에
		// 서비스 메소드에서 실행된 모든 쿼리를 rollback 처리한다.
		
//		logger.debug("두번째 insert 시작 전");
//		memberDao.insertMember(memberVO);
//		logger.debug("두번째 insert 종료 후");
		
		return memberDao.insertMember(memberVO);
	}

	@Override
	public int deleteMember(String userid) {
		
		int deleteCnt = memberDao.deleteMember(userid);
		
		return deleteCnt;
	}

	@Override
	public int updateMember(MemberVO memberVO) {
		int updateCnt = memberDao.updateMember(memberVO);
		return updateCnt;
	}
	
}
