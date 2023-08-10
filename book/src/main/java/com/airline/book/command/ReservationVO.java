package com.airline.book.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationVO {

	private String reservation_num; //예약번호
	private String member_num;
	private String nonmember_num;
	
}
