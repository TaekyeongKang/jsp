package kr.or.ddit.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.member.model.MemberVO;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 사용자가 정상적으로 접속 했는지 체그
		// loginController 컨트롤러 통해 정상적으로 접속 했을 경우
		// SESSION에 S_MEMBER 속성이 존재해야 함
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("S_MEMBER");
		
		// 로그인 페이지로 이동
		if(memberVO == null) {
			response.sendRedirect("/login/view");
			return false;
		}
		
		return true;
	}
	

}
