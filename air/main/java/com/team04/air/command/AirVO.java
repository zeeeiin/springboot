package com.team04.air.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirVO {
	
	private Integer air_id;
	private String airlines;
	private String flight_num;
	private String departure_airport;
	private String arrival_airport;
	private String departure_time;
	private String arrival_time;
	private String flight_date;
	private String start_date;
	private String end_date;
	private Integer seat;
	private Integer price;
	
}
