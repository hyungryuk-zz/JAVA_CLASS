package workshop.broker.exception;

public class RecordNotFoundException extends Exception {
	public RecordNotFoundException(String errMsg) {
		super(errMsg);
	}
	
	public RecordNotFoundException(){
		System.err.println("�ش� �����Ͱ� �������� �ʽ��ϴ�.");
	}
	
}
