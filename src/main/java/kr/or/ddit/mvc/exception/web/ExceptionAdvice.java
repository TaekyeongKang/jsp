package kr.or.ddit.mvc.exception.web;

import java.io.FileNotFoundException;

import org.apache.ibatis.javassist.NotFoundException;
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
//	
//	@ExceptionHandler({FileNotFoundException.class, NotFoundException.class}) // 예외 설정할 수 있음
//	public String notFoundHandler() {
//		logger.debug("ExceptionAdvice.notFoundhandler.handler()");
//		
//		// 에러를 처리할 화면 viewName 반환
//		return "exception/404"; 
//	}
//	
//	@ExceptionHandler({InternalError.class}) // 예외 설정할 수 있음
//	public String internalHandler() {
//		logger.debug("ExceptionAdvice.internalHandler.handler()");
//		
//		// 에러를 처리할 화면 viewName 반환
//		return "exception/500"; 
//	}

}
   