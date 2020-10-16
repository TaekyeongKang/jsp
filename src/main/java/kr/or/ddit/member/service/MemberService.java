package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;

public class MemberService implements MemberServiceI {

	private MemberDaoI memberDao;
	

	
	@Override
	public MemberVO getMember(String userId) {
		memberDao = new MemberDao();
		return memberDao.getMember(userId);
	}

	@Override
	public List<MemberVO> selectAllMember() {
		memberDao = new MemberDao();
		return memberDao.selectAllMember();
	}

	@Override
	public Map<String, Object> selectMemberPageList(PageVO pageVO) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		memberDao = new MemberDao();
		
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

}
