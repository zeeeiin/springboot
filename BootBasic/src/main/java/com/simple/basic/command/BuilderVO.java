package com.simple.basic.command;

public class BuilderVO {

	//빌더패턴을 사용할 수 있도록 설계
	//1. 멤버변수
	private String name;
	private int age;
	//기존형태라면 생성자,get,set을 만들었을 것.
	
	
	//8. 생성자 생성
	//바깥에서 바로 생성할 수 없게 private로 선언
	private BuilderVO (Builder builder) { //메소드 매개변수는 빌더타입이어야한다.
		this.name = builder.name; //
		this.age = builder.age; //
	}
	
	//6. 
	public static Builder builder() {
		return new Builder();
	}
	
	
	@Override
	public String toString() {
		return "BuilderVO [name=" + name + ", age=" + age + "]";
	}


	//2. 내부클래스 - 클래스안에 또다른 클래스
	public static class Builder {
		//3. 내부클래스 멤버변수를 생성
		private String name;
		private int age;
		
		//4. 내부클래스 생성자를 제한
		private Builder() {
			
		}
		
		//5. 세터메소드 생성 - 나 자신을 다시 반환하는 모형
		public Builder name (String name) {
			this.name = name;
			return this;
		}
		public Builder age(int age) {
			this.age = age;
			return this;
		}
		
		//7. build 메소드를 실행하면 멤버변수를 외부 클래스에 저장    //외부 생성자를 이용해서 setter로 저장된 값을 외부클래스에 저장
		//바깥에 있는 BuilderVO를 반환
		public BuilderVO build() {
			//바깥에서 생성자 모형을 만들어줘야함.
			BuilderVO vo = new BuilderVO(this); //this = 나 자신		
			return vo; 
		}
	
	}
	
	
}
