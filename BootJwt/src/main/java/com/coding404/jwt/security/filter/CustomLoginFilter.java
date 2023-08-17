package com.coding404.jwt.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.coding404.jwt.security.config.JWTService;
import com.coding404.jwt.user.MyUserDetails;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter{
	
	//멤버변수 하나 선언
	private AuthenticationManager authenticationManager;
	
	
	//생성될 때 AuthenticationManager를 생성자 매개변수를 받는다.
	public CustomLoginFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	//attemptAuthentication를 오버라이딩 하면
	//클라이언트에서 포스트형태로 /login 로 들어오면 실행됩니다.(이걸 디폴트 요청으로 잡는다.)
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
		System.out.println("내가 실행되었다는, 로그인 성공:" + authentication);
				
		
		//return super.attemptAuthentication(request, response);
		return authentication; 
		//여기서 반환되는 return은 시큐리티 세션이 가져가서 
		//new 시큐리티세션(new 인증객체(new 유저객체))) 형태로 저장시킨다. 
	}

	//로그인 성공 후에 실행되는 메소드
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("==========로그인 성공 핸들러 실행됨=========");
		
		//토큰발행 헤더에 담고 클라이언트로 전달
		//어떤 값이 들어있는지 확인해보기
		System.out.println("로그인 성공 이후 인증객체 : "  + authResult);		
		
		//오브젝트 형		
		MyUserDetails principal =  (MyUserDetails)authResult.getPrincipal();
		
		//예전엔 세션에 저장했지만, 토큰을 발행해서 헤더에 담고 클라이언트로 전달
		String token = JWTService.createToken(principal.getUsername()); //userid를 받아야한다. 이렇게 뽑으면 유저디테일 객체
		
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Authorization", "Bearer" + token); //헤더의 키 값과 :
		response.getWriter().println("로그인성공(아이디)" + principal.getUsername());
		
	}

	
	//로그인 실패한 후에 실행되는 메소드
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println("==========로그인 실패 핸들러 실행됨=========");
		
		response.setContentType("text/html; charset=UTF-8"); //응답을 보낼 컨텐츠 타입, 인코딩 형식 지정
		
		//response.getWriter().println("응답할 내용");
		response.sendError(500, "아이디, 비밀번호를 확인하세요");
	
	
	}

	
	
	
}
