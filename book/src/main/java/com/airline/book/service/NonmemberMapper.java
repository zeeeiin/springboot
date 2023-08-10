package com.airline.book.service;

import org.apache.ibatis.annotations.Mapper;

import com.airline.book.command.Non_MemberVO;

@Mapper
public interface NonmemberMapper {
	
	public Non_MemberVO getNonmemberNum(String nonmember_num);
	
	
}
