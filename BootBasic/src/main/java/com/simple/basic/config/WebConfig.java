package com.simple.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.simple.basic.controller.HomeController;
import com.simple.basic.util.interceptor.UserAuthHandler;

@Configuration //설정파일
public class WebConfig implements WebMvcConfigurer { //자바 빈설정을 하기위해서 상속

	
	//인터셉터로 사용할 클래스를 bean
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}

	
	//스피링설정에 인터셉터를 추가 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor( userAuthHandler() )
		.addPathPatterns("/user/**") //user로 시작하는 모든 요청에 검사 (모든 경로에 인터셉터를 적용하겠다.)
				.excludePathPatterns("/user/login") // user/login 제외
				.excludePathPatterns("/user/loginForm"); //로그인메서드 제외
				//.addPathPatterns( "/user/mypage" )
				
		//인터셉터는 여러개 있을 수도 이는데, 추가하면 된다.		
	}
	
	
	
	//IOC 확인
//	@Autowired
//	ApplicationContext applicationContext;	
	
	//value 어노테이션의 사용 - application.properties 값을 참조하는데 사용한다.
//	@Value("${spring.datasource.url}")
//	String url;
	
//	@Bean //이 메소드를 빈으로 생성 - 리턴으로 객체를 반환하는 모형을 만들면, 빈으로 등록된다.
//	public void test() {
//		
//		TestBean test = applicationContext.getBean(TestBean.class);
//		System.out.println("TestBean이 빈으로 등록됨: " + test);
//		
//		HomeController con = applicationContext.getBean(HomeController.class);
//		System.out.println("HomeController가 빈으로 등록됨: " + con);
//		
//		int count = applicationContext.getBeanDefinitionCount();
//		System.out.println("IOC안의 빈의 개수: " + count);
//		
//		System.out.println("application.properties 키값: " + url);
		
//	}
	
	//빈 생성의 2가지 전략 - @Controller, @Service 등 이용해서 빈으로 등록
	//스프링 설정파일에 빈으로 등록
	//return 객체를 반환하는 모형을 만들면, 빈으로 등록
//	@Bean
//	public TestBean test2() {
//		TestBean b = new TestBean();
//		return b; //b라는 객체가 IOC컨테이너에 만들어진다.
//	}
}
