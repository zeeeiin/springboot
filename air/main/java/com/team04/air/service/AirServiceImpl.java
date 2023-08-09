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

}
