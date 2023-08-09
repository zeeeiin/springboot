package com.team04.air.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team04.air.command.AirVO;
import com.team04.air.command.MemberVO;
import com.team04.air.command.Non_MemberVO;
import com.team04.air.command.ReservationVO;
import com.team04.air.service.AirService;
import com.team04.air.service.MemberService;
import com.team04.air.service.PassengerService;
import com.team04.air.util.Criteria;
import com.team04.air.util.PageVO;


@Controller
@RequestMapping("/air")
public class AirController {

	@Autowired
	@Qualifier("airService")
	private AirService airService;
	
	@Autowired
	@Qualifier("passengerservice")
	private PassengerService passengerservice;
	
	
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
		return "/user/search";
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
	
//	@GetMapping("/reserv_result")
//	public String reserv_result() {
//		return "/user/reserv_result";
//	}
	
	@GetMapping("/userDetail")
	public String userDetail() {
		return "/user/userDetail";
	}
	
	
	/////////////////////////////////////////////////

	
	@PostMapping("/resForm")
	public String info(@RequestParam("reservation_num") String resNum, Model model) {		

			ReservationVO vo = passengerservice.getInfo(resNum);
			if(vo != null) {
				model.addAttribute("vo", vo);
				
				System.out.println(vo);
				System.out.println(resNum);
				
				return "/user/reserv_result";
			} else {
				model.addAttribute("vo", new Non_MemberVO());
				System.out.println("error : new Non_MemberVO");
				return "/user/reserv_check";
			}			

		
	}
	
	@Autowired
	private MemberService memberService;
	
	//@Valid @RequestParam("memberNum") String memberNum, Model model

	@PostMapping("/joinForm")
	public String getJoin(@Valid @RequestParam("memberNum") String memberNum,
			Errors errors, Model model ) { //MemberVO vo
		
		MemberVO vo = memberService.getInfo(memberNum);		
		model.addAttribute("vo", vo);				
		
		System.out.println(vo.toString());
		
		if( vo != null ) {
			
			
		}
		
		
		
		return "/user/joinResult";
		
//		if( vo != null ) {
//			
//			if(errors.hasErrors()) {
//			List<FieldError> list = errors.getFieldErrors();		
//				for(FieldError err : list) {
//					model.addAttribute(err.getField(), err.getDefaultMessage());
//				}
//			}
//			System.out.println(vo);
//			//System.out.println(memberNum);
//			return "/user/joinResult";			
//				
//		} else {
//			//model.addAttribute("vo", new MemberVO());
//			System.out.println("Error: new MemberVO");
//			return "/user/join";
//		}			
		
	}
	
	
	
	
	
	
}
