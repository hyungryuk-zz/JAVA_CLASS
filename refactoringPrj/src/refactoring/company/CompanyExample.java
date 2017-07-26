/*
* ȸ����� ������ ����(�λ�,����)�ϴ� ���α׷�
* ���� ȸ�翡 3���� ������ �ִٰ� ����, �θ��� ����(����) ������ ����� ������.
*
* �޼��带 �����ϱ�(Extract Method), 
* �޼��� �̸��� �ٲٱ�(Rename Method), 
* ���ǹ��� �������������� �ٲٱ�(Replace Conditional with Polymorphism), 
* Ÿ���ڵ带 ����Ŭ���� �ٲٱ�(Replace Type Code with Subclass)
*
*/
package refactoring.company;
   
public class CompanyExample {
    public static void main(String[] args) {        
        
        Employee manager = new Employee(Employee.MANAGER, "ȫ�浿", 200, null);
        Employee mereClerk1 = new Employee(Employee.CLERK, "ö��", 100, manager);
        Employee mereClerk2 = new Employee(Employee.CLERK, "����", 100, manager);
        
        System.out.println("���� �����Դϴ�.");
        
        System.out.println(mereClerk1.getName() + "�� ���� ������ " + mereClerk1.getSalary() + " ���� �Դϴ�.");
        System.out.println(mereClerk2.getName() + "�� ���� ������ " + mereClerk2.getSalary() + " ���� �Դϴ�.");
        System.out.println(manager.getName() + "�� ���� ������ " + manager.getSalary() + " ���� �Դϴ�.");

        System.out.println("");
              
        System.out.println("�ø� ���� �����Դϴ�.");
        
        mereClerk1.manageES(10);
        System.out.println(mereClerk1.getName() + "�� ���� ������ " + mereClerk1.getSalary() + " ���� �Դϴ�.");
        
        mereClerk2.manageES(10);
        System.out.println(mereClerk2.getName() + "�� ���� ������ " + mereClerk2.getSalary() + " ���� �Դϴ�.");
        
        manager.manageES(10);
        System.out.println(manager.getName() + "�� ���� ������ " + manager.getSalary() + " ���� �Դϴ�.");        
        
        System.out.println("");
        System.out.println(mereClerk1.getName() + "�� �Ŵ����� " + mereClerk1.getManager().getName() + "�̴�.");
        System.out.println(mereClerk2.getName() + "�� �Ŵ����� " + mereClerk2.getManager().getName() + "�̴�.");
        
   }
}
