package kr.or.ddit.mvc.fileupload.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequestMapping("/fileupload")
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	// localhost/fileupload/view 요청시 화면 요청 처리 메소드
	// jsp로 응답 생성
	// jsp 에서는 파일을 선택할 수 있는 input 태그 1개
	// userid 파라미터를 보낼 수 있는 input 태그 1개
	// 전송을 담당하는 submit input태그 1개를 작성
	// 화면 요청에 처리 메소드
	// jsp : /WEB-INF/views/fileupload/fileupload.jsp
	// 테스트 코드 까지 작성
	@RequestMapping("/view")
	public String getFileuploadView() {
		return "fileupload/fileupload";
	}

	
	
	// 파일 업로드 처리 메소드
	@RequestMapping("/upload")
	public String multipart(String userid,
							@RequestPart("file") MultipartFile file
//							MultipartHttpServletRequest request, 
//		                    HttpServletRequest hsr, 
//		                    Model model
		                    ) {
		
		logger.debug("userid : {}" , userid);
		logger.debug("name : {} / fileName : {} /  fileSize : {}" ,file.getName(), file.getOriginalFilename(), file.getSize());  // cf) getName() : 파라미터 이름
				
		File uploadFile = new File("d:\\upload\\" + file.getOriginalFilename());
		
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
						
		//multipartHttpServletRequest
//		MultipartFile file = request.getFile("file");
//		logger.debug(file.getName());
//			
//		model.addAttribute("fileName", file.getName());
//		model.addAttribute("fileSize", file.getSize());
//	
//		//multipartFile
//		model.addAttribute("multipartFileName", multipartFile.getName());
//		model.addAttribute("multipartFileSize", multipartFile.getSize());
//	
//		//일반 파라미터
//		model.addAttribute("userid", request.getParameter("userid"));
//		model.addAttribute("hsrUserid", hsr.getParameter("userid"));
//			
		return "fileupload/fileupload";
	}
	
}
