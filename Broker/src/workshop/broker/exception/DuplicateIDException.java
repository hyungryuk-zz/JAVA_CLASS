package workshop.broker.exception;

public class DuplicateIDException extends Exception {
	public DuplicateIDException(String errMsg) {
		super(errMsg);
	}
	public DuplicateIDException() {
		this("해당 고객은 이미 존재합니다.");
	}
}
