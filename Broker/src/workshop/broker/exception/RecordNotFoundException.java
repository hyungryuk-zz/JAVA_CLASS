package workshop.broker.exception;

public class RecordNotFoundException extends Exception {
	public RecordNotFoundException(String errMsg) {
		super(errMsg);
	}
	
	public RecordNotFoundException(){
		System.err.println("해당 데이터가 존재하지 않습니다.");
	}
	
}
