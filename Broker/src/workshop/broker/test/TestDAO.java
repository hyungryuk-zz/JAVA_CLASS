package workshop.broker.test;

import java.sql.SQLException;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import workshop.broker.dao.Database;
import workshop.broker.exception.DuplicateIDException;
import workshop.broker.exception.InvalidTransactionException;
import workshop.broker.exception.RecordNotFoundException;
import workshop.broker.vo.CustomerRec;
import workshop.broker.vo.SharesRec;

public class TestDAO {

	Database dao;
	
	@Before
	public void init() {
		try {
			dao = new Database();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getStockPrice() {
		System.out.println(dao.getStockPrice("SUNW"));
		Assert.assertEquals(68.75f, dao.getStockPrice("SUNW"));
	}
	
//	@Test
//	public void updateStockPrice() {
//		dao.updateStockPrice("CyAs", 100.0f);
//	}
//	
//	@Test
//	public void buyShares() {
//		try {
//			dao.buyShares(new SharesRec("111-112","JSVco",100));
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void sellShares() {
//		try {
//			dao.sellShares("111-112","JSVco");
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidTransactionException e) {
//			// TODO Auto-generated catch block
//			System.out.println("haha");
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void sellShares() {
		try {
			dao.sellShares(new SharesRec("111-112","JDK",100));
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	
}
