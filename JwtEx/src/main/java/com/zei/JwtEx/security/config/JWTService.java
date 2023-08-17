package com.zei.JwtEx.security.config;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class JWTService {

	private static String secretKey = "zei"; //시그니처 맨뒤에 붙는 키
	
	//토큰생성
	public static String createToken(String username) {
	
		//알고리즘생성
		Algorithm alg = Algorithm.HMAC256(secretKey);
		
		//만료시간 생성
		long expire = System.currentTimeMillis() + 3600000; //currentTimeMillis()이 long반환
		
		//토큰 생성
		Builder builder = JWT.create().withSubject(username) //매개변수로 받은 username
										.withIssuedAt(new Date()) //발행일
										.withExpiresAt(new Date(expire)) //만료시간
										.withIssuer("zei") //발행자   -- 여기까지가 등록된 클레임들이고 나머지는 withClaim으로 추가해주면 된다.
										.withClaim("admin", "공개클레임"); // +공개클레임
		return builder.sign(alg);
		
	}
	
	
	//토큰의 유효성확인
	public static boolean validateToken(String token) throws JWTVerificationException{
		Algorithm alg = Algorithm.HMAC256(secretKey);
		JWTVerifier verifier = JWT.require(alg).build(); //token을 검증할 객체
	
		verifier.verify(token); //토큰검사 - 만료기간 or 토큰위조가 발생하면 throws 처리된다.
	
		return true; //검증성공시 true, 실패시 false;
	}
	
	
	
}