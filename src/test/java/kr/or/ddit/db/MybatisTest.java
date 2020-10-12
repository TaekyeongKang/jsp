package kr.or.ddit.db;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MybatisTest {

	@Test
	public void getSqlSessionTest() {
		/***Given***/
		// static 메소드를 테스트 하기 때문에 인스턴스 생성 안해도 됨

		/***When***/
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		/***Then***/
		assertNotNull(sqlSession);
	}

}
