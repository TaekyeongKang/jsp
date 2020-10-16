package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;

public interface MemberServiceI {

	MemberVO getMember(String userId);
	
	List<MemberVO> selectAllMember();

	Map<String, Object> selectMemberPageList(PageVO pageVO);

}
