package com.team04.air.util;

import lombok.Data;

@Data
public class Criteria {

	private int page;
	private int amount;
	
	private String searchAirline;
	private String searchFlight_Num;
	private String searchDeparture_Airport;
	private String searchArrival_Airport;
	private String searchDeparture_time;
	private String searchArrival_time;
	private String searchFlight_Date;
	private String startDate;
	private String endDate;
	private Integer seat;
	private String searchPrice;
	
	public Criteria() {
		this.page = 1;
		this.amount = 10;
	}
	
	public Criteria(int page, int amount) {
		this.page = page;
		this.amount = amount;
	}
	
	public int getPageStart() {
		return (page - 1) * amount;
	}
}
