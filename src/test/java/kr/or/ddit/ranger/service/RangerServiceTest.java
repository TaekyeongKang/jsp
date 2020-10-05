package kr.or.ddit.ranger.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class RangerServiceTest {

	
	// 해당 메소드가 Test 대상임을 알려주는 어노테이션
	// 일반적으로 메소드 단위로 테스트를 진행
	// 하나의 메소드도 시나리오에 따라 여러개의 테스트 메소드를 생성할 수도 있다.
	// ex) 회원 등록  dao 메소드 생성시
	//		정상적인 값을 입력해서 db 테이블에 데이터가 정상적으로 입력되는 성공 케이스
	// 		비정상적인 값(회원 id 중복 등)을 입력하여 db테이블에 데이터가 입력되지 않는 실패 케이스 
	
	// 테스트 메소드는 테스트 하려고하는 메소드 이름 + 접미어Test
	// getRangers => getRangersTEst
	
	// 테스트 코드 통과 조건 : 두가지 조건 모두 통과하면
	// 	1. 테스트 메소드 실행 시 assert 구문을 모두 통과
	// 	2. 에러가 없으면 통과
	
	// main 메소드가 없음에도 실행이 가능한 것은
	// eclipse 에서 @Test 메소드가 붙은 메소드를 실행해주기 때문 (정확이는 Junit에서 실행)
	
	// maven 배포 시에도 @Test 어노테이션이 붙은 메소드가 통과되어야 빌드 사이클이 진행이 된다. 
	
	@Test
	public void getRangersTest() {
		/***Given : 주어진 상황 기술 ***/
		RangerServiceI rangerService = new RangerService();
		
		/***When : 행위 ***/
		List<String> rangers = rangerService.getRangers();

		/***Then***/
		assertNotNull(rangers);
		
		
		/*fail("Not yet implemented"); // 강제로 테스트를 실패시키는 코드*/
		/*
		if(rangers != null) {
			System.out.println("success");
		}
		if(rangers == null) {
			System.out.println("fail");
		}
		*/
		
		// 예상값, 결과값 같으면 성공, 다르면 실패
		//assertEquals(expected, actual);

		// 예상값& 결과값 다르면 성공, 같으면 실패 
		//assertNotEquals(unexpected, actual);
		
		
		// ranger가 널이 아니어야 통과
		//assertNotNull(rangers);
		
	}
	
	

}
