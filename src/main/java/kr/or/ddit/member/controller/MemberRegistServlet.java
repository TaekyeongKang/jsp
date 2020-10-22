package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.fileUpload.FileUploadUtil;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@WebServlet("/memberRegist")
@MultipartConfig
public class MemberRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberRegistServlet.class);
	
	private MemberServiceI memberService;
	
	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/member/memberRegist.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String userid = request.getParameter("userid");
		String usernm = request.getParameter("usernm");
		String alias = request.getParameter("alias");
		String pass = request.getParameter("pass");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String zipcode = request.getParameter("zipcode");
		logger.debug("parameter : {}, {}, {}, {}, {}, {}, {}", userid, usernm, alias, pass, addr1, addr2, zipcode);
		
		// 넘어온 파일 정보 확인 (part로 넘어옴)
		Part profile = request.getPart("realFilename");
		logger.debug("file : {}",profile.getHeader("Content-Disposition"));
		
		String realFilename = FileUploadUtil.getFilename(profile.getHeader("Content-Disposition"));
		String extension = FileUploadUtil.getExtension(realFilename);
		logger.debug("extension : {}",extension);
		String fileName = "D:\\profile\\" + UUID.randomUUID().toString() +"."+ extension;
		String filePath = "";
		if(profile.getSize() > 0) {
			filePath = fileName;
			profile.write(filePath);
		}
		
		// 사용자 정보 등록
		MemberVO memberVO = new MemberVO(userid, pass, usernm, alias, addr1, addr2, zipcode, fileName, realFilename);
		
		int insertCnt = memberService.insertMember(memberVO);
		logger.debug("insertCnt : {}", insertCnt);
		
		// 1건이 입력되었을 때 : 정상 -> memberList 페이지로 이동
		// 1건이 아닐 때 : 비정상 -> 사용자가 데이터를 다시 입력할 수 있도록 등록페이지로 이동
		if(insertCnt == 1) {
			//request.getRequestDispatcher("/memberList").forward(request, response);
			// forward 방식으로 memberList 페이지 호출 : memberListServlet 의 doPost()메서드 실행하겠다.
			// memberListServlet 의 doPost()메서드 에서 doGet() 메서드 호출
			// but 새로고침하면 오류 
			// redirect  방식
			response.sendRedirect(request.getContextPath()+"/memberList");
		}
		else {
			// 사용자가 입력했던 정보를 담은 파라미터를 다시 가져가게끔 설정
			
			doGet(request,response);
		}
		
	}
	

}
