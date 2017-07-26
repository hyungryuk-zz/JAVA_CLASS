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
	
	//���� 3�� �޼���� ���� �޼����� ������ ���� hook�Ǵ� �޼�����̴� �̸� hook method�� �Ѵ�.
	public abstract boolean isPrintable();
	public abstract void printing(Object msg);
	public abstract void alert();

	public void testPrinting() {
		print("�ƾ�~ ����Ʈ �׽�Ʈ. ����Ʈ �׽�Ʈ");												 
	}
	//Printer ������ ����� ����
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