package com.team04.air.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageVO {

	private int start;
	private int end;
	private boolean prev;
	private boolean next;
	private int realEnd;
	
	private int total;
	private int page;
	private int amount;
	private Criteria cri;
	
	private int pnCount = 10;
	private List<Integer> pageList;
	
	public PageVO(Criteria cri, int total) {
		this.cri = cri;
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		
		this.end = (int)Math.ceil( this.page / (double)this.pnCount ) * this.pnCount;
		
		this.realEnd = (int)Math.ceil( this.total / (double)this.amount );
		
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		
		this.prev = this.start > 1;
		
		this.next = this.realEnd > this.end;
		
		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
	}
	
	
}
