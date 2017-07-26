/* 홀수값만 들어가는 스택 */
package refactoring.bridge;

public class OddNumberStackArray extends StackArray {
	public OddNumberStackArray(int num) {
		super(num);
	}
	
	public void push(int in) {
		if ( in%2 == 0 ) 
			return;
		
		super.push(in);		
	}		
}