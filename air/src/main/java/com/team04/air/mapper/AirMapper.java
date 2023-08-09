package com.team04.air.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.team04.air.command.AirVO;
import com.team04.air.util.Criteria;

@Mapper
public interface AirMapper {

	public ArrayList<AirVO> getList(@Param("cri") Criteria cri);
	public int getTotal(@Param("cri") Criteria cri);
	
}
