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

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.fileUpload.FileUploadUtil;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/memberUpdate")
@MultipartConfig
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberUpdateServlet.class);
	
	MemberVO memberVO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userid = request.getParameter("userid");
		
		logger.debug("userid : {}",userid);
		
		MemberServiceI memberService = new MemberService();
		
		memberVO = memberService.getMember(userid);
		
		request.setAttribute("memberVO", memberVO);
		
		request.getRequestDispatcher("/member/memberUpdate.jsp").forward(request, response);
		
	}

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
		
		logger.debug("file : {}", profile.getHeader("Content-Disposition"));
		
		MemberServiceI memberService = new MemberService();
		

		String filePath = "";
		String fileName = "";
		String realFilename = FileUploadUtil.getFilename(profile.getHeader("Content-Disposition"));
		if(realFilename == null || realFilename.equals("")) {
			fileName = memberVO.getFilename();
			realFilename = memberVO.getRealFilename();
		}
		if(profile.getSize() > 0) {
			String extension = FileUploadUtil.getExtension(realFilename);
			logger.debug("extension : {}",extension);
			fileName  = "D:\\profile\\" + UUID.randomUUID().toString() +"."+ extension;
			filePath = fileName;
			profile.write(filePath);
		}

		// 사용자 정보 등록
		//MemberVO updateMemberVO = new MemberVO(userid, pass, usernm, alias, addr1, addr2, zipcode, fileName, realFilename);
		memberVO.setUserid(userid);
		memberVO.setUsernm(usernm);
		memberVO.setPass(pass);
		memberVO.setAlias(alias);
		memberVO.setAddr1(addr1);
		memberVO.setAddr2(addr2);
		memberVO.setZipcode(zipcode);
		memberVO.setFilename(fileName);
		memberVO.setRealFilename(realFilename);
		
		logger.debug("updateMemberVO : {}", memberVO);
		int updateCnt = memberService.updateMember(memberVO);
		
		logger.debug("updateCnt : {}", updateCnt);
		
		if(updateCnt == 1) {
			response.sendRedirect(request.getContextPath()+"/member?userid="+userid);
		}
		else {
		}
	}

}
