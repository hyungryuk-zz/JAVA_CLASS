/**
*잉크젯, 도트, 레이저 프린터와 PDF 파일 생성기를 테스트하는 Demo 프로그램을 만들자. 
*잉크젯, 레이저 프린터는 매번 프린트할 때마다 각각 잉크와 토너를 소비하면 각각의 
*소비율은 서로 다르다. 사용하려는 프린터의 잉크나 토너의 용량이 다 떨어진 상태에서 
*프린트를 시도하면 경고 메시지를 보여준다. 반면 도트 프린터는 반영구적으로 사용가능.
*PDF 파일 생성기는 지정된 이름의 PDF 파일을 생성하고 그 안에 문서내용을 출력한다.
*Recfactoring
*1.상위 클래스를 추출하기(Extract Superclass)
*2.템플릿 메서드 만들기 (Form Template Method)
*3.인터페이스를 추출하기(Extract Interface)
*4.메서드 이동하기(Move Method)
*5.메서드를 합치기(Inline Method)
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

		iPrinter.print("환영합니다. 프린터를 테스트중입니다.");
		dPrinter.print("환영합니다. 프린터를 테스트중입니다.");
		lPrinter.print("환영합니다. 프린터를 테스트중입니다.");
		pWriter.print("환영합니다. 프린터를 테스트중입니다.");		
	}
}