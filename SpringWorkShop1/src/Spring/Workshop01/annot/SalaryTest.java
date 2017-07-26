package Spring.Workshop01.annot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myspring.salary.service.SalaryServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)	//스프링이 Junit을 확장해서 실행한다는 의미이다
@ContextConfiguration(locations="classpath:config/applicationContext.xml")	//bean을 설정한 xml파일명을 써준다.
public class SalaryTest {
	
	@Autowired
	SalaryServiceImpl salSer;
	
	@Test
	public void test() {
		System.out.println(salSer.computeSalary(1001, 11));
	}
}
