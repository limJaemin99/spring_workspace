package org.iclass.mvc.controller;

import org.iclass.mvc.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")	//url 이 community 로 시작하면 DicpatcherServlet 으로부터 
								//	CommunityController 가 위임 받아 처리한다.
public class SampleController {
	
	private final CommunityService service;
	
	private SampleController(CommunityService service) {
		this.service = service;
	}
	
	//━━━━━━━━━━━━━━━━━━━━ 여기부터 요청을 처리하는 핸들러 메소드 ━━━━━━━━━━━━━━━━━━━━//
	//URL 설계 : 글 목록 조회는 /community2/list , 글 읽기 /community2/read , 글 쓰기 /community2/write
	
	@GetMapping("/list")
	public void list() {
		
	}
	
	@GetMapping("/read")
	public void read() {
		
	}
	
	@GetMapping("/write")
	public void write() {
		
	}
	
	
}
