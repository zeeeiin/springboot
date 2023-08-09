package com.team04.air.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team04.air.command.Non_MemberVO;
import com.team04.air.command.ReservationVO;
import com.team04.air.mapper.PassengerMapper;

@Service("passengerservice")
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerMapper passengerMapper;

//	@Override
//	public Non_MemberVO getInfo(int reservation_num) {
//		
//		return passengerMapper.getInfo(reservation_num);
//	}

	@Override
	public ReservationVO getInfo(String resNum) {
		
		return passengerMapper.getInfo(resNum);
	}

		
}
