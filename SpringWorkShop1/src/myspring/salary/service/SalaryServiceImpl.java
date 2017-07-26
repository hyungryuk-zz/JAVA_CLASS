package myspring.salary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Spring.Workshop01.annot.SalaryDAO;
import Spring.Workshop01.annot.SalaryService;
import Spring.Workshop01.annot.SalaryVO;
import myspring.salary.dao.SalaryDAOImpl;

@Component
public class SalaryServiceImpl implements SalaryService {
	
	
	@Autowired
	private SalaryDAO  dao;
	
	@Autowired
	SalaryVO detail;
	
	public SalaryVO computeSalary(int snum, int month){
		
		int salary=dao.getSalary(snum);  	 //  ������ ���ؿ´�.
		int mSalary=salary/12;	// ���޿��� ���Ѵ�.
		detail.setSnum(snum);		// ��ȣ ����
		detail.setMonth(month);	// ���޿� ����
		detail.setTotal(mSalary);		// ���޿� ����
		int ins=medicalIns(mSalary);	//�ǷẸ��� ��� ȣ��
		detail.setMedicalIns(ins);
		int pens=pension(mSalary);	// ���� ���� ��� ȣ��
		detail.setPension(pens);
		detail.setPayment(mSalary-ins-pens);	 // ���޿�����  ���� ����
		dao.saveSalaryDetail(detail);	// DB ���� ��û
		return detail;
	}
	
	
	private int medicalIns(int salary){	// �ǷẸ�� ��� �޼���
		return (int)(salary*0.0589/2);
	}
	
	private int pension(int salary){	// ���ο��� ��� �޼���
		return (int) (salary*0.09/2);
	}

}
