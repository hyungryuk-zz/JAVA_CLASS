package chap06.sec10.exam04_singleton;

public class Singleton {
	//내부에서 객체를 생성
	private static Singleton singleton = new Singleton();
	
	//외부클래스에서 객체를 생성하지 못하도록 생성자의 접근제한자는 private으로 함
	private Singleton() {}
	
	//내부에서 생성된 객체의 레퍼런스를 return 해주는 메서드
	static Singleton getInstance() {
		return singleton;
	}
}

