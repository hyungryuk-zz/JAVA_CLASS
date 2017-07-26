package workshop.broker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import workshop.broker.exception.DuplicateIDException;
import workshop.broker.exception.InvalidTransactionException;
import workshop.broker.exception.RecordNotFoundException;
import workshop.broker.vo.CustomerRec;
import workshop.broker.vo.SharesRec;
import workshop.broker.vo.StockRec;

/**
 * 1) DB vender�� �����ϴ� Driver�� �̿��Ͽ� DB�� ������<BR>
 * 2) JDBC�� �����ϴ� java.sql package�� SQL�� ����Ͽ� DB�� data�� �����´�.<BR>
 */
public class Database {

	public Connection conn;

	/**
	 * <PRE>
	 * 1) JDBC ����̹��� load��
	 * < example >
	 *	try {
	 *   	Class.forName("oracle.jdbc.driver.OracleDriver");
	 *	}catch(ClassNotFoundException e) {
	 *		e.printStackTrace();
	 *   }
	 * 2) load�� ����̹��� server name�� ������ DB�� connect.
	 * < example >
	 *	conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORA81","ascjp","asung");
	 * </PRE>
	 */
	// constructor...........................
	public Database() throws SQLException {
		this("127.0.0.1");
	}

	public Database(String serverName) throws SQLException {
		// Load the Oracle JDBC Driver

		try {
			// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// Class.forName("org.gjt.mm.mysql.Driver");
			Class.forName("oracle.jdbc.OracleDriver");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// **** mysql
		// String url =
		// "jdbc:mysql://"+serverName+":3306/sl300?useUnicode=true&characterEncoding=euc-kr"
		// **** oracle
		String url = "jdbc:oracle:thin:@" + serverName + ":1521:xe";
		String user = "scott";
		String pass = "tiger";
		// Connection ��ü����
		conn = DriverManager.getConnection(url, user, pass);
		System.out.println("after connection.....");
	}

	/**
	 * Closes the connection with the database
	 */
	public void close() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
		}
	}

	public void close(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * util method : seoul ===> 'seoul' �� ��ȯ������
	 */
	public String makeSQLString(String s) {
		String quotedString = new String("'" + s + "'");
		return quotedString;
	}

	/**
	 * <PRE>
	 * �����ϼ���
	 * ssn�� �����ϴ��� �˾ƺ��� method
	 * �����ϸ� true / ������ false
	 * <available method>
	 * executeQuery()
	 * next()
	 * </PRE>
	 */
	public boolean ssnExists(String ssn) {
		String sql = "select ssn from customer where ssn=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ssn);
			rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
		}

	}

	/**
	 * <PRE>
	 * �����ϼ���
	 * customer table�� �ű԰��� ���� �Է���
	 * 1)�Ȱ��� ssn�� �����ϴ��� �˻� - �����ϸ� exception
	 * 2)������ table�� ���
	 * < available method >
	 * ssnExists()
	 * PreparedStatement�� ������ �ִ�  prepareStatement(query)
	 * setString(1,value)
	 * executeUpdate()
	 * </PRE>
	 */
	public void addCustomer(CustomerRec customer) throws DuplicateIDException {
		if (ssnExists(customer.getSSN())) {
			throw new DuplicateIDException();
		} else {
			String sql = "insert into customer values(?,?,?)";
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, customer.getSSN());
				pstmt.setString(2, customer.getName());
				pstmt.setString(3, customer.getAddr());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		}
	}

	/**
	 * <PRE>
	 * customer table���� ���� ���� ����
	 * 1)�Ȱ��� ��(ssn)�� �����ϴ��� �˻� - �������� ������ exception
	 * 2)�����ϸ� ���� shares table�� ���� foreign key(ssn)�� �����
	 * 3)���� customer table�� primary key(ssn)�� �����
	 * < available method >
	 * ssnExists() / makeSQLString()
	 * executeUpdate()
	 * </PRE>
	 */
	public void deleteCustomer(String ssn) throws RecordNotFoundException {
		if (!ssnExists(ssn)) {
			throw new RecordNotFoundException();
		} else {
			String sql1 = "delete from shares where ssn = ?";
			String sql2 = "delete from customer where ssn = ?";

			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			try {
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, ssn);
				pstmt1.executeUpdate();
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, ssn);
				pstmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt1);
				close(pstmt2);
			}
		}
	}

	/**
	 * <PRE>
	 * customer table���� ssn�� �������� �ٸ� ����(name,address)�� ����(ssn�� ������� ����)
	 * 1)customer table�� ssn�� �����ϴ��� check - ������ exception �߻�
	 * 2)ssn�� �����ϸ� �ٸ�����(name,address)�� ����
	 * < available method >
	 * ssnExists() 
	 * PreparedStatement�� ������ �ִ�  prepareStatement(query)
	 * setString(1,value)
	 * excuteUpdate()
	 * </PRE>
	 */
	public void updateCustomer(CustomerRec customer) throws RecordNotFoundException {
		if (!ssnExists(customer.getSSN())) {
			throw new RecordNotFoundException();
		} else {
			String sql1 = "update customer set cust_name=? where ssn=?";
			String sql2 = "update customer set address=? where ssn=?";

			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			try {
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, customer.getName());
				pstmt1.setString(2, customer.getSSN());
				pstmt1.executeUpdate();
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, customer.getAddr());
				pstmt2.setString(2, customer.getSSN());
				pstmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt1);
				close(pstmt2);
			}
		}
	}

	/**
	 * <PRE>
	 * Customer table���� �Ѹ��� ssn�� �ش�Ǵ� record�� ��������
	 * Shares table���� �ش� ssn�� �����ֽ��� �����ͼ� CustomerRec�� ������
	 * 1)�ش� ssn�� ���� ���� Ȯ�� - ������ exception
	 * 2)������ ssn�� record�� �����ð�(ssn,name,address)
	 * 3)CustomerRec ��ü �����ؼ� ������(ssn,name,address)�� ��� �ʵ忡 ����
	 * 4)getPortfolio(ssn)�� ����Ͽ� CustemerRec�� portfolio ��� �ʵ忡 ����
	 * < available method >
	 * ssnExists() / makeSQLString()
	 * exucuteQuery()
	 * next()
	 * getString()
	 * getPortfolio()
	 * setPortfolio()
	 * </PRE>
	 */
	public CustomerRec getCustomer(String ssn) throws RecordNotFoundException {
		if (!ssnExists(ssn)) {
			throw new RecordNotFoundException();
		} else {
			String sql = "select * from customer where ssn=?";
			CustomerRec cusRec = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ssn);
				rs = pstmt.executeQuery();
				rs.next();
				cusRec = new CustomerRec(rs.getString("SSN"), rs.getString("CUST_NAME"), rs.getString("ADDRESS"));
				cusRec.setPortfolio(getPortfolio(cusRec.getSSN()));

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} finally {
				close(pstmt);
				return cusRec;
			}
		}
	}

	/**
	 * <PRE>
	 * customer table�� ��� �� ������ 
	 * �ش� ��(ssn)�� shares table�� ��� �����ֽ� ������ �����ͼ� ArrayList�� ����
	 * 1) ArrayList<CustomerRec> ��ü�� ������ssn,name,address ������ ���� CustomerRec ��ü��
	 *    �����Ͽ� �Է�
	 * 2) ArrayList<CustomerRec>�� ��ü�� �ִ� ������ CustomerRec ��ü�� portfolio������ ����
	 * < available method > 
	 * executeQuery()
	 * next() / getString()
	 * getPortfolio() 
	 * setPortfolio()
	 * </PRE>
	 */
	public ArrayList<CustomerRec> getAllCustomers() {
		
		String sql = "select * from customer";
		
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<CustomerRec> resCus= new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			CustomerRec cr = null;
			while(rs.next()) {
				cr = new CustomerRec(rs.getString("SSN"),rs.getString("CUST_NAME"),rs.getString("ADDRESS"));
				cr.setPortfolio(getPortfolio(cr.getSSN()));
				resCus.add(cr);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			return resCus;
		}				
		

	}

	/**
	 * <PRE>
	 * �ش� ssn ���� �� �����ֽ������� shares table���� �����ͼ� Vector�� �Է��Ѵ�.
	 * 1) �ش� ssn�� ���� ���� check - ������ exception
	 * 2) Vector ��ü ���� �ϰ�  shares table���� �ش� ssn �� ���� ������
	 * 3) ������ record ������ŭ Vector�� ssn,symbol,quantity ������ ����SharesRec ��ü�� add�Ұ�
	 * < available method >
	 * ssnExists() / executeQuery() / next()
	 * getString() / getInt()
	 * addElement()
	 * </PRE>
	 */
	public Vector<SharesRec> getPortfolio(String ssn) throws RecordNotFoundException {
		if (!ssnExists(ssn)) {
			throw new RecordNotFoundException();
		} else {
			Vector<SharesRec> resSharesRec = new Vector<>();
			String sql = "select * from shares where ssn = ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ssn);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					resSharesRec.add(new SharesRec(rs.getString("SSN"), rs.getString("SYMBOL"), rs.getInt("QUANTITY")));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
				return resSharesRec;
			}
		}
	}

	/**
	 * <PRE>
	 * Stock table�� �ִ� ��� record������ ������
	 * 3) HashSet<StockRec>�� ��ü�� symbol,price������ ���� StockRec ��ü�� �����Ұ�
	 * < available method >
	 * executeQuery() / next() 
	 * getString() / getFloat()
	 * </PRE>
	 */
	public Set<StockRec> getAllStocks() {
		Set<StockRec> resSet = new HashSet<>();
		String sql = "select * from stock";

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resSet.add(new StockRec(rs.getString("SYMBOL"), rs.getFloat("PRICE")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(stmt);
			return resSet;
		}
	}

	/**
	 * <PRE>
	 * -- Ư�� symbol�� �����ֽ� �Ϻκ��� �ȶ� --
	 * Shares table�� Ư�� ssn�� Ư�� symbol�� ����(quantity)������ �����ͼ� �ȷ���
	   ������ ������, ����� ���� shares table�� ������ ������ 
	 * 1) ssn���� ���� Ȯ�� - ������ exception
	 * 2) Ư�� ssn�� Ư�� symbol�� ���������� shares table���� ������
	 * 3) �ȷ��� ������ ����(����)�Ұ�
	 * 4) ���� ������ ��츦 ����Ұ�
	      �������� > �ȷ��� ���� : �ܿ������� shares table�� �Է�
		  �������� = �ȷ��� ���� : shares table�� Ư��ssn�� Ư�� symbol������ ����
		  �������� < �ȷ��� ���� : �������� �ŷ�(exception �߻�)
	 * < available method >
	 * ssnExists() / makeSQLString() / next() / executeQuery()
	 * getInt()
	 * </PRE>
	 */
	public void sellShares(SharesRec shares) throws RecordNotFoundException, InvalidTransactionException {
		if (!ssnExists(shares.getSSN())) {
			throw new RecordNotFoundException();
		} else {
			String sql = "select quantity from shares where ssn = ? and symbol = ?";
			String sql1 = "update shares set quantity=? where ssn=? and symbol = ?";
			String sql2 = "delete from shares where ssn=? and symbol = ?";
			PreparedStatement pstmt = null;
			PreparedStatement pstmt1 = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shares.getSSN());
				pstmt.setString(2, shares.getSymbol());
				rs = pstmt.executeQuery();
				rs.next();
				int qu = rs.getInt("QUANTITY");
				if (qu > shares.getQuantity()) {
					pstmt1 = conn.prepareStatement(sql1);
					pstmt1.setInt(1, qu - shares.getQuantity());
					pstmt1.setString(2, shares.getSSN());
					pstmt1.setString(3, shares.getSymbol());
					pstmt1.executeUpdate();
				} else if (qu == rs.getInt("QUANTITY")) {
					pstmt1 = conn.prepareStatement(sql2);
					pstmt1.setString(1, shares.getSSN());
					pstmt1.setString(2, shares.getSymbol());
					pstmt1.executeUpdate();
				} else {
					throw new InvalidTransactionException();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(pstmt1);
			}
		}
	}

	/**
	 * <PRE>
	 * -- Ư�� symbol�� �����ֽ� ��θ� �ȶ� --
	 * Ư�� ssn�� Ư�� symbol�� record�� �������� ������ �ش� record�� �����.
	 * 1) �ش� ssn�� ���� ���� check - ������ exception
	 * 2) Ư�� ssn�� Ư�� symbol�� record�� �����´�
	 * 3) �ش� record�� ������ ����� / ������ �������� �ŷ�(exception �߻�)
	 * < available method >
	 * ssnExists() / makeSQLString() / executeQuery() / next()
	 * </PRE>*/
	public void sellShares(String ssn, String symbol) throws RecordNotFoundException, InvalidTransactionException {
		if (!ssnExists(ssn)) {
			throw new RecordNotFoundException();
		} else {
			String sql = "delete from shares where ssn=? and symbol = ?";
			String sql1 = "select count(*) from shares where ssn=? and symbol = ?";
			PreparedStatement pstmt = null;
			PreparedStatement pstmt1 = null;
			ResultSet rs = null;
			try {
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, ssn);
				pstmt1.setString(2, symbol);
				rs = pstmt1.executeQuery();
				rs.next();
				if(rs.getInt("count(*)")==0) {
					throw new InvalidTransactionException();
				}
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ssn);
				pstmt.setString(2, symbol);
				pstmt.executeUpdate();
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new InvalidTransactionException();
			} finally {
				close(pstmt);
				close(pstmt1);
			}
		}
	}

	/**
	 * <PRE>
	 * �����Ϸ��� Ư�� �ֽİ� ������ shares table�� ���� ÷���ϰų� update�Ѵ�
	 * 1) ssn���� ����check - ������ exception
	 * 2) Ư�� ssn�� symbol�� ����(quantity)������ shares table���� �����´�
	 * 3) record���� ������ ������ ������ ������ shares table update�Ұ�
	 * 4) record���� ������ ������ ������ addShares() call
	 * < available method >
	 * ssnExists() / next() / executeQuery() / getInt()
	 * addShares() / executeUpdate()
	 * </PRE>
	 */
	public void buyShares(SharesRec shares) throws RecordNotFoundException {
		if (!ssnExists(shares.getSSN())) {
			throw new RecordNotFoundException();
		} else {
			String sql = "select count(*) from shares where ssn = ? and symbol = ?";
			String sql1 = "update shares set quantity=? where ssn=? and symbol = ?";
			String sql2 = "insert into shares values (?,?,?)";
			PreparedStatement pstmt = null;
			PreparedStatement pstmt1 = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shares.getSSN());
				pstmt.setString(2, shares.getSymbol());
				rs = pstmt.executeQuery();
				rs.next();
				if (rs.getInt("count(*)") == 0) {
					pstmt1 = conn.prepareStatement(sql2);
					pstmt1.setString(1, shares.getSSN());
					pstmt1.setString(2, shares.getSymbol());
					pstmt1.setInt(3, shares.getQuantity());
					pstmt1.executeUpdate();
				} else {
					pstmt1 = conn.prepareStatement(sql1);
					pstmt1.setInt(1, shares.getQuantity());
					pstmt1.setString(2, shares.getSSN());
					pstmt1.setString(3, shares.getSymbol());
					pstmt1.executeUpdate();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(pstmt1);
			}
		}
	}

	/**
	 * <PRE>
	 * Ư�� symbol�� ����(price)�� update ��
	 * 1) Ư�� symbol�� ����(price)�� �Ʒ� �޼ҵ尡 ȣ��ɶ����� update��
	 * < available method >
	 * executeUpdate() / makeSQLString()
	 * </PRE>
	 */
	public void updateStockPrice(String symbol, float price) {

		String sql = "update stock set price=? where symbol=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, price);
			pstmt.setString(2, symbol);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}	

	/**
	 * <PRE>
	 * Ư�� symbol�� ����(price)�� ������
	 * < available method >
	 * executeQuery() / next() / makeSQLString() / getFloat()
	 * </PRE>
	 */
	public float getStockPrice(String symbol) {
			String sql = "select price from stock where symbol = ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			float pri=0.0f;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, symbol);
				rs = pstmt.executeQuery();
				rs.next();
				pri = rs.getFloat("PRICE");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
				return pri;
			}
	}

	/**
	 * finalize �޼��� ������ �Ѵ�. finalize �޼��尡 gc �Ǳ� ���� ȣ������� connection��ü�� close() �޼��带
	 * ȣ���Ͽ� connetion ��ü�� release �ؾ� �Ѵ�.
	 */

}