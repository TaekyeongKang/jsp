package kr.or.ddit.mvc.view;

import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

public class ProfileImgView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		FileInputStream fis = new FileInputStream((String)model.get("filepath"));
		// 파일 이름(경로)로부터 파일을 읽어들일 객체
		ServletOutputStream sos = response.getOutputStream();
		
		// 바이트 배열로 읽어들인 파일을 읽어서 넣어주는 작업
		byte[] buffer = new byte[512];
		
		// 바이트배열에 담긴 파일 정보를 쓰기작업
		while(fis.read(buffer) != -1) {
			sos.write(buffer);
		}
		
		fis.close();
		sos.flush();
		sos.close();
		
	}


}
