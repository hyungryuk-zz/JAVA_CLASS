package workshop.broker.exception;

public class DuplicateIDException extends Exception {
	public DuplicateIDException(String errMsg) {
		super(errMsg);
	}
	public DuplicateIDException() {
		this("�ش� ���� �̹� �����մϴ�.");
	}
}
