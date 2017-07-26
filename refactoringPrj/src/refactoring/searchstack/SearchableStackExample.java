/*
* ������ ������� �ٲٱ� �����丵
*/
 
package refactoring.searchstack;

import java.util.Vector;

/* Replace Delegation with Inheritance */
public class SearchableStackExample {
	public static void main(String[] args) {
		SearchableStack stack = new SearchableStack();		
	
		pushNumber(stack, 1, 50);
		printStack(stack);							
		
		int target = 5;
		if ( stack.search(target) )
			System.out.println(target + " �� ���þȿ� �ִ�.");
		else 
			System.out.println(target + " �� ���þȿ� ����.");			 
	}
	
	/* start���� end���� stack�� ���ڸ� �Է��Ѵ�. */
	public static void pushNumber(SearchableStack stack, int start, int end) {
		for (int i = start; i <= end; i++)
			stack.push(i);
	}
	
	/* ������ ������ pop�ؼ� ����Ѵ�.*/
	public static void printStack(SearchableStack stack) {
		while (!stack.isEmpty())
			System.out.print(stack.pop() + "  ");
		System.out.println();
	}
		
}



