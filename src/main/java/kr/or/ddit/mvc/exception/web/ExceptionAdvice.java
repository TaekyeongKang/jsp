package kr.or.ddit.mvc.exception.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler({ArithmeticException.class}) // 예외 설정할 수 있음
	public String handler() {
		logger.debug("ExceptionAdvice.handler()");
		
		// 에러를 처리할 화면 viewName 반환
		return "exception/arithmetic"; 
	}

}
   