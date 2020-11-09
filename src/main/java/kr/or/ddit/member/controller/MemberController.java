package kr.or.ddit.member.controller;



import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

/**
 * Servlet implementation class MemberServlet
 */
@RequestMapping("/member")
@Controller
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="memberService")
	private MemberServiceI memberService;
	
    
	@RequestMapping("/member")
	public String member(Model model, String userid) {
		
		/*
		 userid 파라미터 받기
		 service 객체 준비 - 호출
		 화면담당 jsp로 위임 
		 */
		
		MemberVO memberVO = memberService.getMember(userid);
		
		model.addAttribute("memberVO", memberVO);
		
//		return "member/member";
		return "tiles/member/memberContent";
		
		
	}

}
