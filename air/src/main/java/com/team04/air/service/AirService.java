package com.team04.air.service;

import java.util.ArrayList;

import com.team04.air.command.AirVO;
import com.team04.air.util.Criteria;

public interface AirService {

	public ArrayList<AirVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	public AirVO getDetail(int air_id);
	public int passengerDetail(AirVO vo);
	public int updateSeat(int air_id);
}
