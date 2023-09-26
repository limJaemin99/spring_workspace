package org.iclass.mvc.controller;

import java.time.LocalDate;

import org.iclass.mvc.dto.Community;
import org.iclass.mvc.dto.CommunityComments;
import org.iclass.mvc.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/community")
@Slf4j
public class CommunityController {
	
	private final CommunityService service;
	
	private CommunityController(CommunityService service) {
		this.service = service;
	}
	
	
	@GetMapping("/list")	//▼ 파라미터 값이 없을 경우 오류 발생 (따라서 기본값을 1로 설정함) ▶ @RequestParam(defaultValue="")
	public void list(@RequestParam(defaultValue = "1") int page , Model model) {	//메소드 인자 int page 는 URL 의 파라미터
		//Model : View 로 전달될 데이터를 주로 저장한다.
		//addAttribute() 메소드 : request.setAttribute() 와 동일하다. (session 도 Model 사용 가능 - 로그인에서 할 예정)
		model.addAttribute("list",service.pagelist(page).get("list"));
		model.addAttribute("paging",service.pagelist(page).get("paging"));
		model.addAttribute("today",LocalDate.now());
	}
	
	
	//URL : community/read?idx=779&page=1
	@GetMapping("/read")		//▼ 파라미터로 받은 값을 Model 객체(model.addAttribute) 와 같이 저장한다.
	public void read(long idx , @ModelAttribute("page") int page , Model model) {
		model.addAttribute("vo",service.read(idx));
		model.addAttribute("cmtlist",service.commentsList(idx));
	}
	
	
	@GetMapping("/write")
	public void write() {
		//글쓰기 GET 요청은 View name 만 지정하고 끝
		
	}
	
	
	@PostMapping("/write")
	public String save(Community dto , RedirectAttributes redirectAttributes) {	//파라미터가 많을 때, 그것들을 필드로 갖는 dto 또는 map 타입으로 전달받기
		log.info("━━━━━▶ dto : {}",dto);
		service.insert(dto);
		
		//▼ addFlashAttribute() 를 사용하면 1회용이므로 URL에 보이지 않는다. (스프링MVC프로젝트.txt 하단에 설명)
		redirectAttributes.addFlashAttribute("message","글 등록이 완료되었습니다.");
		
		return "redirect:/community/list";	//★★★★★★ 중요 ★★★★★★
	}										//▼ 설명은 하단에 있음 ▼
	
	//location.href='list.jsp' ▶ 자바스크립트 - 클라이언트 브라우저에서 주소 변경
	//response.sendRedirect("list.jsp") ▶ 서버에서 클라이언트가 새로 보낼 요청을 강제로 실행
		//	ㄴ POST 요청을 처리한 후에는 redirect 를 한다.
	
	
	@GetMapping("/update")
	public void updateView(long idx , @ModelAttribute("page") int page , Model model) {
		//int page 는 @ModelAttribute 로 model.addAttribute("page",page); 를 대신해서 update.jsp 로 전달한다.
		model.addAttribute("vo",service.selectByIdx(idx));
	}
	
	
	@PostMapping("/update")
	public String updateAction(Community vo,
			/* @ModelAttribute("page") */ int page, RedirectAttributes redirectAttributes/* Model model */) {
		service.update(vo);
		
//		return "redirect:/community/read";
		//수정 후 다시 글 상세보기
		redirectAttributes.addAttribute("page",page);	//redirectAttributes.addAttribute() 를 사용하면
		redirectAttributes.addAttribute("idx",vo.getIdx());	//ㄴ @ModelAttribute() 를 사용할 수 없다.
//		model.addAttribute("page",page);	//redirectAttributes 대신 model 사용 가능
//		model.addAttribute("idx",vo.getIdx());	//▲ 이 내용은 스프링MVC프로젝트.txt 하단에 있음
		
		//▼ addFlashAttribute() 를 사용하면 1회용이므로 URL에 보이지 않는다. (스프링MVC프로젝트.txt 하단에 설명)
		redirectAttributes.addFlashAttribute("message","글 수정이 완료되었습니다.");
		
		return "redirect:/community/read";
	}
	
	
	@PostMapping("/delete")  //▼ redirectAttributes 와 충돌이 일어나기 때문에 page 를 넘겨줄 수 없다. ▶ 따라서 redirectAttributes 로 page 를 넘겨야한다.
	public String delete(/* @ModelAttribute("page") */int page , long idx , RedirectAttributes redirectAttributes) {
		service.delete(idx);
		
		//@ModelAttribute 와 RedirectAttributes 충돌하므로 직접 코딩함
		redirectAttributes.addAttribute("page",page);
		//▼ addFlashAttribute() 를 사용하면 1회용이므로 URL에 보이지 않는다. (스프링MVC프로젝트.txt 하단에 설명)
		redirectAttributes.addFlashAttribute("message","글 삭제가 완료되었습니다.");
		
		return "redirect:/community/list";
	}
	
	
	@PostMapping("/comments")	//댓글
	public String comments(int page , int f , CommunityComments dto , RedirectAttributes redirectAttributes) {
		String message = null;
		
		log.info("━━━━━▶ dto : {}",dto);
		service.comments(dto,f);
		redirectAttributes.addAttribute("page",page);
		redirectAttributes.addAttribute("idx",dto.getMref());
		
		if(f == 1)
			message = "댓글이 등록되었습니다.";
		else if(f == 2)
			message = "댓글 삭제가 완료되었습니다.";

		redirectAttributes.addFlashAttribute("message",message);
		
//		return "redirect:/community/read?page="+page+"&idx="+dto.getMref();
		return "redirect:/community/read";	//RedirectAttributes 사용하므로 쿼리스트링을 사용하지 않는다.
	}
	
}
