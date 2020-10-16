package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberServiceI memberService;
	
	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page_str = request.getParameter("page");
		int page = page_str == null ? 1 : Integer.parseInt(page_str);
		request.setAttribute("page", page);
		
		
		String pageSize_str = request.getParameter("pageSize");
		int pageSize = pageSize_str == null ? 5 : Integer.parseInt(pageSize_str);
		request.setAttribute("pageSize", pageSize);
		
		
		PageVO pageVO = new PageVO(page,pageSize);
		
		
//		List<MemberVO> memberList = memberService.selectPageMember(page);
		Map<String, Object> map = memberService.selectMemberPageList(pageVO); 
		
		request.setAttribute("memberList", map.get("memberList"));
		request.setAttribute("pages", map.get("pages"));
		
		request.getRequestDispatcher("/member/memberList.jsp").forward(request, response);
	}
	

}
