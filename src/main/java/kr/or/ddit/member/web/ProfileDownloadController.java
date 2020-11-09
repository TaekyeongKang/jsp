package kr.or.ddit.member.web;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

/**
 * Servlet implementation class ProfileServlet
 */
@RequestMapping("/member")
@Controller
public class ProfileDownloadController{
	
	@Resource(name = "memberService")
	private MemberServiceI memberService;
	
	@RequestMapping("/downloadView")
	public String downloadView(String userid, Model model) {
		
		MemberVO memberVO = memberService.getMember(userid);
		model.addAttribute("filepath", memberVO.getFilename());
		model.addAttribute("filename", memberVO.getRealFilename());
		
		return "downloadView";
	}
	
//	@RequestMapping("/profileDownload")
//	protected void profileDownload(String userid, HttpServletResponse response) throws ServletException, IOException {
//		
//		
//		// db 사용자 filename 확인
//		MemberVO memberVO= memberService.getMember(userid);
//
//		// response content-type 을 따로 설정하지 않아도 크롬이 똑똑해서 출력해주지만 부하가 더 걸리는 일
//		// response content-type 설정
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + memberVO.getRealFilename() + "\"");
//		response.setContentType("application/octet-stream");
//		
//		// 경로 확인 후 파일 입출력을 통해 응답 생성
//		// 파일 읽기
//		// 응답 생성
//		
//		FileInputStream fis = new FileInputStream(memberVO.getFilename());
//		// 파일 이름(경로)로부터 파일을 읽어들일 객체
//		ServletOutputStream sos = response.getOutputStream();
//		
//		// 바이트 배열로 읽어들인 파일을 읽어서 넣어주는 작업
//		byte[] buffer = new byte[512];
//		
//		// 바이트배열에 담긴 파일 정보를 쓰기작업
//		while(fis.read(buffer) != -1) {
//			sos.write(buffer);
//		}
//		
//		fis.close();
//		sos.flush();
//		sos.close();
//		
//		
//	}


}
