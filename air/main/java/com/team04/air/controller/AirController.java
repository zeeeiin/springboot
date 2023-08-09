package com.team04.air.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team04.air.command.AirVO;
import com.team04.air.service.AirService;
import com.team04.air.util.Criteria;
import com.team04.air.util.PageVO;

@Controller
@RequestMapping("/air")
public class AirController {

	@Autowired
	@Qualifier("airService")
	private AirService airService;
	
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	
	@GetMapping("/search")
	public String list(Model model, Criteria cri){
			
		ArrayList<AirVO> list = airService.getList(cri);
		
		int total = airService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
			
		model.addAttribute("list",list);
		model.addAttribute("pageVO",pageVO);
			
		System.out.println(pageVO.toString());
				
		System.out.println(1111);
		return "user/search";
	};
	
	@GetMapping("/registration")
	public String registration() {
		return "/reg/registration";
	}
	
	@GetMapping("/payment")
	public String payment() {
		return "/pay/payment";
	}
	
	@GetMapping("/join")
	public String join() {
		return "/user/join";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("/reserv_check")
	public String reserv_check() {
		return "/user/reserv_check";
	}
	
	@GetMapping("/reserv_result")
	public String reserv_result() {
		return "/user/reserv_result";
	}
	
	@GetMapping("/userDetail")
	public String userDetail() {
		return "/user/userDetail";
	}
	
	
	
}
