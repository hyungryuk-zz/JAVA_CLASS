package refactoring.printer;
public class LaserPrinter extends Printer {
	
	private PrinterCartridge cartridge;

	public LaserPrinter(String ID) {
		super(ID);
		cartridge = new PrinterCartridge(100,0.2);
	}
	
	public void printing(Object msg) {
		System.out.println("*������ ������� ����Ʈ�� �����մϴ�.*");
		System.out.println(msg.toString());
		System.out.println("*������ ������� ����Ʈ�� �����մϴ�.*");
	
		cartridge.consume();
	}
	
	public void alert() {
		System.out.println("��ʰ� �����մϴ�. ��� ������ ���ڱ���~ ");
	}
	
	public boolean isPrintable() { // ������ ���� �з��� ����������,
		return cartridge.isAvailable();
	}		
}