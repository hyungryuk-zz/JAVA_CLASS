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
 * 1) DB vender가 제공하는 Driver를 이용하여 DB와 연결함<BR>
 * 2) JDBC를 구성하는 java.sql package와 SQL을 사용하여 DB의 data를 가져온다.<BR>
 */
public class Database {

	public Connection conn;

	/**
	 * <PRE>
	 * 1) JDBC 드라이버를 load함
	 * < example >
	 *	try {
	 *   	Class.forName("oracle.jdbc.driver.OracleDriver");
	 *	}catch(ClassNotFoundException e) {
	 *		e.printStackTrace();
	 *   }
	 * 2) load된 드라이버에 server name을 가지고 DB에 connect.
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
		// Connection 객체생성
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
	 * util method : seoul ===> 'seoul' 로 변환시켜줌
	 */
	public String makeSQLString(String s) {
		String quotedString = new String("'" + s + "'");
		return quotedString;
	}

	/**
	 * <PRE>
	 * 구현하세요
	 * ssn이 존재하는지 알아보는 method
	 * 존재하면 true / 없으면 false
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
	 * 구현하세요
	 * customer table에 신규고객을 새로 입력함
	 * 1)똑같은 ssn이 존재하는지 검사 - 존재하면 exception
	 * 2)없으면 table에 등록
	 * < available method >
	 * ssnExists()
	 * PreparedStatement를 생성해 주는  prepareStatement(query)
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
	 * customer table에서 기존 고객을 지움
	 * 1)똑같은 고객(ssn)이 존재하는지 검사 - 존재하지 않으면 exception
	 * 2)존재하면 먼저 shares table에 사용된 foreign key(ssn)을 지울것
	 * 3)다음 customer table의 primary key(ssn)을 지울것
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
	 * customer table에서 ssn을 기준으로 다른 정보(name,address)를 변경(ssn은 변경되지 않음)
	 * 1)customer table의 ssn이 존재하는지 check - 없으면 exception 발생
	 * 2)ssn이 존재하면 다른정보(name,address)를 변경
	 * < available method >
	 * ssnExists() 
	 * PreparedStatement를 생성해 주는  prepareStatement(query)
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
	 * Customer table에서 한명의 ssn에 해당되는 record를 가져오고
	 * Shares table에서 해당 ssn의 보유주식을 가져와서 CustomerRec에 저장함
	 * 1)해당 ssn의 존재 유무 확인 - 없으면 exception
	 * 2)있으면 ssn의 record를 가져올것(ssn,name,address)
	 * 3)CustomerRec 객체 생성해서 위정보(ssn,name,address)를 멤버 필드에 저장
	 * 4)getPortfolio(ssn)를 사용하여 CustemerRec의 portfolio 멤버 필드에 저장
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
	 * customer table의 모든 고객 정보와 
	 * 해당 고객(ssn)의 shares table의 모든 보유주식 정보를 가져와서 ArrayList에 저장
	 * 1) ArrayList<CustomerRec> 객체에 각각의ssn,name,address 정보를 갖는 CustomerRec 객체를
	 *    생성하여 입력
	 * 2) ArrayList<CustomerRec>의 객체에 있는 각각의 CustomerRec 객체에 portfolio정보를 저장
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
	 * 해당 ssn 고객의 총 보유주식정보를 shares table에서 가져와서 Vector에 입력한다.
	 * 1) 해당 ssn고객 존재 유무 check - 없으면 exception
	 * 2) Vector 객체 생성 하고  shares table에서 해당 ssn 고객 정보 가져옴
	 * 3) 가져온 record 갯수만큼 Vector에 ssn,symbol,quantity 정보를 가진SharesRec 객체를 add할것
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
	 * Stock table에 있는 모든 record정보를 가져옴
	 * 3) HashSet<StockRec>의 객체에 symbol,price정보를 갖는 StockRec 객체를 저장할것
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
	 * -- 특정 symbol의 보유주식 일부분을 팔때 --
	 * Shares table의 특정 ssn의 특정 symbol의 수량(quantity)정보를 가져와서 팔려는
	   수량과 연산후, 결과에 따라 shares table의 정보를 변경함 
	 * 1) ssn존재 유무 확인 - 없으면 exception
	 * 2) 특정 ssn과 특정 symbol의 수량정보를 shares table에서 가져옴
	 * 3) 팔려는 수량과 연산(가감)할것
	 * 4) 다음 세가지 경우를 고려할것
	      보유수량 > 팔려는 수량 : 잔여수량을 shares table에 입력
		  보유수량 = 팔려는 수량 : shares table의 특정ssn과 특정 symbol정보를 삭제
		  보유수량 < 팔려는 수량 : 부적절한 거래(exception 발생)
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
	 * -- 특정 symbol의 보유주식 모두를 팔때 --
	 * 특정 ssn과 특정 symbol의 record를 가져오고 있으면 해당 record를 지운다.
	 * 1) 해당 ssn의 존재 유무 check - 없으면 exception
	 * 2) 특정 ssn과 특정 symbol의 record를 가져온다
	 * 3) 해당 record가 있으면 지운다 / 없으면 부적절한 거래(exception 발생)
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
	 * 구매하려는 특정 주식과 수량을 shares table에 새로 첨가하거나 update한다
	 * 1) ssn존재 유무check - 없으면 exception
	 * 2) 특정 ssn과 symbol의 수량(quantity)정보를 shares table에서 가져온다
	 * 3) record에서 가져올 정보가 있으면 연산후 shares table update할것
	 * 4) record에서 가져올 정보가 없으면 addShares() call
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
	 * 특정 symbol의 가격(price)을 update 함
	 * 1) 특정 symbol의 가격(price)을 아래 메소드가 호출될때마다 update함
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
	 * 특정 symbol의 가격(price)을 가져옴
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
	 * finalize 메서드 재정의 한다. finalize 메서드가 gc 되기 전에 호출됨으로 connection객체의 close() 메서드를
	 * 호출하여 connetion 객체를 release 해야 한다.
	 */

}