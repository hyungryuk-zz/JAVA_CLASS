package myspring.salary.dao;

import java.util.Random;

import Spring.Workshop01.annot.SalaryDAO;
import Spring.Workshop01.annot.SalaryVO;

public class SalaryDAOImpl implements SalaryDAO {

	public int getSalary(int snum) {
		return (new Random().nextInt(10) + 2) * 10000000;
	}

	public void saveSalaryDetail(SalaryVO detail) {
		System.out.println("저장완료:" + detail);
	}

}
