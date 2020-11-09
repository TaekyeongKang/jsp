package kr.or.ddit.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.fileupload.FileUploadUtil;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@RequestMapping("/member")
@Controller
@MultipartConfig
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberUpdateController.class);
	
	@Resource(name = "memberService")
	MemberServiceI memberService;
	
	MemberVO memberVO;
	
	@RequestMapping("/memberUpdate")
	public String memberUpdate(String userid, Model model) {
		
		
		logger.debug("userid : {}",userid);
		
		
		memberVO = memberService.getMember(userid);
		
		model.addAttribute("memberVO", memberVO);
		
		return "member/memberUpdate";
		
	}

	@RequestMapping("/updateProcess")
	public String updateProcess(MemberVO updateMemberVO, @RequestPart("profile") MultipartFile profile) {
		
		logger.debug("parameter updateMemberVO : {}", updateMemberVO);
		logger.debug("name : {} / fileName : {} /  fileSize : {}" ,profile.getName(), profile.getOriginalFilename(), profile.getSize());  // cf) getName() : 파라미터 이름
				
		
		
		// 넘어온 파일 정보 확인 (part로 넘어옴)
		
		String fileName = "";
		String realFilename = profile.getOriginalFilename();
		if(realFilename == null || realFilename.equals("")) {
			fileName = memberVO.getFilename();
			realFilename = memberVO.getRealFilename();
		}
		if(profile.getSize() > 0) {
			String extension = FileUploadUtil.getExtension(realFilename);
			logger.debug("extension : {}",extension);
			fileName = "D:\\profile\\" + UUID.randomUUID().toString() +"."+ extension;
			File uploadFile = new File(fileName);
			try {
				profile.transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		// 사용자 정보 등록
		updateMemberVO.setFilename(fileName);
		updateMemberVO.setRealFilename(realFilename);
		
		
		logger.debug("updateMemberVO : {}", updateMemberVO);
		int updateCnt = memberService.updateMember(updateMemberVO);
		
		logger.debug("updateCnt : {}", updateCnt);
		
		if(updateCnt == 1) {
			return "redirect:member?userid="+updateMemberVO.getUserid();
		}
		else {
			return "member/memberUpdate";
		}
	}

}
