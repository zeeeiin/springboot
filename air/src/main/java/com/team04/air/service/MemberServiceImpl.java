package com.team04.air.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team04.air.command.MemberVO;
import com.team04.air.mapper.MemberMapper;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy--MM-dd HH:mm:ss");
	 
	@Autowired
	private MemberMapper memberMapper;

//	@Override
//	public void getInfo(MemberVO vo) {		
//		
//	}
	
	//회원가입
	@Override
	public void regist(MemberVO vo) {		
		
	}
	//회원조회
//	@Override
//	public List<MemberVO> getInfo(MemberVO vo) {		
//		
//		return memberMapper.getInfo(vo);
//	}

	@Override
	public MemberVO getInfo(String memberNum) {		
		
		return memberMapper.getInfo(memberNum);
	}

	@Override
	public MemberVO getLogin(String id) { //MemberVO vo
		
		return memberMapper.getLogin(id);
	}


}
