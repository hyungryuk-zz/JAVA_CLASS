package myspring.user.vo;

public class DeptVO {
	private Integer deptNo;
	private String deptName;
	private String deptLoc;
	public Integer getDeptNo() {
		return deptNo;
	}
	public String getdName() {
		return deptName;
	}
	public String getdLoc() {
		return deptLoc;
	}
	public void setdName(String dName) {
		this.deptName = dName;
	}
	public void setdLoc(String dLoc) {
		this.deptLoc = dLoc;
	}
	@Override
	public String toString() {
		return "DeptVO [deptNo=" + deptNo + ", dName=" + deptName + ", dLoc=" + deptLoc + "]";
	}
	public DeptVO(Integer deptNo, String dName, String dLoc) {
		super();
		this.deptNo = deptNo;
		this.deptName = dName;
		this.deptLoc = dLoc;
	}
	public DeptVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
