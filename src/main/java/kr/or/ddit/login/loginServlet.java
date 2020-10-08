package kr.or.ddit.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(loginServlet.class);
	// login 화면을 클라이언트에게 응답으로 생성
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	// login 화면에서 사용자가 보낸 아이디,비밀번호를 사용하여 로그인 처리 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		logger.debug("userId : {}, password : {} ", userId, password);
		
		// 파라미터로 온 userId가 db상에 존재하는지 확인하고, 비밀번호가 데이터베이스에 저장된 비밀번호와 일치하는지 확인
		// select * 
		// from 회원
		// where 회원 아이디 = 파라미터로 넘어온 userId;
		// => 메소드  public MemberVO getMember(String userId)
		
		MemberServiceI memberService = new MemberService();
		MemberVO memberVO = memberService.getMember(userId);
		
		// db에 등록되지 않은 경우 or 비밀번호가 불일치할 경우-> login 페이지 반환
		if(memberVO == null || !memberVO.getPassword().equals(password)) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		// 비밀번호가 일치할 경우 ->  main 페이지로 이동
		else if(memberVO.getPassword().equals(password)){
			request.getSession().setAttribute("S_MEMBER", memberVO);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} 
			
		
		// 쿠키정보 확인
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			logger.debug("name : {} , value : {}", cookie.getName(),cookie.getValue());
		}
		
		// JSESSIONID 쿠키 설정되는 방식 
		Cookie cookie = new Cookie("SERVERCOOKIE","COOKIEVALUE");
		cookie.setMaxAge(60*60*24); // 하루로 설정 ( 단위 : 초)
		
		// 응답 할 때 이 쿠키값을 가져가서 설정해라
		response.addCookie(cookie);
		
	}

}
