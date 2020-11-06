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
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.fileupload.FileUploadUtil;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.JSRMemberVO;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.MemberVoValidataor;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@RequestMapping("/member")
@Controller
@MultipartConfig
public class MemberRegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberRegistController.class);
	@Resource(name = "memberService")
	private MemberServiceI memberService;
	
      
	@RequestMapping("/memberRegist")
	public String memberRegist(){
		
		return "member/memberRegist";
	}
	
	@RequestMapping("/registProcess")
	public String registProcess(@Valid MemberVO memberVO,BindingResult br,
								@RequestPart("profile") MultipartFile profile) {
								// 필드명과 동일하게 객체명을 지정할 경우 @RequestPart("profile") 을 생략해도 가능
//	public String registProcess(@Valid JSRMemberVO memberVO,BindingResult br,
//								@RequestPart("profile") MultipartFile profile) {
		
		//new MemberVoValidataor().validate(memberVO, br);
		
		
		// 검증을 통과하지 못했으므로 사용자 등록 화면으로 이동
		if(br.hasErrors()) {
			return "member/memberRegist";
		}
		
		logger.debug("사용자 등록  파라미터 memberVO : {}", memberVO);
		
		// 넘어온 파일 정보 확인 (part로 넘어옴)
		
		
		String realFilename = "";
		String fileName ="";
		String extension = FileUploadUtil.getExtension(realFilename);
		logger.debug("extension : {}",extension);
		if(profile.getSize() > 0) {
			realFilename = profile.getOriginalFilename();
			fileName= "D:\\upload\\" + UUID.randomUUID().toString() +"."+ extension;
			File uploadFile = new File("D:\\upload\\" + UUID.randomUUID().toString() +"."+ extension);
			try {
				profile.transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		// 사용자 정보 등록
//		MemberVO memberVO = new MemberVO(userid, pass, usernm, alias, addr1, addr2, zipcode, fileName, realFilename);
		memberVO.setFilename(fileName);
		memberVO.setRealFilename(realFilename);
		logger.debug("사용자 등록용 memberVO : {}", memberVO);
		
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
			return "redirect:/member/memberList";
		}
		else {
			// 사용자가 입력했던 정보를 담은 파라미터를 다시 가져가게끔 설정
			return "member/memberRegist";
		}
		
	}
	
}
