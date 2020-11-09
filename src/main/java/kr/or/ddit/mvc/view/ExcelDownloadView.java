package kr.or.ddit.mvc.view;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;


public class ExcelDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// model 객체에서 header, data 가져오기
		List<String> header =(List<String>) model.get("header");
		List<Map<String, String>> data =(List<Map<String, String>>) model.get("data");
		
		// excel 파일 contentType : application/vnd.ms-excel; UTF-8
		response.setContentType("application/vnd.ms-excel; UTF-8");
		
		// 첨부파일임을 암시
		response.setHeader("Content-Disposition", "attachment; filename=test.xlsx");
		
		// poi 라이브러리를 이용하여 엑셀 파일을 생성
		Workbook workbook = new XSSFWorkbook();// poi 라이브러리에서는  엑셀파일을 workbook이라고 함
		// sheet 만들어주기
		Sheet sheet = workbook.createSheet("lineFriends");
		// 행 만들기
		int rowNum = 0;
		Row row = sheet.createRow(rowNum++);
		
		int column = 0;
		// header 설정
		for(String h : header) {
			// cell 만들기
			row.createCell(column++).setCellValue(h);
		}
		
		// data 설정
		for(Map<String, String> map : data) {
			// 행 생성
			row = sheet.createRow(rowNum++);
			
			// 셀 채우기
			column = 0; // 매 행마다 셀 인덱스값은 초기화 되어야 함
			row.createCell(column++).setCellValue(map.get("userid"));
			row.createCell(column++).setCellValue(map.get("usernm"));
		}
		OutputStream os = response.getOutputStream();
		workbook.write(os);
		
		os.flush();
		os.close();
		
		workbook.close();
			
	}

}
