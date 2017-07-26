package refactoring.printer;

public class InkjetPrinter extends Printer {
	private PrinterCartridge cartridge;
	
	public InkjetPrinter(String ID) {
		super(ID);
		cartridge = new PrinterCartridge(100,0.5);
		
	}
	

	public void printing(Object msg) {
		System.out.println("*À×Å©Á¬ ¹æ½ÄÀ¸·Î ÇÁ¸°Æ®¸¦ ½ÃÀÛÇÕ´Ï´Ù.*");
		System.out.println(msg.toString());
		System.out.println("*À×Å©Á¬ ¹æ½ÄÀ¸·Î ÇÁ¸°Æ®¸¦ Á¾·áÇÕ´Ï´Ù.*");	
		
		cartridge.consume();
	}
	
	public void alert() {
		System.out.println("À×Å©°¡ ºÎÁ·ÇÕ´Ï´Ù. »¡°£ ·¥ÇÁ¸¦ ±ô¹Ú±ô¹Ú~ ");
	}
	
	public boolean isPrintable() { // ÇÑÀåÀ» ÂïÀ» ºÐ·®ÀÌ ³²¾ÆÀÖÀ¸¸é,
		return cartridge.isAvailable();		
	}	
}