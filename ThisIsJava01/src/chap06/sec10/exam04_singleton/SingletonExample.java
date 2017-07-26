package chap06.sec10.exam04_singleton;

import java.util.Calendar;

public class SingletonExample {
	public static void main(String[] args) {
		
//		Singleton obj1 = new Singleton();  //������ ����
//		Singleton obj2 = new Singleton();  //������ ����
		
		
		Singleton obj1 = Singleton.getInstance();
		Singleton obj2 = Singleton.getInstance();
		
		if(obj1 == obj2) {
			System.out.println("���� Singleton ��ü �Դϴ�.");
		} else {
			System.out.println("�ٸ� Singleton ��ü �Դϴ�.");
		}
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		System.out.println(cal1 == cal2);
		
	}
}
