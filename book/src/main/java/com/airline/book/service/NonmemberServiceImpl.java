package com.airline.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.book.command.Non_MemberVO;

@Service("nonmemberService")
public class NonmemberServiceImpl implements NonmemberService {
	
	@Autowired
	private NonmemberMapper nonmemberMapper;

	@Override
	public Non_MemberVO getNonmemberNum(String nonmember_num) {
		
		return nonmemberMapper.getNonmemberNum(nonmember_num);
	}
	
	
	

}
