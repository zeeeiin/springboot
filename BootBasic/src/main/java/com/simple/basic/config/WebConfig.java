package com.simple.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.simple.basic.controller.HomeController;

@Configuration //설정파일
public class WebConfig implements WebMvcConfigurer { //자바 빈설정을 하기위해서 상속

	//IOC 확인
	@Autowired
	ApplicationContext applicationContext;	
	
	//value 어노테이션의 사용 - application.properties 값을 참조하는데 사용한다.
	@Value("${spring.datasource.url}")
	String url;
	
	
	@Bean //이 메소드를 빈으로 생성 - 리턴으로 객체를 반환하는 모형을 만들면, 빈으로 등록된다.
	public void test() {
		
		TestBean test = applicationContext.getBean(TestBean.class);
		System.out.println("TestBean이 빈으로 등록됨: " + test);
		
		HomeController con = applicationContext.getBean(HomeController.class);
		System.out.println("HomeController가 빈으로 등록됨: " + con);
		
		int count = applicationContext.getBeanDefinitionCount();
		System.out.println("IOC안의 빈의 개수: " + count);
		
		System.out.println("application.properties 키값: " + url);
		
		
	}
	
	//빈 생성의 2가지 전략 - @Controller, @Service 등 이용해서 빈으로 등록
	//스프링 설정파일에 빈으로 등록
	//return 객체를 반환하는 모형을 만들면, 빈으로 등록
	@Bean
	public TestBean test2() {
		TestBean b = new TestBean();
		return b; //b라는 객체가 IOC컨테이너에 만들어진다.
	}
}
