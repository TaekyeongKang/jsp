package kr.or.ddit.member.dao;

import kr.or.ddit.member.model.MemberVO;

public interface MemberDaoI {
	
	public MemberVO getMember(String userId);
	
}
