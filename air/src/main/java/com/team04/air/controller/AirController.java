package com.team04.air.controller;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@Qualifier("memberService")
	private MemberService memberService;
	
	@Autowired
	@Qualifier("passengerService")
	private PassengerService passengerService;
	
	
	@GetMapping("/")
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
	public String registration(@RequestParam("air_id") int air_id,
	                        Model model) {
	      
		AirVO vo = airService.getDetail(air_id);
	    
		model.addAttribute("vo", vo);
		System.out.println(vo.toString());
		return "/reg/registration";
	}
	
	@PostMapping("/payForm") //resistration 에서 payment 넘어갈 때
	public String payment(@ModelAttribute("vo") AirVO vo) {
		
		System.out.println(vo.toString());

		//return "redirect:/pay/payment?air_id="+vo.getAir_id();
		return "/pay/payment";
		
	}
	
	@PostMapping("/regForm") //payment에서 결제버튼 누를 때 
	public String regForm(@ModelAttribute("vo") AirVO vo) {
		
		System.out.println(vo.toString());
		
		
		return "/pay/payment";
		
	}
	
//	@PostMapping("/payForm")
//	   public String payment(@ModelAttribute("vo") AirVO vo) {
//	      
//	      String pk = "R"+ UUID.randomUUID().toString().split("-")[0];
//	      vo.setResNum(pk);
//	      int result = airService.passengerDetail(vo);
//
//	      return "/pay/payment";
//	   }
//
//   @GetMapping("/payment")
//   public String payment(@RequestParam("air_id") int air_id, 
//	         @RequestParam("resNum") String resNum) {
//
//	      System.out.println(air_id);
//	      System.out.println(resNum);
//
//	      return "/pay/payment";
//   }
	

	
	@GetMapping("/join")
	public String join() {
		return "/user/join";
	}
	
	@GetMapping("/login")//로그인페이지 이동
	public String login() {
		return "/user/login";
	}
	
	
	
	@PostMapping("/loginForm")
	public String loginForm(@RequestParam("id") String id,
							@RequestParam("password") String password,
							Model model, HttpSession session ) {
		
		MemberVO vo = memberService.getLogin(id);
		
		
		
		
		if( vo != null && vo.getPassword().equals(password)) { //성공			
			session.setAttribute("id", id);
			return "redirect:/user/mypage";
		} else { //실패
			model.addAttribute("loginError", "아이디 또는 비밀번호가 잘못되었습니다");
			return "/user/login"; 
		}	
	}
	
	
//	@PostMapping("/loginForm")//로그인 버튼 누를 때
//	public String loginForm(@RequestParam("id") String id,
//							@RequestParam("pw") String pw,
//							HttpSession session) {
//		System.out.println(id);
//		System.out.println(pw);
//		
//		
//		
//		
//		
//		String memberNum ="vo.memberNum의 위치";
//		session.setAttribute("login", id);
//		session.setAttribute("memberNum", memberNum);
//		//session.setAttribute("memberNum"+id, memberNum);
//		
//		return "main";
//	}
	
	@GetMapping("/reserv_check")
	public String reserv_check() {
		return "/user/reserv_check";
	}

	@PostMapping("/resForm")
	public String info(@RequestParam("reservation_num") String resNum, 
			Model model) {		

		ReservationVO vo = passengerService.getInfo(resNum);
		if(vo != null) {
			model.addAttribute("vo", vo);

			System.out.println(vo);
			System.out.println(resNum);

			return "/user/reserv_result";
		} else {
			model.addAttribute("vo", new ReservationVO());
			System.out.println("error : new ReservationVO");
			return "/user/reserv_check";
		}		
	}
	
	
	@GetMapping("/reserv_result")
	public String reserv_result() {
		return "/user/reserv_result";
	}
	
	@GetMapping("/userDetail")
	public String userDetail(HttpSession session) {
		String memberNum = (String)session.getAttribute("memberNum");
		//MemberVO vo = memberService.getInfosession(memberNum);
		return "/user/userDetail";
	}
	
	@GetMapping("/joinResult")
	public String joinResult() {
		return "/user/joinResult";
	}
	

	//@Valid @RequestParam("memberNum") String memberNum, Model model

	@PostMapping("/joinForm")
	public String getJoin(@Valid MemberVO vo,
			Errors errors, Model model ) { //MemberVO vo

		//난수생성 - resNum, memberNum
		//	      String resNum = "R"+ UUID.randomUUID().toString().split("-")[0];
		//	      vo.setResNum(resNum);

		String memberNum = "M" + UUID.randomUUID().toString().split("-")[0];
		System.out.println("!!!   "+vo.toString()); 
		vo.setMemberNum(memberNum);
		System.out.println(vo.toString()); 

		memberService.regist(vo); //회원정보저장

		//vo = memberService.getInfo(memberNum); //회원 정보 조회   
		model.addAttribute("vo", vo); // 회원 정보를 모델에 추가해서 다음 화면에서 사용         

		System.out.println(vo.toString());      

		return "/user/joinResult";
//	      if(errors.hasErrors()) {
//	         List<FieldError> list = errors.getFieldErrors();      
//	         for(FieldError err : list) {
//	            if(err.isBindingFailure()) {
//	               model.addAttribute(err.getField(), "잘못된 입력입니다.");               
//	            } else {            
//	               model.addAttribute(err.getField(), err.getDefaultMessage());
//	            }
//	         }
//	         System.out.println(vo);
//	         System.out.println(memberNum);
//	         return "/user/join"; //return "redirect:/user/join";
//	         
//	      } else {
//	         model.addAttribute("vo", new MemberVO());
//	         System.out.println("Error: new MemberVO");      
	//
//	         return "/user/joinResult";   //user/joinResult   
//	      }         
	      
	   }

	
	
}
