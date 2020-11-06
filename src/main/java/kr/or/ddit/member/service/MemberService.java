package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;


@Service("memberService")
public class MemberService implements MemberServiceI {

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
		
		int insertCnt = memberDao.insertMember(memberVO);
		
		return insertCnt;
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
