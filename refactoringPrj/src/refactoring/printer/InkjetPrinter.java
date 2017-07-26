package refactoring.printer;

public class InkjetPrinter extends Printer {
	private PrinterCartridge cartridge;
	
	public InkjetPrinter(String ID) {
		super(ID);
		cartridge = new PrinterCartridge(100,0.5);
		
	}
	

	public void printing(Object msg) {
		System.out.println("*��ũ�� ������� ����Ʈ�� �����մϴ�.*");
		System.out.println(msg.toString());
		System.out.println("*��ũ�� ������� ����Ʈ�� �����մϴ�.*");	
		
		cartridge.consume();
	}
	
	public void alert() {
		System.out.println("��ũ�� �����մϴ�. ���� ������ ���ڱ���~ ");
	}
	
	public boolean isPrintable() { // ������ ���� �з��� ����������,
		return cartridge.isAvailable();		
	}	
}