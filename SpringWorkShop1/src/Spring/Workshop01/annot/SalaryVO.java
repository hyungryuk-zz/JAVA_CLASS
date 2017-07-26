package Spring.Workshop01.annot;

public class SalaryVO {

	private int snum;
	private int month;
	private int total;
	private int medicalIns;
	private int pension;
	private int payment;

	public void setSnum(int snum) {
		this.snum = snum;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setMedicalIns(int medicalIns) {
		this.medicalIns = medicalIns;
	}
	public void setPension(int pension) {
		this.pension = pension;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "SalaryVO [snum=" + snum + ", month=" + month + ", total=" + total + ", medicalIns=" + medicalIns
				+ ", pension=" + pension + ", payment=" + payment + "]";
	}
	public SalaryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
}
