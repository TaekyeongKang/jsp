package kr.or.ddit.batch.basic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class BasicReader implements ItemReader<String>{

	private static final Logger logger = LoggerFactory.getLogger(BasicReader.class);
	
	private List<String> list;
	private int index = 0; // primitive type 을 필드에 선언할 경우 초기화 해주지 않아도 기본값으로 자동 초기화가 됨 
	
	public BasicReader() { // 생성자
		// 이 객체가 생성이 될 때 list 를 초기화
	 	list = new ArrayList<String>();
		
		// list 객체에 5개의 임의 값을 생성
		list.add("brown");
		list.add("sally");
		list.add("cony");
		list.add("moon");
		list.add("james");
	}
	
	// return 값이 Processor에게 전달 : 단순히 읽어서 전달하는 역할
	// 더이상 읽을 데이터가 없을 때 null을 리턴 ==> spring batch 모듈에서 읽을 데이터가 없다고 인식
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		logger.debug("=========read()===========");
		
		if(index < list.size()) {
			String item = list.get(index++);
			logger.debug("item : {}", item);
			return item;
		}
		else {
			index = 0;
			return null;
		}
	}
	
}
