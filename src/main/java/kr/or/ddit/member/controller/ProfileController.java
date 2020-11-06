package kr.or.ddit.member.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberServiceI;

/**
 * Servlet implementation class ProfileServlet
 */
@RequestMapping("/member")
@Controller
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "memberService")
	private MemberServiceI memberService;
	
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletResponse response, String userid) throws ServletException, IOException {
		// response content-type 을 따로 설정하지 않아도 크롬이 똑똑해서 출력해주지만 부하가 더 걸리는 일
		// response content-type 설정
		response.setContentType("image/png");
		
		// 사용자 아이디 파라미터 확인
		
		// db 사용자 filename 확인
		MemberVO memberVO= memberService.getMember(userid);
		
		// 경로 확인 후 파일 입출력을 통해 응답 생성
		// 파일 읽기
		// 응답 생성
		memberVO.getFilename(); // 파일경로
		FileInputStream fis = new FileInputStream(memberVO.getFilename());
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
