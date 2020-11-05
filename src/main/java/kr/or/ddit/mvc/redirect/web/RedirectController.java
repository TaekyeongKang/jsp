package kr.or.ddit.mvc.redirect.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/redirect")
@Controller
public class RedirectController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedirectController.class);
	
	// localhost/redirect/process => localhost/login/view 
	// 
	// 요청 시 /login/view 리다이렉트 처리
	@RequestMapping("/process")
	public String process(Model model, HttpSession session,
						  RedirectAttributes ra) {
		logger.debug("RedirectController.process()");
		
		// model에 속성을 추가했을 때 redirect된 화면에서 이 속성을 사용할 수 있는 가
		// 사용불가!! ∵ redirect는 요청이 끊긴 후 새롭게 요청을 하는것이므로
		// 요청이 살아있는 동안에만 살아있는 model 객체(request)에 저장된 속성이 유지될 수 없음
		model.addAttribute("msg", "hello, world");
		// => session에 저장하여 유지 
		// but redirect로 보여지는 최종 결과가 redirect방식이 아닌 본 요청으로 응답받아도 값이 살아있음
		// ==> 사용 후에는 삭제를 해주어야한다는 단점 있음 <c:remove var="msg_s" scope="session"> 으로 삭제
		// -> 새로고침하면 값이 사라져 1회용으로 사용
		session.setAttribute("msg_s", "hello, world_session");
		
		// ===> RedirectAttribute 속성에 저장하여 1회용으로만 사용하고 사라지게 함
		ra.addFlashAttribute("msg_ra", "hello, world_ra");
		
		// 리다이렉트시 RedirectAttributes 객체에 추가된 속성을 리다이렉트 주소의 파라미터로 추가한다. (get)
		//  "redirect:/login/view" ==> /login/view?msg_ra_attr=brown
		ra.addAttribute("msg_ra_attr", "brown");
		
		return "redirect:/login/view";
	}
	
	
}
