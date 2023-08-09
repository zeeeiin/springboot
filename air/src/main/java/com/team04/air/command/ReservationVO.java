package com.team04.air.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationVO {

	private String resNum; //예약번호
	private String lastName;
	private String firstName;
	private String mobile;
	private String email;
	
}
