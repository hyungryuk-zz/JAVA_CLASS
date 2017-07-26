package refactoring.printer;

public abstract class Printer implements Printable {

	protected String ID;

	public Printer() {
		super();
	}
	
	public Printer(String p_id) {
		this.ID = p_id;
	}

	public String getID() {
		return ID;
	}

	public void print(Object msg) {
		if ( isPrintable() ) {
			printing(msg);		
		} else 
			alert();				
	}
	
	//밑의 3개 메서드는 뼈대 메서드의 로직에 따라서 hook되는 메서드들이다 이를 hook method라 한다.
	public abstract boolean isPrintable();
	public abstract void printing(Object msg);
	public abstract void alert();

	public void testPrinting() {
		print("아아~ 프린트 테스트. 프린트 테스트");												 
	}
	//Printer 종류별 상수를 정의
	enum  PrinterType{
		INKJET,LASER,DOT
	}
	public static Printer create(PrinterType type, String ID) {
		switch(type) {
		case INKJET :
			return new InkjetPrinter(ID);
		case LASER : 
			return new LaserPrinter(ID);
		case DOT:
			return new DotPrinter(ID);
		default:
			throw new IllegalArgumentException("Invalid type value");
		}
		
	}

}