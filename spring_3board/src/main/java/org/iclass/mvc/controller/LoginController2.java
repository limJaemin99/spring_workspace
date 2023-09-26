package org.iclass.mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.iclass.mvc.dto.BookUser;
import org.iclass.mvc.service.BookUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
// [방법 2] HttpSession 사용 ▶ [방법 1] 사용하는동안 주석하기
//@Controller
@RequiredArgsConstructor
public class LoginController2 {
	private final BookUserService service;

	
	@GetMapping("/login")    
	public void login() {	//login.jsp View 로 요청 전달
		
	}
	
	
	@PostMapping("/login")  //POST 요청일때 @PostMapping. param 에 저장되는 것 = id/pw
	public String loginProc(@RequestParam Map<String,String> param, RedirectAttributes redirectAttributes,
								Model model, HttpSession session) {
		String url="/";		//계정정보가 일치할때 context path(index)로 이동
		
		BookUser member = service.login(param);
		
		if(member == null) {	
			redirectAttributes.addFlashAttribute("incorrect","y");
			url="login";   //계정 정보가 틀릴때 다시 로그인으로 이동
		} else {
			session.setAttribute("user", member);
		}
		
		return "redirect:"+url;		
	}

	
	@GetMapping("logout")
	public String logout(HttpSession session) {	//SessionStatus : @SessionAttribute 로 저장된 model 을 관리하는 객체
		session.invalidate();	//session 무효화 (브라우저 쿠키 JsessionID 가 삭제됨)
		
		return "redirect:/";
	}
	
}
