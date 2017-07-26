package refactoring.company;

public class Employee {
	static final int CLERK = 1;
	static final int MANAGER = 2;
		 
    private String name;
    private double salary;
    private int type;
    private Employee manager;
    
    public Employee(int type, String name, double salary, Employee manager) {
    	this.type = type;
        this.name = name;
        this.salary = salary;
        this.manager = manager;
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getSalary() {
        return this.salary;
    }
    
    public Employee getManager() {
    	return manager;
    }
    
    public void manageES(double rate) {
    	if ( type == CLERK ) {
        	salary = salary + salary*(rate/100);
    	} else if ( type == MANAGER ) {
    		salary = salary+ salary*(rate/100);
	        salary += 20; // 관리자는 20만원을 추가로 받는다.
    	}        
    }
}