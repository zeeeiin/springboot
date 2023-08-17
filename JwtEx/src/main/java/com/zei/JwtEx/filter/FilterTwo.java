package com.zei.JwtEx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterTwo implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("=====filter Two 실행됨====>");
		
		//토큰의 유효성 검사		
		
		chain.doFilter(request, response);//다음필터로의 연결		
	}

}