package chap06.sec11.exam01_final;

public class Person {
	//선언과 동시에 값을 초기화 시킴
	final String nation = "Korea";
	//상수로 선언만 해놓고 초기화는 생성자에서 할 수도 있음
	final String ssn;
	String name;
	
	public Person(String ssn, String name) {
		this.ssn = ssn;
		this.name = name;
	}
}

