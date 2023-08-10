package com.team04.air.command;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVO {

	private String resNum; //예약번호	
	private String memberNum; //회원번호
	private String registNum; //회원 주민번호

	@NotEmpty(message = "이름은 필수입니다")
	private String lastName; //회원이름 - 성
	private String firstName; //회원이름 - 이름	
	
	@Pattern(regexp="[a-zA-Z0-9],{8,}")
	private String id; //아이디
	
	@Pattern(regexp="[a-z0-9@!~#$%^&*_+]{8,}")
	private String password; //비밀번호
	
	private String phoneNum; //연락처
	
	@Email(message = "이메일 형식이어야 합니다")
	private String email; //회원이메일
	
	private String gender; //성별
	
	//private LocalDateTime regdate; //가입날짜
}
