package kr.or.ddit.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// @WebServlet 혹은 web.xml url-mapping을 통해 url 등록
@RequestMapping("/login")
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	// 클래스에 등록된 urlmapping 이 먼저 나오고
	// 그다음 메소드에 등록된 urlmapping이 나온다
	// ex) localhost/login/view
	//     요청시 처리되는 메소드
	@RequestMapping("/view.do")
	public String getView() { // 요청을 받아 viewName을 리턴
		logger.debug("LoginController.getView()");
		return "login/view";
	}
}
