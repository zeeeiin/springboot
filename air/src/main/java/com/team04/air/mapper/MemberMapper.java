package com.team04.air.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.team04.air.command.MemberVO;

@Mapper
public interface MemberMapper {

	public MemberVO getInfo(String memberNum);
}
