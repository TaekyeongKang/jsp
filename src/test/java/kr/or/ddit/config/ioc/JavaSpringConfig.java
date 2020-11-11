package kr.or.ddit.config.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import kr.or.ddit.board.repository.BoardRepository;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;

@Configuration

public class JavaSpringConfig {
	
	// boardRepository, boardService
	// 메소드 이름 ==> 스프링 빈 이름
	
	// xml : <bean id="boardRepository(메소드이름)" class="BoardRepository" />
	@Bean
	public BoardRepositoryI boardRepository() {
		return new BoardRepository();
	}
	
	// xml : <bean id="boardService(메소드이름)" class="BoardService" /> 
	@Bean
	public BoardService boardService() {
		BoardService boardService = new BoardService();
		// boardService 에 boardRepository 세팅하는 방법
		// 방법 1 ) new 연산자로 만들어주기 
		//boardService.setBoardRepository(new BoardRepository());
										// == 스프링 빈이 아님!! ∵ @Bean 어노테이션이 붙은 메소드를 호출해야 스프링 컨테이너에서 관리되는 
										//						스프링 빈을 얻을 수 있다. 
		// 방법 2) 위에 생성한 boardRepository() 메소드 호출
		boardService.setBoardRepository(boardRepository());
										// 위에 생성한 boardRepository() 메소드 호출하여 반환된 boardRepository 객체로 세팅
		return boardService;
	}
}
