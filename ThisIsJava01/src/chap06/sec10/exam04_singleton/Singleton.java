package chap06.sec10.exam04_singleton;

public class Singleton {
	//���ο��� ��ü�� ����
	private static Singleton singleton = new Singleton();
	
	//�ܺ�Ŭ�������� ��ü�� �������� ���ϵ��� �������� ���������ڴ� private���� ��
	private Singleton() {}
	
	//���ο��� ������ ��ü�� ���۷����� return ���ִ� �޼���
	static Singleton getInstance() {
		return singleton;
	}
}

