package com.airline.book.service;

import java.util.ArrayList;

import com.airline.book.command.DomesticVO;
import com.airline.book.command.MemberVO;
import com.airline.book.command.Non_MemberVO;
import com.airline.book.command.ReservationVO;

public interface BookService {
	
	public ArrayList<DomesticVO> getList(String dom_num); //국내선 조회? 
	//public DomesticVO getDomdetail(String dom_num);//국내선 상세정보?	

	//항공권예악
	
	public Non_MemberVO getNonmem(String nonmember_num); //비회원 조회
	public MemberVO getMem(String member_num); //회원조회
	public ReservationVO getDetail(String reservation_num); //예약정보
	
}
