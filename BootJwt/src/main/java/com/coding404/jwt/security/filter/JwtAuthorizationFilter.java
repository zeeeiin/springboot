package com.coding404.jwt.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.coding404.jwt.security.config.JWTService;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	//생성자
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);	
	}

	//필터기능
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("====JwtAuthorizationFilter 실행됨====");
		
		//헤더에 담긴 토큰의 유효성을 확인하고, 인증된 토큰이면 우리서비스로 연결, 만료or위조된 경우에는 error메시지반환
		String headers = request.getHeader("Authorization");
		
		//헤더가 없거나 Bearer로 시작하지 않으면
		if(headers == null || headers.startsWith("Bearer ") == false) {
			//Bearer 로 시작하면 true, 이렇게 시작하지 않으면 에러 메세지가 넘어옴
			response.setContentType("text/plain; charset=UTF8");
			response.sendError(403, "토큰없음");
			
			return; //함수종료
			
		}
		
		//여길 지나왔다는건 토큰이 있다는 것.
		//토큰의 유효성검사
		try {
			
			String token = headers.substring(7); //Bearer 공백 이후 진짜 토큰
			boolean result = JWTService.validateToken(token); //토큰 검증
			
			if(result) { //result == true면 정상토큰
				chain.doFilter(request, response); //컨트롤러로 연결됨
			} else { //토큰이 만료됨
				response.setContentType("text/plain; charset=UTF8");
				response.sendError(403, "토큰만료");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			//토큰이 위조
			response.setContentType("text/plain; charset=UTF8");
			response.sendError(403, "토큰위조");
		}
		
		
		//super.doFilterInternal(request, response, chain);
	}
	
	

}
