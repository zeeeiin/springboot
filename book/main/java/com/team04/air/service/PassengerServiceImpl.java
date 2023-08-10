package com.team04.air.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team04.air.command.Non_MemberVO;
import com.team04.air.mapper.PassengerMapper;

@Service("passengerservice")
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerMapper passengerMapper;

	@Override
	public Non_MemberVO getInfo(int reservation_num) {
		
		return passengerMapper.getInfo(reservation_num);
	}

//	@Override
//	public Non_MemberVO getInfo(int reservation_num) {
//		
//		return passengerMapper.getInfo(reservation_num);
//	}
	
	
	
//	public ArrayList<Non_MemberVO> getInfo(int reservation_num) {
//	@Override
//	public Non_MemberVO getInfo(Non_MemberVO vo) { //String reservation_num
//		
//		String reservation_num = vo.getReservation_num();
//		String name = vo.getNonmember_name();
//		
//		return passengerMapper.getInfo(vo);
//	}

	
}
