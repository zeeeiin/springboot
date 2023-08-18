package com.coding404.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.coding404.demo.user.MyUserDetailService;

@Configuration //설정파일 어노테이션
@EnableWebSecurity //이 설정파일을 시큐리티 필터에 추가 
@EnableGlobalMethodSecurity(prePostEnabled = true) //어노테이션으로 권한을 지정할 수 있게 함
public class SecurityConfig {
	
	//나를기억해 에서 사용할 UserDetailService
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	//비밀번호 암호화 객체
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		//http 객체를 자동으로 보내는데 - 설정파일을 보내주는 역할
		
		//csrf토큰x =>  
		http.csrf().disable();
		
		
		//권한 설정 builder
		//http.authorizeHttpRequests( authorize -> authorize.anyRequest().permitAll()); 
		//authorize 객체를 받아옴/모든 요청에 대해 권한없이 허용하겠다.
		
		
		//모든페이지에 대해 거부
		//http.authorizeHttpRequests( authorize -> authorize.anyRequest().denyAll()); 
		
		//user페이지에 대해서 인증이 필요하다. //authenticated = 인증. 모든 리퀘에 대해 인증이 필요하다면
		//http.authorizeHttpRequests( authorize -> authorize.
//													antMatchers("/user/**").
//													authenticated()); 
			
		
		//hasRole = 권한 -> 인증과는 다르다.
		//user페이지에 대해서 권한이 필요
		//http.authorizeHttpRequests( authorize -> authorize.antMatchers("/user/**")).hasRole("USER"));
		
//		http.authorizeHttpRequests( authorize -> authorize.antMatchers("/user/**").hasRole("USER")
//															.antMatchers("/admin/**").hasRole("ADMIN"));
		
		
		//all페이지는 인증만 되면 되고, user페이지는 user권한, admin페이지는 admin권한, 이 외의 나머지 모든 페이지는 접근이 가능
//		http.authorizeHttpRequests( authorize -> authorize.antMatchers("/all").authenticated()
//															.antMatchers("/user/**").hasRole("USER")
//															.antMatchers("/admin/**").hasRole("ADMIN")
//															.anyRequest().permitAll() );
//		
		
		//all페이지는 인증만 되면 됨, user페이지는 셋 중 하나만 권한이 있어도 접속이 가능. (셋 중 하나만 권한을 가지면 된다)
//		http.authorizeHttpRequests( authorize -> authorize.antMatchers("/all").authenticated()
//															.antMatchers("/user/**").hasAnyRole("USER", "ADMIN", "TESTER")
//															.antMatchers("/admin/**").hasRole("ADMIN")
//															.anyRequest().permitAll() );
//		
		
		//시큐리티 설정파일을 만들면, 시큐리티가 제공하는 기본 로그인페이지가 보이지 않게 된다.
		//시큐리티가 사용하는 기본 로그인 페이지를 사용하겠다는 설정
		//권한 or 인증이 되지 않으면 기본으로 선언된 로그인 페이지를 보여주게 된다.
		//http.formLogin( Customizer.withDefaults()); //기본로그인페이지에서 사용
		
		//사용자가 제공하는 폼기반 로그인 기능을 사용할 수 있게 된다.
		//http.formLogin().loginPage("/login");
		
		
		//사용자가 제공하는 폼기반 로그인 기능을 사용할 수 있게 된다.
		http.formLogin()
			.loginPage("/login") //로그인화면
			.loginProcessingUrl("/loginForm") //로그인시도 요청경로 -> 스프링이 로그인 시도를 낚아채서 UserDatailService로
			.defaultSuccessUrl("/all") //로그인 성공시 페이지
			.failureUrl("/login?err=true") //로그인 실패시 이동할 url
			.and()
			.exceptionHandling().accessDeniedPage("/deny") //권한이 없을 때 이동할 리다이렉트 경로
			.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/hello"); //default 로그아웃 경로 /logout, /logout 주소를 직접 작성할 수 있고, 로그아웃 성공시 리다이렉트할 경로 
		
		
		//rememberMe 나를 기억해 설정
//		http.rememberMe()
//			.key("coding404") //토큰(쿠키)을 만들 비밀키 - 이걸 이용해 알 수 없는 형식의 토큰을 만들어준다.
//			.rememberMeParameter("remember-me") //화면에서 전달받는 checked name 명
//			.tokenValiditySeconds(60) //쿠키(토큰)의 유효시간
//			.userDetailsService(myUserDetailService) //리멤버미토큰이 있을 때 실행시킬 userDetailsService 객체
//			.authenticationSuccessHandler( customRememberMe() ); //나를 기억해가 동작할 때, 실행할 핸들러 객체를 넣는다.
//		
//		return http.build();
//	}
	
	
	//customRemeberMe
//	@Bean
//	public CustomRememberMe customRememberMe() {
//		 CustomRememberMe me = new CustomRememberMe("/all");//리멤버미 성공시 실행시킬 리다이렉트 주소
//		 return me;
//	}
//		
		return http.build();
//	}
}
}
