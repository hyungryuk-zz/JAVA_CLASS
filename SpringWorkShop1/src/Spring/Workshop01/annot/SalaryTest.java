package Spring.Workshop01.annot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myspring.salary.service.SalaryServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)	//�������� Junit�� Ȯ���ؼ� �����Ѵٴ� �ǹ��̴�
@ContextConfiguration(locations="classpath:config/applicationContext.xml")	//bean�� ������ xml���ϸ��� ���ش�.
public class SalaryTest {
	
	@Autowired
	SalaryServiceImpl salSer;
	
	@Test
	public void test() {
		System.out.println(salSer.computeSalary(1001, 11));
	}
}
