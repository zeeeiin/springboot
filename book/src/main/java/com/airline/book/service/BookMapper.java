package com.airline.book.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.airline.book.command.DomesticVO;
import com.airline.book.command.MemberVO;
import com.airline.book.command.Non_MemberVO;
import com.airline.book.command.ReservationVO;

@Mapper
public interface BookMapper {
	
	public ArrayList<DomesticVO> getList(String dom_num); //국내선 조회? 
	//public DomesticVO getDomdetail(String dom_num);//국내선 상세정보?	
	
	public Non_MemberVO getNonmem(String nonmember_num); //비회원 조회
	public MemberVO getMem(String member_num); //회원조회
	public ReservationVO getDetail(String reservation_num); //예약정보조회
	
	
}
