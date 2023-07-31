package com.coding404.myweb.util;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data //getter,setter,toString
public class PageVO {

	private int start; //시작페이지네이션 시작페이지 번호
	private int end; //끝페이지네이션
	private boolean prev; //이전버튼 활성화여부
	private boolean next; //다음버튼 활성화여부
	private int realEnd; //실제보여지는 끝번호


	private int total; //전체 게시글 개수	
	private int page; //cri에 있는 현재 조회하는 페이지
	private int amount; //cri에 있는 데이터 개수
	private Criteria cri; //페이지 기준
	
	private	int pnCount = 10; //페이지네이션 개수
	
	private List<Integer> pageList; //페이지네이션을 리스트로 저장
	
	
	//페이지네이션 클래스는 cri와 total을 매개변수로 받는다.
	public PageVO(Criteria cri, int total) { //total 값은 sql문 밖에서 받아올 것
	
		this.cri = cri;
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		
		//end 페이지 계산
		//page가 1을 가리킬 때, end = 10 일 것이다.
		//page가 11을 가리킬 때, end = 20
//		this.end = (int)(Math.ceil( 현재조회하는 페이지 / 페이지네이션 개수 ) ) * 페이지네이션 개수 ;
		this.end = (int)(Math.ceil( this.page / (double)this.pnCount ) ) * this.pnCount;
		
		
		//start페이지 계산
//		this.start = this.end - 페이지네이션 개수 + 1; //만약 5라면 5-5+1 = 1
		this.start = this.end - this.pnCount + 1;
		
		
		//실제끝번호의 계산 (맨 마지막에 찍히는 페이지네이션) -> 데이터 개수 10개 기준으로 아래처럼 된다.
		//총 게시글 수가 53개라면.. 마지막 번호는 6이 될 것.
		//총 게시글 수가 233개라면.. 마지막 번호는 24가 될 것.
		//전체 게시글 수과 연관이 있다.
		//total 변수에서 뿌려지는 데이터의 개수(화면에 보여지는데이터 수)로 나눠야한다.
		
//		this.realEnd = (int)(Math.ceil( 전체 게시글수 / 데이터개수 ) );
		this.realEnd = (int)(Math.ceil( this.total / (double)this.amount ) );
		
		
		//end페이지 재결정
		//데이터가 25개 있다면 end 값은 10으로 결정난 상태, realEnd = 3
		//이럴 땐 realEnd를 따라가야한다. end를 따라가면 4~10까지가 비기때문에.

		//데이터가 153개 있다면 -> end = 20, realEnd = 16 (실제끝번호를 따라감)
		//데이터가 153개(3번페이지를 조회시) -> end = 10, realEnd = 16 ( end 페이지네이션을 따라감)
		
		if(this.end > this.realEnd) { //end가 더 큰 경우 실제 끝번호를 따라간다.
			this.end = this.realEnd; //end페이지의 재결정
		}
		
		//prev활성화 여부
		//start페이지 결정은 1,11,21,31,41 ... (페이지네이션이 10개일 경우)
		this.prev = this.start > 1; //start가 1보다 크면 true  //1이면 false니까 prev버튼이 안 보인다.
		
		//next버튼 활성화 여부
		//end = 10, realEnd = 16 이라고 할 때 다음버튼이 있다는 의미
		//반례까지 다 생각하고 만들어야함
		this.next = this.realEnd > this.end;
		
		//타임리프 - 리스트에 페이지네이션을 담음
		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
		//1부터 10까지 반복
		
		
		
	}
	
}
