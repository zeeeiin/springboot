package com.coding404.trip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/trip")
public class TripController {

	@RequestMapping("/main")
	public String home() {
		return "/index";
	}	
	
	@GetMapping("/notice_list")
	public String noticeList() {
		
		String writer = "admin";
		
		
		return "trip/notice_list";
	}
}
