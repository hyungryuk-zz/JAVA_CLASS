package workshop.broker.vo;
import java.io.Serializable;
import java.util.*;

public class CustomerRec implements Serializable {

	private String ssn;
	private String name;
	private String addr;
	private Vector<SharesRec> portfolio;

	// Constructors
	public CustomerRec (String ssn, String name, String addr, 
		Vector<SharesRec> portfolio) {
		this.ssn = ssn;
		this.name = name;
		this.addr = addr;
		this.portfolio = portfolio;
	}

	public CustomerRec (String ssn, String name, String addr) {
		this (ssn, name, addr, null);
	}

	public CustomerRec (String ssn) {
		this (ssn, null, null, null);
	}

	public CustomerRec () {
		this (null, null, null, null);
	}

	// Accessor methods

	public String getSSN () {
		return ssn;
	}

	public String getName () {
		return name;
	}

	public String getAddr () {
		return addr;
	}

	// Get and return the portfolio for this customer
	// This method will return the Vector object that contains the portfolio
	public Vector<SharesRec> getPortfolio () {
		return portfolio;
	}

	// Mutator methods - note you cannot change the ssn

	public void setName (String newName) {
		name = newName;
	}

	public void setAddr (String newAddr) {
		addr = newAddr;
	}

	public void setPortfolio (Vector<SharesRec> newPortfolio) {
		portfolio = newPortfolio;
	}

	@Override
	public String toString() {
		return "CustomerRec [ssn=" + ssn + ", name=" + name + ", addr=" + addr + ", portfolio=" + portfolio + "]";
	}
	
}
