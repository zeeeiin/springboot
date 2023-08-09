package com.team04.air.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team04.air.command.MemberVO;
import com.team04.air.mapper.MemberMapper;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy--MM-dd HH:mm:ss");
	 
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public MemberVO getInfo(String memberNum) {		
		
		return memberMapper.getInfo(memberNum);
	}
}
