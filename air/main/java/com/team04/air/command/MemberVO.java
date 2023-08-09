package com.team04.air.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVO {

	private String reservation_num; //예약번호
	
	private String member_num; //회원번호
	private String member_name; //회원이름
	private String member_registNum; //회원 주민번호
	private String member_id; //아이디
	private String member_pw; //비밀번호
	private String member_addr; //주소
	private String member_email; //회원이메일
	private String member_gender; //성
}
