package kr.or.ddit.mvc.ajax.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.member.model.MemberVO;

@Controller
public class AjaxController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@RequestMapping("/ajax/view")
	public String view() {
		return "ajax/view"; // jsp
	}
	
	// consumes, produces
	// consumes - 사용자가 보내는 데이터 타입을 제한
	// produces - 사용자가 응답 받기 희망(Accept header, jquery - dataType) 하는 mimeType 을 제한
	@RequestMapping(path="/ajax/json" , 
					consumes = {"application/json"}, 
  				 // consumes = {"application/xml"} :  xml만 처리할 수 있다고 정해놓은 것
					produces = {"application/xml", "application/json"} )
				 // produces = {"application/xml", "application/json"} :  xml과 json으로 처리할 수 있다고 정해놓은 것
				 // produces = {"application/xml", "!application/json"} :  xml으로만 처리하고 json으로 처리할 수 없다고 정해놓은 것
	@ResponseBody
	public MemberVO json(@RequestBody MemberVO memberVO) {
		logger.debug("body : {}" , memberVO);
		memberVO.setAlias("곰");
		return memberVO;
	}
	
	// viewName을 리턴하지 않을 경우 path경로와 일치하는 jsp를 찾아 응답, 없을경우 404
	
}
