package kr.or.ddit.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVO;


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
	
}
