/* 배열로 구현된 스택 구현 클래스 */
package refactoring.bridge;

public class StackArray extends Stack {
	private int[] items;
	private int total;
	
	public StackArray() {
		items = new int[10]; // 디폴트 
		total = -1;		
	}
		
	public StackArray(int num) {
		if ( num > 0 )
			items = new int[num];
		else
			items = new int[10]; // 디폴트 
			
		total = -1;
	}
	
	public void push(int i) {
		if (!isFull())
			items[++total] = i;
	}
	
	public boolean isEmpty() {
		return total == -1;
	}
	
	public boolean isFull() {
		return total == items.length - 1;
	}
	
	public int top() {
		if (isEmpty())
			return -1;
		return items[total];
	}
	
	public int pop() {
		if (isEmpty())
			return -1;
			
		return items[total--];
	}
	
}