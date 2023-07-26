package com.simple.basic.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.basic.command.SimpleVO;

@Controller
@RequestMapping("/view")
public class ViewController {

//	@RequestMapping(value = "/ex01", method = RequestMethod.GET)
	@GetMapping("/ex01")
	public String ex01() {
		return "view/ex01"; //템플릿폴더 밑이 기준 / 리턴에 돌아가는 값은 템플릿위치 기준.
	}
	
	@GetMapping("/ex02")
	public String ex02(Model model) {
		
		SimpleVO vo = SimpleVO.builder()
				.sno(1)
				.first("mong")
				.regdate( LocalDateTime.now() )
				.build();		
		
		ArrayList<SimpleVO> list = new ArrayList<>();
		for(int i = 0; i <= 10; i++) {
			
			SimpleVO simpleVO = SimpleVO.builder()
					.sno(i)
					.first("mong" + i)
					.regdate( LocalDateTime.now() )
					.build();
			
			list.add(simpleVO);
		}
		
		
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
				
		
		return "view/ex02"; 
	}
	
	//ex03 - a링크의 사용
	@GetMapping("/ex03")
	public String ex03(Model model) {
		
		SimpleVO vo = SimpleVO.builder()
				.sno(1)
				.first("mong")
				.regdate( LocalDateTime.now() )
				.build();
		
		
		ArrayList<SimpleVO> list = new ArrayList<>();
		for(int i = 0; i <= 10; i++) {
			
			SimpleVO simpleVO = SimpleVO.builder()
					.sno(i)
					.first("mong" + i)
					.regdate( LocalDateTime.now() )
					.build();
			
			list.add(simpleVO);
		}
		
		model.addAttribute("vo", vo);		
		model.addAttribute("list", list);
	
		return "view/ex03"; 
	}
	
	//키를 받는 첫번째 방법 - 쿼리스트링
	@GetMapping("/result")
	public String ex03_result(@RequestParam("sno") int sno,
							  @RequestParam("first") String first) {
		
		System.out.println("넘어온 값: " + sno + ", " + first );
		
		return "view/ex03_result";
	}
	
	//키를 받는 두 번째 방법 - URL파람
	@GetMapping("/result02/{a}/{b}")
	public String ex03_result02(@PathVariable("a") String name,
								@PathVariable("b") int age) {
		
		System.out.println("넘어온 값: " + name + ", " + age );
		
		return "view/ex03_result";
	}
	
	
	//ex04 - 타임리프 인클루드
	@GetMapping("/ex04")
	public String ex04() {
		return "view/ex04";
	}
	
	
	//ex05 - 타임리프 템플릿 형식으로 결합하기
	@GetMapping("/ex05")
	public String ex05() {
		return "view/ex05";
	}
	
	
	/////////////// quiz /////////////////
	@GetMapping("/quiz")
	public String quiz(Model model) {
		
		SimpleVO vo = SimpleVO.builder()
					.sno(1)
					.first("mong")
					.last("yoon")
					.regdate( LocalDateTime.now() )
					.build();
		
		model.addAttribute("vo", vo);	
		
		return "view/quiz";
	}
	
	@GetMapping("/quiz_result01")
	public String result(@RequestParam("sno") int sno,
 						 @RequestParam("name") String name) {	
			
		System.out.println(sno);
		System.out.println(name);			
		
		return"view/quiz_result01";
	}
	
	
}
