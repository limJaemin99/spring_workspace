package org.iclass.mvc.controller;

import org.iclass.mvc.dto.BookUser;
import org.iclass.mvc.service.BookUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {	//회원가입을 구현해보기
	
	private final BookUserService service;
	
	@RequestMapping("/test")
	public void test(@SessionAttribute("user") BookUser user) {	//로그인 방법1 로 되돌려놓고 테스트합니다.
		log.info("user test : {}",user);
	}
	
	
	//회원가입 완료 후 이동할 welcome 파일
	@GetMapping("/welcome")
	public void index() {
	
	}
	
	
	@GetMapping("/join")
	public void joinView() {
		
	}
	
	
	@PostMapping("/join")
	public String joinAction(BookUser dto , RedirectAttributes redirectAttributes) {
		int result = service.join(dto);
		String message = null;
		
		if(result == 1)
			message = "회원가입이 완료되었습니다.";
		else
			message = "회원 가입 실패";
		
		redirectAttributes.addFlashAttribute("message",message);
		
		return "redirect:/member/welcome";
	}
}
