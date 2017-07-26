/**
*��ũ��, ��Ʈ, ������ �����Ϳ� PDF ���� �����⸦ �׽�Ʈ�ϴ� Demo ���α׷��� ������. 
*��ũ��, ������ �����ʹ� �Ź� ����Ʈ�� ������ ���� ��ũ�� ��ʸ� �Һ��ϸ� ������ 
*�Һ����� ���� �ٸ���. ����Ϸ��� �������� ��ũ�� ����� �뷮�� �� ������ ���¿��� 
*����Ʈ�� �õ��ϸ� ��� �޽����� �����ش�. �ݸ� ��Ʈ �����ʹ� �ݿ��������� ��밡��.
*PDF ���� ������� ������ �̸��� PDF ������ �����ϰ� �� �ȿ� ���������� ����Ѵ�.
*Recfactoring
*1.���� Ŭ������ �����ϱ�(Extract Superclass)
*2.���ø� �޼��� ����� (Form Template Method)
*3.�������̽��� �����ϱ�(Extract Interface)
*4.�޼��� �̵��ϱ�(Move Method)
*5.�޼��带 ��ġ��(Inline Method)
*/
 
package refactoring.printer;

import refactoring.printer.Printer.PrinterType;

/* Extract SuperClass */
public class PrinterExample {

	public static void main(String[] args) {

		Printer iPrinter = Printer.create(PrinterType.INKJET, "101");//new InkjetPrinter("101");
		Printer dPrinter = Printer.create(PrinterType.DOT, "102");//new DotPrinter("102");
		Printer lPrinter = Printer.create(PrinterType.INKJET, "103");//new LaserPrinter("103");
	
		
		Printable pWriter = new PDFWriter("Test.pdf");

		iPrinter.print("ȯ���մϴ�. �����͸� �׽�Ʈ���Դϴ�.");
		dPrinter.print("ȯ���մϴ�. �����͸� �׽�Ʈ���Դϴ�.");
		lPrinter.print("ȯ���մϴ�. �����͸� �׽�Ʈ���Դϴ�.");
		pWriter.print("ȯ���մϴ�. �����͸� �׽�Ʈ���Դϴ�.");		
	}
}