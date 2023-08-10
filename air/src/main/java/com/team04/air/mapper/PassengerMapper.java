package com.team04.air.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.team04.air.command.Non_MemberVO;
import com.team04.air.command.ReservationVO;

@Mapper
public interface PassengerMapper {
   
//   public ArrayList<Non_MemberVO> getInfo(int reservation_num);
//   public Non_MemberVO getInfo(int reservation_num);
   public ReservationVO getInfo(String resNum);
}