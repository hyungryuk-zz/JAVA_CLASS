package chap06.sec11.exam01_final;

public class Person {
	//����� ���ÿ� ���� �ʱ�ȭ ��Ŵ
	final String nation = "Korea";
	//����� ���� �س��� �ʱ�ȭ�� �����ڿ��� �� ���� ����
	final String ssn;
	String name;
	
	public Person(String ssn, String name) {
		this.ssn = ssn;
		this.name = name;
	}
}

