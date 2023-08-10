package com.simple.basic.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserAuthHandler implements HandlerInterceptor {

	//컨트롤러 진입하기 이전에 실행됨
	//return true가 들어가면 컨트롤러가 실행되고
	//return false가 들어가면 컨트롤러가 실행되지 않는다.
	//스프링 설정파일에 인터셉터 등록
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		System.out.println("UserAuthHandler 동작함");
		
		//세션검사
		HttpSession session = request.getSession();
		if(session.getAttribute("username") != null ) { //로그인이 된 사람
			return true;
		} else { //로그인이 안 되있는 경우
			
			response.sendRedirect( request.getContextPath() + "/user/login" );
			return false; //컨트롤러를 실행하지 않겠다. 반드시 적어야함
		}
		
		
	}

	//컨트롤러 실행후에 동작함
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//ModelAndView를 사용해서 컨트롤러에서 돌아오는 데이터를 뽑아낼 수도 있고 뷰의 정보 확인
		System.out.println("UserAuthHandler post핸들러 동작함");
	
	}

	
}
