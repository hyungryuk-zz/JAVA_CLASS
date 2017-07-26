/* ¦������ ���� ���� */
package refactoring.bridge;

public class EvenNumberStackArray extends StackArray {
	
	public EvenNumberStackArray(int num) {
		super(num);
	}
	
	public void push(int in) {
		if ( in%2 != 0 ) 
			return;
		
		super.push(in);		
	}	
}