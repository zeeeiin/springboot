package com.zei.JwtEx.user;

import org.apache.ibatis.annotations.Mapper;

import com.zei.JwtEx.command.UserVO;

@Mapper
public interface UserMapper {
	
	public void join(UserVO vo); //가입
	public UserVO login(String username); //로그인
	
}