package kr.or.ddit.mvc.exception.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@RequestMapping("/exception/view") // 클래스에는 requestmapping 안하고 메소드에만 적용 
	public String view() {
		logger.debug("ExceptionController.view()");
		throw new ArithmeticException(); // 강제 예외 발생
		//return "";  // return 주석 처리 시  : 리턴이 있는 메소드 임에도 불구하고 컴파일하는데 문제가 없음
	}
	
	@RequestMapping("/exception/resㅔSt")
	public String responseStatus() throws NoFileException{
		try { 
			// 파일을 찾는 로직이 있음.
			// 찾고자 하는 파일이 없어서 예외 발생
			throw new Exception(); // 강제 예외 발생
		} catch (Exception e) {
			
			// Exception 대신 우리가 만든 NoFileException 으로 처리
			// NoFileException 은 @ResponseStatus 설정에 의해
			// 404 코드로 사용자에게 응답 처리함
			throw new NoFileException();
		}
//		return "";
	}
	
	
//	@ExceptionHandler({ArithmeticException.class}) // 예외 설정할 수 있음
//	public String handler() {
//		logger.debug("ExceptionController.handler()");
//		
//		
//		// 에러를 처리할 화면 viewName 반환
//		return "exception/arithmetic"; 
//	}
}
