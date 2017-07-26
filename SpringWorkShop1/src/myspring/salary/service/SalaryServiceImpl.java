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
		
		int salary=dao.getSalary(snum);  	 //  연봉을 구해온다.
		int mSalary=salary/12;	// 월급여를 구한다.
		detail.setSnum(snum);		// 번호 저장
		detail.setMonth(month);	// 지급월 저장
		detail.setTotal(mSalary);		// 월급여 저장
		int ins=medicalIns(mSalary);	//의료보험료 계산 호출
		detail.setMedicalIns(ins);
		int pens=pension(mSalary);	// 국민 연금 계산 호출
		detail.setPension(pens);
		detail.setPayment(mSalary-ins-pens);	 // 월급여에서  세금 공제
		dao.saveSalaryDetail(detail);	// DB 저장 요청
		return detail;
	}
	
	
	private int medicalIns(int salary){	// 의료보험 계산 메서드
		return (int)(salary*0.0589/2);
	}
	
	private int pension(int salary){	// 국민연금 계산 메서드
		return (int) (salary*0.09/2);
	}

}
