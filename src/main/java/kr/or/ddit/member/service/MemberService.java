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
	
	public MemberService() {
		memberDao = new MemberDao();

	}
	
	@Override
	public MemberVO getMember(String userId) {
		return memberDao.getMember(userId);
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
