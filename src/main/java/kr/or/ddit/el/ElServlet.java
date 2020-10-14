package kr.or.ddit.el;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

/**
 * Servlet implementation class ElServlet
 */
@WebServlet("/el")
public class ElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ElServlet.class);
	
	
	// 필드로 service 객체 선언
	private MemberServiceI memberService;
	
	@Override
		public void init() throws ServletException {
			memberService = new MemberService();
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/el/el.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scope = request.getParameter("scope");
		logger.debug("scope : {}", scope);
		
		if(scope.equals("requestValue")) {	// request 일때
			request.setAttribute("attr", scope); // request에만 저장
		}
		else if(scope.equals("sessionValue")) {	// session 일 때 
			request.setAttribute("attr", scope);	// request에 저장
			request.getSession().setAttribute("attr", scope);;	// session에 저장
			
		}
		else if(scope.equals("applicationValue")) { // application 일 때
			request.setAttribute("attr", scope);	// request에 저장
			request.getSession().setAttribute("attr", scope);	// session에 저장
			getServletContext().setAttribute("attr", scope);	// application에 저장
		}
		
		// Map 객체를 생성하여 request 속성에 넣어준다.
		Map<String, String> map = new HashMap<>();
		map.put("brown", "브라운");
		map.put("sally", "샐리");
		
		request.setAttribute("rangers", map);
		
		// List 객체를 생성하여 request 속성에 넣어준다.
		List<MemberVO> memberlist = memberService.selectAllMember();
		request.setAttribute("rangersList", memberlist);
		
		// 요청하는 화면, 결과 출력 화면 동일
		request.getRequestDispatcher("/el/el.jsp").forward(request, response);
	}


}
