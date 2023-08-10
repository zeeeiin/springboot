package com.coding404.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	//홈
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	//회원가입
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	//커스터마이징 로그인페이지
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	
	@GetMapping("/all")
	public String all() {
		return "all";
	}

	@GetMapping("/admin/mypage")
	public @ResponseBody String mypage() {
		return "REST API admin mypage";
	}
	
	@GetMapping("/user/mypage")
	public @ResponseBody String usermypage() {
		return "REST API user mypage";
	}
	
	
}
