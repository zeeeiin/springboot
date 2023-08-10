package com.team04.air.service;

import java.util.ArrayList;

import com.team04.air.command.Non_MemberVO;
import com.team04.air.command.ReservationVO;

public interface PassengerService {

   //public ReservationVO getInfo(String resNum);
//   public Non_MemberVO getInfo(int reservation_num);

	public ReservationVO getInfo(String resNum);
   
}