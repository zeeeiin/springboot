package com.coding404.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coding404.jwt.command.UserVO;
import com.coding404.jwt.security.config.JWTService;

@RestController //rest
public class APIController {

	//로그인 기능이라고 가정. 토큰 검증
	//{"username": "xxx", "password" : "1234", "role" : "ROLE_USER"}
	@PostMapping("/login") 
	public ResponseEntity<String> login(@RequestBody UserVO vo) {
		
		System.out.println(vo.toString());
	
		//로그인 시도 ~> 가 일어났다면 토큰을 만든다.
		String token = JWTService.createToken(vo.getUsername()); //회원아이디
		
		return new ResponseEntity<>(token, HttpStatus.OK); //토큰을 ResponseEntity에 실을 것.
	}

	
	//사용자정보 확인 
	//토큰을 헤더에 담아서 보내기 → 사용자 정보와 함께 요청 (헤더 - Authorization: 토큰)
//	@PostMapping("/api/v1/getInfo")
//	public ResponseEntity<Object> getInfo(HttpServletRequest request) {
//		
//		//헤더에 담긴 토큰
//		String token = request.getHeader("Authorization");
//		System.out.println("전달된토큰:" + token);
//		
//		//토큰의 무결성 검사
//		try {
//			boolean result = JWTService.validateToken(token);
//			System.out.println("토큰 무결성:"+result);
//			
//		} catch (Exception e) {			
//			e.printStackTrace();
//			return new ResponseEntity<>("토큰위조", HttpStatus.UNAUTHORIZED);
//		}
//		
//		return new ResponseEntity<>("success getInfo", HttpStatus.OK);
//		
//	}
	
	//시큐리티 요청테스트
	@GetMapping("/api/v1/hello")
	public String hello() { //rest라서 responsebody달려있음
		
		return "<h3>헬로</h3>";
	}
	
	
	
	//토큰기반으로 한 사용자 요청정보 반환기능
	@PostMapping("/api/v1/getInfo")
	public ResponseEntity<Object> getInfo() {
		
		System.out.println("토큰이 있으면 호출됨(데이터베이스 연결이 처리)");
	
		return new ResponseEntity<>("데이터", HttpStatus.OK);
	}
	
	//회원가입 -> 토큰이 필요한가?
	@PostMapping("/join")
	public ResponseEntity<Object> join() {
		return new ResponseEntity<>("가입성공", HttpStatus.OK);
	}
	
}
