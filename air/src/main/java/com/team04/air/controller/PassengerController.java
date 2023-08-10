//package com.team04.air.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.team04.air.command.ReservationVO;
//import com.team04.air.service.PassengerService;
//
//@Controller
//@RequestMapping("/air")
//public class PassengerController {
//
//	@Autowired
//	@Qualifier("passengerService")
//	private PassengerService passengerService;
//	
//	
//	@PostMapping("/resForm") //예약 확인
//	 public String info(@RequestParam("reservation_num") String resNum, 
//			 			Model model) {      
//
//		 ReservationVO vo = passengerService.getInfo(resNum);
//		 if(vo != null) {
//			 model.addAttribute("vo", vo);
//	            
//			 System.out.println(vo);
//			 System.out.println(resNum);
//	            
//			 return "user/reserv_result";
//		 } else {
//			 model.addAttribute("vo", new ReservationVO());
//			 System.out.println("error : new ReservationVO");
//			 return "user/reserv_check";
//		 }      
//	 }
//	
//	
//	
//	
//	
//	
//}
