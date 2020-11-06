package kr.or.ddit.mvc.exception.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 사용자에게 500 에서 대신 404 응답 코드로 응답이 가게끔 설정
@ResponseStatus(HttpStatus.NOT_FOUND) // : 404응답을 보내겠다
public class NoFileException extends Exception{ // 사용자 정의 예외클래스 생성 : 예외 상속받기

}
