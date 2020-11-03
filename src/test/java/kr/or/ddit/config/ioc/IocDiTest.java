package kr.or.ddit.config.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.support.DelegatePerTargetObjectIntroductionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.person.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/ioc/ioc.xml"})
	// 인자 : String[] -> 사용 편의에 따라 설정파일을 여러개로 나누어놓는 경우가 맣음, 한개라 하더라도 배열형태로 넣어줌
public class IocDiTest {

	// ioc.xml을 바탕으로 스프링빈이 잘 생성되었는지 확인
	// setter-boardService, constructor-boardServiceC 주입 확인
	
	@Autowired  // application 객체는 하나뿐 -> 이름을 모르더라도 @Autowired 어노테이션을 통해 컨테이너 객체 DI 가능
	// @Autowired  : 스프링 빈 중에 호환되는 타입의 빈이 있을 때 주입
	ApplicationContext context;
	// junit코드가 spring runner 기반으로 돌아감 -> 동일한 타입이 있는지를 통해서 주입받을 수 있음. 
	
	//@Autowired
	@Resource(name="boardService")
	BoardService boardService;
	
	@Resource(name="boardServiceC")
	BoardService boardServiceC;
	
	@Resource(name="person")
	Person person;
	
	// person 스프링빈의 age(value), boareRepository(ref) 두 속성에 주입이 잘 되었는지 확인
	@Test
	public void valueRefTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertEquals(30, person.getAge());
		assertEquals("내용", person.getBoardRepository().getBoard(1).getContent());
//		assertEquals("2020-11-03", person.getBirthdate());
		/*
		Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'person' defined in class path resource [kr/or/ddit/config/spring/ioc/ioc.xml]: Initialization of bean failed; 
		nested exception is org.springframework.beans.ConversionNotSupportedException: 
		Failed to convert property value of type 'java.lang.String' to required type 'java.util.Date' for property 'birthdate'; 
		nested exception is java.lang.IllegalStateException: Cannot convert value of type 'java.lang.String' to required type 'java.util.Date' for property 'birthdate': no matching editors or conversion strategy found
		 */
	}
	
	
	
	// 스프링 컨테이너를 주입받아 DL을 통해 boardService 스프링 빈이 제대로 생성됐는지 확인
	@Test
	public void DItest() {
		/***Given***/
		
		/***When***/
		BoardServiceI boardService = context.getBean("boardService", BoardServiceI.class);
		BoardVO boardVO = boardService.getBoard(1);
		
		/***Then***/
		assertEquals("첫번째 글", boardVO.getTitle());
		
	}
	
	@Test
	public void DIAutowiredTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertNotNull(boardService);
	}
	
	
	// boardService, boardServiceC  스프링 빈에 주입한 boardRepository 스프링빈은 동일한 빈이므로
	// 두 객체의 getter 메소드를 통해 얻어온 boardRepository  객체는 동일해야 한다.
	@Test
	public void repositorySameTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertEquals(boardService.getBoardRepository(), boardServiceC.getBoardRepository());
	}

}
	