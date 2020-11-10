package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.model.PageVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@RequestMapping("/member")
@Controller
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberListController.class);
	
	@Resource(name = "memberService")
	private MemberServiceI memberService;
	
	@RequestMapping("/memberList")
	public String memberList(Model model,PageVO pageVO) throws ServletException, IOException {
		pageVO.setPage(pageVO.getPage() == 0 ? 1 : pageVO.getPage());
		model.addAttribute("page", pageVO.getPage());
		
		
		pageVO.setPageSize(pageVO.getPageSize() == 0 ? 5 : pageVO.getPageSize());
		model.addAttribute("pageSize", pageVO.getPageSize());
		
		
		
		
//		List<MemberVO> memberList = memberService.selectPageMember(page);
		Map<String, Object> map = memberService.selectMemberPageList(pageVO); 
		
		model.addAttribute("memberList", map.get("memberList"));
		model.addAttribute("pages", map.get("pages"));
		logger.debug("page : {} , pageSize : {}, pages : {}", pageVO.getPage(), pageVO.getPageSize(), map.get("pages"));
		//return "member/memberList";
		// tile 버전 viewName 리턴
		return "tiles/member/memberListContent";
	}
	
	@RequestMapping("/listAjaxPage")
	public String listAjaxPage() {
		return "tiles/member/listAjaxPage";
	}
	
	
	// 페이지 요청 (/list)와 다르게 page, pageSize 파라미터가 반드시 존재한다는 가정으로 작성
		@RequestMapping("/listAjaxHTML")
		public String listAjaxHTML(PageVO pageVO, Model model) {
			
			logger.debug("pageVO : {}" , pageVO);
			
			Map<String, Object> map = memberService.selectMemberPageList(pageVO); 
			
			model.addAttribute("memberList", map.get("memberList"));
			model.addAttribute("pages", map.get("pages"));
			// 응답을 html ==> jsp로 생성
			return "member/listAjaxHTML";
		}

	// 페이지 요청 (/list)와 다르게 page, pageSize 파라미터가 반드시 존재한다는 가정으로 작성
	@RequestMapping("/listAjax")
	public String listAjax(PageVO pageVO, Model model) {
		
		logger.debug("pageVO : {}" , pageVO);
		
		Map<String, Object> map = memberService.selectMemberPageList(pageVO); 
		
		model.addAttribute("memberList", map.get("memberList"));
		model.addAttribute("pages", map.get("pages"));
		
		return "jsonView";
	}

}
