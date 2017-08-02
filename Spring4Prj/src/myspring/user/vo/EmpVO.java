package myspring.user.vo;

public class EmpVO {
   private Integer empNO;
   private String empName;
   private String empJob;
   private Integer empMgr;
   private String empHireDate;
   private float empSal;
   private float empComm;
   private DeptVO dept;
   
   public EmpVO() {
      super();
   }

   public EmpVO(Integer empNO, String empName, String empJob, Integer empMgr, String empHireDate, float empSal,
         float empComm, DeptVO dept) {
      super();
      this.empNO = empNO;
      this.empName = empName;
      this.empJob = empJob;
      this.empMgr = empMgr;
      this.empHireDate = empHireDate;
      this.empSal = empSal;
      this.empComm = empComm;
      this.dept = dept;
   }

   public Integer getEmpNO() {
      return empNO;
   }

   public String getEmpName() {
      return empName;
   }

   public void setEmpName(String empName) {
      this.empName = empName;
   }

   public String getEmpJob() {
      return empJob;
   }

   public void setEmpJob(String empJob) {
      this.empJob = empJob;
   }

   public Integer getEmpMgr() {
      return empMgr;
   }

   public void setEmpMgr(Integer empMgr) {
      this.empMgr = empMgr;
   }

   public String getEmpHireDate() {
      return empHireDate;
   }

   public void setEmpHireDate(String empHireDate) {
      this.empHireDate = empHireDate;
   }

   public float getEmpSal() {
      return empSal;
   }

   public void setEmpSal(float empSal) {
      this.empSal = empSal;
   }

   public float getEmpComm() {
      return empComm;
   }

   public void setEmpComm(float empComm) {
      this.empComm = empComm;
   }

   public DeptVO getDept() {
      return dept;
   }

   public void setDept(DeptVO dept) {
      this.dept = dept;
   }

   @Override
   public String toString() {
      return "EmpVO [empNO=" + empNO + ", empName=" + empName + ", empJob=" + empJob + ", empMgr=" + empMgr
            + ", empHireDate=" + empHireDate + ", empSal=" + empSal + ", empComm=" + empComm + ", dept=" + dept
            + "]";
   }
   
   
   
}