package com.airline.book.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomesticVO {

	private String dom_num; //국내선 순서
	private String airline; //항공사
	private String flight_num; //편명
	
	private String departure_airport; //출발공항
	private String arrival_airport; //도착공항
	
	private String departure_time; //출발시간
	private String arrival_time; //도착시간
	
	private String operating_day; //운항요일
	private String start_date; //시작일자
	private String end_date; //종료일자
	
	//private String model; //기종은 보류
	
	
}
