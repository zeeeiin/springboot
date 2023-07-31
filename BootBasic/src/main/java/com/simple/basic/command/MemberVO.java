package com.simple.basic.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Controller
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {

	@Pattern(regexp="[a-zA-Z0-9]{8,}")
	private String id;
	
	@Pattern(regexp="[a-z0-9@!~#$%^&*_+]{8,}")
	private String pw;

}
