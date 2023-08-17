package com.zei.JwtEx.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter{

	//attemptAuthentication를 오버라이딩하면
	//클라이언트에서 post형태로 /login 요청이 들어오면 실행된다. (이걸 디폴트 요청으로 잡는다.)
	
	
	//멤버변수 하나 선언
	private AuthenticationManager authenticationManager;
	
	
	//생성될 때 AuthenticationManager를 생성자 매개변수를 받는다.
	public CustomLoginFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("========attemptAuthentication실행됨========");
//		지금 만든게 authenticationFilter		
			
		//로그인처리 - 로그인시도하는 사람은 반드시 form타입으로 전송해야한다.(JSON형식도 받을 수 있다 + JSON맵핑처리)
		//authenticationManager가 실행되면 userDetailsService가 동작한다.
		
		//1. username,password를 받음
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);
		
		//스프링 시큐리티가 로그인에 사용하는 토큰객체
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(username, password);
		
		//AuthenticationManager가 실행되면 userDeatilsService가 실행된다.
		Authentication authentication = authenticationManager.authenticate(token);
		System.out.println(authentication);
		
		
		
		//return super.attemptAuthentication(request, response);
		return authentication; 
		//여기서 반환되는 return은 시큐리티 세션이 가져가서 
		//new 시큐리티세션(new 인증객체(new 유저객체))) 형태로 저장시킨다. 
	}

}