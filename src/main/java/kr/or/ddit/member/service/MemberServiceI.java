package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;

public interface MemberServiceI {
	MemberVO getMember(String userid);
	
	List<MemberVO> selectAllMember();

	Map<String, Object> selectMemberPageList(PageVO pageVO);
	
	int insertMember(MemberVO memberVO);

	int deleteMember(String userid);

	int updateMember(MemberVO memberVO);
	
}
