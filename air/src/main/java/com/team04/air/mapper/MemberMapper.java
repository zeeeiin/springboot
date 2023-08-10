package com.team04.air.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.team04.air.command.MemberVO;

@Mapper
public interface MemberMapper {

	public void regist(MemberVO vo);
	public MemberVO getInfo(String memberNum);
	public MemberVO getLogin(String id); //MemberVO vo or memberNum

//	public MemberVO getInfo(MemberVO vo);
}
