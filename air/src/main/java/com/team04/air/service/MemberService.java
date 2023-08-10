package com.team04.air.service;


import java.util.List;

import com.team04.air.command.MemberVO;

public interface MemberService {

	public void regist(MemberVO vo);
	public MemberVO getInfo(String memberNum);
	public MemberVO getLogin(String id); //MemberVO vo or memberNum
	
//	public MemberVO getInfo(MemberVO vo);
	//list나 매개변수 하나도 가능
}