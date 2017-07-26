package workshop.broker.vo;
import java.io.Serializable;

public class SharesRec implements Serializable {

	private String ssn;
	private String symbol;
	private int quantity;

	// SharesRec constructor
	public SharesRec (String ssn, String symbol, int quantity) {
		this.ssn = ssn;
		this.symbol = symbol;
		this.quantity = quantity;
	}

	public SharesRec (String ssn) {
		this (ssn, "", 0);
	}

	public SharesRec () {
		this ("", "", 0);
	}

	// Accessor methods
	public String getSSN () {
		return ssn;
	}

	public String getSymbol () {
		return symbol;
	}

	public int getQuantity () {
		return quantity;
	}

	// Mutator methods - note no setSSN method

	public void setSymbol (String newSymbol) {
		symbol = newSymbol;
	}

	public void setQuantity (int newQuantity) {
		quantity = newQuantity;
	}

	@Override
	public String toString() {
		return "SharesRec [ssn=" + ssn + ", symbol=" + symbol + ", quantity=" + quantity + "]";
	}
	
	
}
