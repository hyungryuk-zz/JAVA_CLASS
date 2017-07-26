package workshop.broker.vo;
import java.io.Serializable;

public class StockRec implements Serializable {

	private String symbol;
	private float price;

	// StockRec constructor - create a instance of a stock
	// from the queried data
	public StockRec (String symbol, float price) {
		this.symbol = symbol;
		this.price = price;
	}
	
	// StockRec constructor - create a instance of a stock
	// with no data
	public StockRec () {
		symbol = "";
		price = 0.0f;
	}

	// Methods to return the private values of this object
	public float getPrice () {
		return price;
	}

	public String getSymbol () {
		return symbol;
	}

	public void setPrice (float newPrice) {
		price = newPrice;
	}

	@Override
	public String toString() {
		return "StockRec [symbol=" + symbol + ", price=" + price + "]";
	}
	
}
