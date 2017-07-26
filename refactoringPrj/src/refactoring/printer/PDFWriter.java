package refactoring.printer;
public class PDFWriter implements Printable {
	private String fileName; // PDF ���� ���� �̸�

    public PDFWriter(String fileName) {
        this.fileName = fileName;
    }
    
   	public void setFileName(String fileName) {
   		this.fileName = fileName;
   	}

    /* (non-Javadoc)
	 * @see refactoring.printer.Printable#print(java.lang.Object)
	 */
    public void print(Object document) {
        System.out.println("*���������� " + fileName + " ���Ͽ� PDF �������� ����ϱ� �����մϴ�.*");
        System.out.println(document);
        System.out.println("*���������� PDF �������� ����� �Ϸ��߽��ϴ�.");
    }
}