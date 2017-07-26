package chap10.sec07_user_define_exception;

public class BalanceInsufficientException extends Exception {
	public BalanceInsufficientException() { }
	public BalanceInsufficientException(String message) {
		super(message);
	}
}

