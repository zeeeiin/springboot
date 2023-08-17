package com.zei.JwtEx.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
	
	private String username;
	private String password;
	private String role;
		
}