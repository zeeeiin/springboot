package com.team04.air.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team04.air.command.AirVO;
import com.team04.air.command.Non_MemberVO;
import com.team04.air.mapper.AirMapper;
import com.team04.air.util.Criteria;

@Service("airService")
public class AirServiceImpl implements AirService {

	@Autowired
	private AirMapper airMapper;
	
	@Override
	public ArrayList<AirVO> getList(Criteria cri) {
		
		
		return airMapper.getList(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		
		return airMapper.getTotal(cri);
	}

	@Override
	public AirVO getDetail(int air_id) {
		return airMapper.getDetail(air_id);
	}

	@Override
	public int passengerDetail(AirVO vo) {
	   int result = airMapper.passengerDetail(vo);
	   return result;
	}

	@Override
	public int updateSeat(int air_id) {
		int result = airMapper.updateSeat(air_id);
		return result;
	}

}
