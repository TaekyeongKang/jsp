package kr.or.ddit.member.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MemberVoValidataor implements Validator{

	
	// 검증하려고 하는 객체가 MemberVoValidator에서 검증이 가능한 객체인지
	// boolean으로 리턴하는 메소드
	@Override
	public boolean supports(Class<?> clazz) {	
		return MemberVO.class.isAssignableFrom(clazz);
						   // isAssignableForm() :  지금 인자로 들어오는 클래스로부터 할당이 가능한지 
	}

	
	// 검증 로직을 작성
	// Object target : command 객체 (검증로직을 태울 command 객체, validate() 바로 앞에 선언된)
	@Override
	public void validate(Object target, Errors errors) {
		MemberVO memberVO = (MemberVO)target;
		
		// usernm 값이 null 이거나 empty 문자열이면 안됨
		if(memberVO.getUsernm() == null || memberVO.getUsernm().equals("")) {
			errors.rejectValue("usernm", "required");
		}
	}

}
