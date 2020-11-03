package kr.or.ddit.config.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.board.service.BoardServiceI;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JavaSpringConfig.class}) 
public class JavaSpringConfigTest {
	@Resource(name = "boardRepository")
	private BoardRepositoryI boareRepository;
	
	@Resource(name = "boardService")
	private BoardServiceI boardService;
	
	// 
	@Test
	public void beanTest() {
		/***Given***/

		/***When***/
		BoardVO boardVO = boardService.getBoard(1);
		
		/***Then***/
		assertNotNull(boareRepository);
		assertNotNull(boardService);
		assertEquals("내용", boardVO.getContent());
	}

}
