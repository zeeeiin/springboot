package com.simple.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisTest {

	//testmapper interface 선언
	@Autowired
	TestMapper testMapper;
	
	@Test
	public void testCode01() {
		System.out.println(testMapper.getTime());
	}
}
