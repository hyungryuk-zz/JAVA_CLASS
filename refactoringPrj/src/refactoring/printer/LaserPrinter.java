package refactoring.printer;
public class LaserPrinter extends Printer {
	
	private PrinterCartridge cartridge;

	public LaserPrinter(String ID) {
		super(ID);
		cartridge = new PrinterCartridge(100,0.2);
	}
	
	public void printing(Object msg) {
		System.out.println("*레이저 방식으로 프린트를 시작합니다.*");
		System.out.println(msg.toString());
		System.out.println("*레이저 방식으로 프린트를 종료합니다.*");
	
		cartridge.consume();
	}
	
	public void alert() {
		System.out.println("토너가 부족합니다. 노란 램프를 깜박깜박~ ");
	}
	
	public boolean isPrintable() { // 한장을 찍을 분량이 남아있으면,
		return cartridge.isAvailable();
	}		
}