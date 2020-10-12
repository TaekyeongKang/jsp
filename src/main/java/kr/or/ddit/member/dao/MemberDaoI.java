package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.model.MemberVO;

public interface MemberDaoI {
	
	public MemberVO getMember(String userId);
	
	public List<MemberVO> selectAllMember();
	
}
