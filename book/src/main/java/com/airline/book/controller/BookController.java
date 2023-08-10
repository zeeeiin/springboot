package com.airline.book.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.airline.book.command.DomesticVO;
import com.airline.book.command.Non_MemberVO;
import com.airline.book.command.ReservationVO;
import com.airline.book.service.BookMapper;
import com.airline.book.service.BookService;

//import com.airline.book.command.MemberVO;
//import com.airline.book.command.ReservationVO;
//import com.airline.book.service.BookService;
//import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	@Qualifier("bookService")
	public BookService bookService;	
	
	@GetMapping("/main")
	public String main() {		
		return "main";		
	}
	
	
	@GetMapping("/select")
	public String select() {
		
		return "user/select2";
	}
	

	@GetMapping("/search")
	public String search() {
		
		return "user/search2";
	}
	
	
	@GetMapping("/reservation")
	public String detail(String reservation_num, Model model) { //비회원 예약내역 조회
		
		
		return "";
	}
	

}
