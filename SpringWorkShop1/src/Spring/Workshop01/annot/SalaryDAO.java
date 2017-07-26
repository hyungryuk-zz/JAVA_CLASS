package Spring.Workshop01.annot;

import org.springframework.stereotype.Component;

@Component
public interface SalaryDAO {
	public int getSalary(int snum);
	public void saveSalaryDetail(SalaryVO detail);
}
