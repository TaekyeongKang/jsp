package kr.or.ddit.config.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.repository.BoardRepositoryI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/ioc/scope.xml"})
public class IocScopeTest {
	
	private static final Logger logger = LoggerFactory.getLogger(IocScopeTest.class);

	@Autowired
	ApplicationContext context;
	
	@Resource(name="boardRepository")
	private BoardRepositoryI boardRepository;
	
	// 다른 이름으로 동일한 빈을 주입받음
	// 선언만 두개, 바라보고 있는 빈은 동일한 빈
	@Resource(name="boardRepository")
	private BoardRepositoryI boardRepository2;
	
	@Resource(name="boardRepository_a")
	private BoardRepositoryI boardRepository_a;
	
	
	// prototype 으로 scope 속성을 설정하여 singleton을 사용하지 않는 것 확인용
	@Resource(name="boardRepository_p")
	private BoardRepositoryI boardRepository_p;
	
	@Resource(name="boardRepository_p")
	private BoardRepositoryI boardRepository_p2;
	
	
	@Test
	public void singletonTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertEquals(boardRepository, boardRepository2);		// 통과됨 = 동일
		assertNotEquals(boardRepository, boardRepository_a);	// 통과됨 = 동일하지 않음
		assertNotEquals(boardRepository2, boardRepository_a);	// 통과됨 = 동일하지 않음
		
	}
	
	@Test
	public void prototypeTest() {
		/***Given***/

		/***When***/
		for(int i=0; i<10; i++) {	// singleton으로 설정되노 레파지토리 가져옴
			BoardRepositoryI boardRepository = context.getBean("boardRepository", BoardRepositoryI.class);
			
			BoardRepositoryI boardRepositoryp = context.getBean("boardRepository_p", BoardRepositoryI.class);
			
			logger.debug("singleton-boareRepository : {}, prototype-boardRepository : {}", boardRepository, boardRepositoryp); //  동일한 hash값 ==> 동일객체
			/*
				singleton-boareRepository : BoardRepository@dd8ba08, prototype-boardRepository : BoardRepository@38c6f217
				singleton-boareRepository : BoardRepository@dd8ba08, prototype-boardRepository : BoardRepository@478190fc
				singleton-boareRepository : BoardRepository@dd8ba08, prototype-boardRepository : BoardRepository@79e2c065
				singleton-boareRepository : BoardRepository@dd8ba08, prototype-boardRepository : BoardRepository@3a93b025
				singleton-boareRepository : BoardRepository@dd8ba08, prototype-boardRepository : BoardRepository@35cabb2a
				singleton-boareRepository : BoardRepository@dd8ba08, prototype-boardRepository : BoardRepository@7e07db1f
				singleton-boareRepository : BoardRepository@dd8ba08, prototype-boardRepository : BoardRepository@1189dd52
				singleton-boareRepository : BoardRepository@dd8ba08, prototype-boardRepository : BoardRepository@36bc55de
				singleton-boareRepository : BoardRepository@dd8ba08, prototype-boardRepository : BoardRepository@564fabc8
			*/
		}

		/***Then***/
		assertNotEquals(boardRepository_p, boardRepository_p2);	// 통과됨 = 동일하지 않음
		
	}


}
