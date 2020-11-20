package kr.or.ddit.batch.yogurt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import kr.or.ddit.batch.yogurt.model.CycleVO;
import kr.or.ddit.batch.yogurt.model.DailyVO;

public class YogurtProcessor implements ItemProcessor<CycleVO, List<DailyVO>>{
													//  1    :     N
	
	private static final Logger logger = LoggerFactory.getLogger(YogurtProcessor.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	// jobLauncher를 실행하면서 두번째 인자로 넣어준 jobParameter 값을 SPEl을 통해 주입
	// 단, jobParameters 에 접근하기 위해서는 해당 스프링 빈의 scope를 step으로 지정해야 한다
	// 작업실행할때 파라미터로 해당 년월에 대한 값을 넘겨줌 -> 받아올 객체
	@Value("#{jobParameters[ym]}") // spring에서도 el을 사용할 수 있음 , spel으로 칭함
	private String ym;
	
	@Override
	public List<DailyVO> process(CycleVO item) throws Exception {
		// 생성 월이 202011월
		// CycleVO : cid=1, pid=100, day=2, cnt=3
		// ===>
		// DailyVO 5건
		// cid=1, pid=100, dt=20201102, cnt=3
		// cid=1, pid=100, dt=20201109, cnt=3
		// cid=1, pid=100, dt=20201116, cnt=3
		// cid=1, pid=100, dt=20201123, cnt=3
		// cid=1, pid=100, dt=20201130, cnt=3
		
		logger.debug("ym : {} , item : {}", ym, item);
		List<DailyVO> dailyVOList = new ArrayList<DailyVO>();
		
		Calendar calendar = Calendar.getInstance(); //싱글턴 패턴이 적용된 클래스 -> new 연산자 사용 x
		calendar.set(Calendar.YEAR, Integer.parseInt(ym.substring(0, 4)));  // 202011 -> 0123번째 자리 추출
													//calendar 클래스는 0월~11월까지 있음 (하나씩 앞당겨야함) -> 11 을 넣으면 실제 의미하는 달은 12월
		calendar.set(Calendar.MONTH, Integer.parseInt(ym.substring(4))-1);  // 202011 -> 4번째 자리부터 추출
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // 현재 calendar 객체에 있는 해당월의 마지막날짜 설정
		
		// calendar 2020 11 30 으로 설정됨
		Date endTime = calendar.getTime();
		String endTimeStr = sdf.format(endTime);
		int endTimeInt = Integer.parseInt(endTimeStr);
		
		// 해당 월의 1일로 설정
		calendar.set(Calendar.DAY_OF_MONTH, 1); // calendar 2020 11 01
		Date dt = calendar.getTime();
		String dtStr = sdf.format(dt);
		int dtInt = Integer.parseInt(dtStr);
		
		while(endTimeInt >= dtInt) { // 2020년 11월 30일보다 전이라면 실행
			
			// calendar 날짜가 item의 애음요일과 같을 때만 dailyVO 를 생성
			if(item.getDay() == calendar.get(Calendar.DAY_OF_WEEK)) { // Calendar.DAY_OF_WEEK : 주간요일
				DailyVO dailyVO = new DailyVO();
				dailyVO.setCid(item.getCid());
				dailyVO.setPid(item.getPid());
				dailyVO.setDt(sdf.format(calendar.getTime()));
				dailyVO.setCnt(item.getCnt());
				
				dailyVOList.add(dailyVO);
			}
			
			// 다음 날짜로 설정
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
			dt = calendar.getTime();
			dtStr = sdf.format(dt);
			dtInt = Integer.parseInt(dtStr);
		}
		
		
		return dailyVOList;
	}

}
