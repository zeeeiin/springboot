package com.zei.JwtEx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterOne implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("======filter one 실행됨=====>");
		
		//토큰 뜯기 
		//지금 ServletRequest객체라서 getHeader없음 -> 형변환해주기
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//토큰검사
		String token = req.getHeader("Authorization");		
		
		//토큰이 있는 경우
		if(token != null && token.equals("token")) { // 테스트용도의 토큰 //지금은 직접 발행안받고 문자열 토큰이라고 가정.
		
			chain.doFilter(request, response); //토큰이 있다면 다음 필터로 연결 -> 이게 없으면 끊긴다.
			
		} else { //토큰이 없는 경우
			res.setCharacterEncoding("utf-8");
			res.setContentType("text/plain");
			res.sendError(403, "토큰없음");
		}
		
		
		
		chain.doFilter(request, response); //다음 필터로의 연결 -> 이게 없으면 끊긴다.
		
	}


	
}