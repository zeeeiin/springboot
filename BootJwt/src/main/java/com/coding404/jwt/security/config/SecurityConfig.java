package com.coding404.jwt.security.config;

import java.util.Arrays;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.coding404.jwt.security.filter.CustomLoginFilter;
import com.coding404.jwt.security.filter.FilterOne;
import com.coding404.jwt.security.filter.FilterTwo;
import com.coding404.jwt.security.filter.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//비밀번호 암호화객체
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
		

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
		
		//기본로그인 방식, 세션, 베이직인증, csrf토큰 전부 사용하지 않음.
		http.csrf().disable();
		
		http.formLogin().disable(); //form기반 로그인을 사용하지 않는다. -> rest방식이라서.
		http.httpBasic().disable(); //Authorization : 아이디형식으로 넘어오는 basic 인증을 하게끔 되어있는데 사용x
		//이제 아이디가 아닌 토큰을 실어보낼거라서 그런 것.
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션인증 기반을 사용하지 않고, JWT사용해서 인증하겠다는 의미
		http.authorizeHttpRequests( auth -> auth.anyRequest().permitAll() ); //모든 요청은 전부 허용
		
		//1. 크로스오리진 필터 생성 cors
		http.cors( Customizer.withDefaults() );
		
		//2. 필터체이닝 연습
		//http.addFilter(new FilterOne() ); //filter타입이 들어가야한다. // 시큐리티타입의 필터를 등록할 때
		
//		http.addFilterBefore(new FilterOne(), UsernamePasswordAuthenticationFilter.class);	
//		http.addFilterBefore(new FilterTwo(), FilterOne.class ); //filterOne보다 이전에 등록
//		http.addFilterAfter(new FilterTwo(), FilterOne.class); //filterOne보다 나중에 등록
		
		//3. 로그인 시도에 AuthenticationManager가 필요하다.
		//++UserDetailService객체 and PasswordEncoder가 반드시 필요.(내부적으로 사용하고 있기 때문.)
//		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class );
		AuthenticationManager authenticationManager = 
							  authenticationManager( http.getSharedObject(AuthenticationConfiguration.class));
		
		System.out.println(authenticationManager);
		
		
		//4. 로그인 필터를 등록
//		http.addFilterBefore(new CustomLoginFilter(), UsernamePasswordAuthenticationFilter.class);
//		http.addFilter(new CustomLoginFilter(authenticationManager));		
		
		
		//5. jwt검증필터를 등록
//		http.addFilterBefore ( new JwtAuthorizationFilter(authenticationManager), BasicAuthenticationFilter.class );
//		http.addFilter(new JwtAuthorizationFilter(authenticationManager) );
		
		
		//6. 요청별로 필터 실행시키기
		// /login 요청에만 CustomLoginFilter가 실행됨
		http.requestMatchers()
			.antMatchers("/login")
			.and() //http 객체를 다시 받음
			.addFilter( new CustomLoginFilter(authenticationManager));
		
		//api로 시작하는 요청에만 jwt필터가 실행됨
		http.requestMatchers()
			.antMatchers("/api/v1/**")
			.antMatchers("/api/v2/**")
			.and()
			.addFilter( new JwtAuthorizationFilter(authenticationManager));
		
		return http.build();		
	}
	
	//로그인시도에 필요한 AuthenticationManager객체
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	//크로스오리진필터
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*")); //모든 요청(주소)에 대해서  허용함
		configuration.setAllowedMethods(Arrays.asList("*")); //모든 요청 메소드를 허용함
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); //모든 요청에 대해서
		return source;
	}


}
