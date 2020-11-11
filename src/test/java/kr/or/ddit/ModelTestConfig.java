package kr.or.ddit;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//테스트 대상 : repository + service : roo-context.xml
//repaository 에서 주입받는 sqlSessionTemplate : datasource-context.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/root-context.xml",
								   "classpath:kr/or/ddit/config/spring/datasource-context.xml",
								   "classpath:kr/or/ddit/config/spring/transaction-context.xml"})
public class ModelTestConfig {

	@Ignore
	@Test
	public void dummy() {
	}

}
