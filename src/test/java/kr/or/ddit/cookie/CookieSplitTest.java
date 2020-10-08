package kr.or.ddit.cookie;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class CookieSplitTest {
	@Test
	public void getCookieValueSuccessTest() {
		/***Given***/
		CookieSplit cookieSplit = new CookieSplit();
		
		/***When***/
		String USERNMValue =  cookieSplit.getCookieValue("USERNM");// 기대되는 값 brown
		String TESTValue = cookieSplit.getCookieValue("TEST");// 기대되는 값 T
		String XXXXValue = cookieSplit.getCookieValue("XXXX");// 기대되는 값 ""(White Space)
		
		/***Then***/
		assertEquals("brown", USERNMValue);
		assertEquals("T", TESTValue);
		assertEquals("", XXXXValue);
		
		
	}
	
	@Test
	public void getCookieValueFailTest() {
		/***Given***/
		CookieSplit cookieSplit = new CookieSplit();

		/***When***/

		/***Then***/
	}

}
