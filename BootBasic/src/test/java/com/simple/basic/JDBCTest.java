package com.simple.basic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.simple.basic.command.BuilderVO;
import com.simple.basic.command.BuilderVO.Builder;
import com.simple.basic.command.BuilderVO2;

@SpringBootTest //스프링 부트 테스트 클래스
public class JDBCTest {
	
	//빌더 패턴의 확인
	@Test
	public void testCode01() {
		
		//new 키워드가 아니고 6번의 스태틱 키워드를 호출해야한다.
//		Builder b = BuilderVO.builder(); //근데 이렇게 하면 
//		b = b.age(10);
//		b = b.name("mong");
//		BuilderVO vo = b.build();
		
		
		//vo에 setter가 없기 때문에 객체불변성을 보장한다. 값이 굉장히 직관적.
		BuilderVO vo = BuilderVO.builder().age(10).name("mong").build();
		System.out.println(vo.toString());
				
		
		BuilderVO2 vo2 = BuilderVO2.builder().name("moong").age(10).build();
		System.out.println(vo2.toString());
	}

}
