package refactoring.printer;
public class DotPrinter extends Printer {
	public DotPrinter(String ID) {
		super(ID);
	}
	

	public void printing(Object msg) {
		System.out.println("*도트 방식으로 프린트를 시작합니다.*");
		System.out.println(msg.toString());
		System.out.println("*도트 방식으로 프린트를 종료합니다.*");
	}
	
	public boolean isPrintable() { 
		return true;		
	}
	
	public void alert() {
		
	}
}