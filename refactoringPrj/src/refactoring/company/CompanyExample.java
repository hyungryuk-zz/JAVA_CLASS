/*
* 회사원의 봉급을 관리(인상,인하)하는 프로그램
* 현재 회사에 3명의 종업원 있다고 가정, 두명은 점원(평사원) 나머지 사람은 관리자.
*
* 메서드를 추출하기(Extract Method), 
* 메서드 이름을 바꾸기(Rename Method), 
* 조건문을 폴리모피즘으로 바꾸기(Replace Conditional with Polymorphism), 
* 타입코드를 하위클래들 바꾸기(Replace Type Code with Subclass)
*
*/
package refactoring.company;
   
public class CompanyExample {
    public static void main(String[] args) {        
        
        Employee manager = new Employee(Employee.MANAGER, "홍길동", 200, null);
        Employee mereClerk1 = new Employee(Employee.CLERK, "철수", 100, manager);
        Employee mereClerk2 = new Employee(Employee.CLERK, "영희", 100, manager);
        
        System.out.println("현재 월급입니다.");
        
        System.out.println(mereClerk1.getName() + "의 현재 월급은 " + mereClerk1.getSalary() + " 만원 입니다.");
        System.out.println(mereClerk2.getName() + "의 현재 월급은 " + mereClerk2.getSalary() + " 만원 입니다.");
        System.out.println(manager.getName() + "의 현재 월급은 " + manager.getSalary() + " 만원 입니다.");

        System.out.println("");
              
        System.out.println("올린 후의 월급입니다.");
        
        mereClerk1.manageES(10);
        System.out.println(mereClerk1.getName() + "의 현재 월급은 " + mereClerk1.getSalary() + " 만원 입니다.");
        
        mereClerk2.manageES(10);
        System.out.println(mereClerk2.getName() + "의 현재 월급은 " + mereClerk2.getSalary() + " 만원 입니다.");
        
        manager.manageES(10);
        System.out.println(manager.getName() + "의 현재 월급은 " + manager.getSalary() + " 만원 입니다.");        
        
        System.out.println("");
        System.out.println(mereClerk1.getName() + "의 매니저는 " + mereClerk1.getManager().getName() + "이다.");
        System.out.println(mereClerk2.getName() + "의 매니저는 " + mereClerk2.getManager().getName() + "이다.");
        
   }
}
