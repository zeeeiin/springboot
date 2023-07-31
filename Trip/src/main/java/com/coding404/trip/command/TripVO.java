package com.coding404.trip.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripVO {

	private String trip_id;
	private String trip_title;
	private String trip_writer;
	private int trip_count;
	private LocalDateTime trip_regdate;
	private String trip_search;
}
