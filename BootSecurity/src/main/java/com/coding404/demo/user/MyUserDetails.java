package com.coding404.demo.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.coding404.demo.command.UserVO;

//화면에 전달되는데, 화면에서 사용할 값들은 반드시 getter로 생성해줘야한다.
public class MyUserDetails implements UserDetails {

	//멤버변수로 userVO객체를 받는다
	private UserVO userVO;
	
	public MyUserDetails(UserVO vo) {
		this.userVO = vo;
	}
	
	//화면에서 권한도 사용할 수 있게 해주고 싶다면? getter 만들기
	// +++@
	public String getRole() {
		return userVO.getRole();
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//제일 중요한 부분 - GrantedAuthority변환을 처리하는 영역
		//uservo가 가진 권한을 리스트에 담아서 반환시키면, 스프링 시큐리티가 참조해서 사용한다. 
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				
				return userVO.getRole();
			}
		});
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userVO.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}