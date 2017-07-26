package workshop.broker.exception;
public class InvalidTransactionException extends Exception {
	public InvalidTransactionException (String msg) {
		super(msg);
	}
	public InvalidTransactionException() {
		this("This is not transaction");
	}
}