/*
* 위임을 상속으로 바꾸기 리팩토링
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
			System.out.println(target + " 는 스택안에 있다.");
		else 
			System.out.println(target + " 는 스택안에 없다.");			 
	}
	
	/* start부터 end까지 stack에 숫자를 입력한다. */
	public static void pushNumber(SearchableStack stack, int start, int end) {
		for (int i = start; i <= end; i++)
			stack.push(i);
	}
	
	/* 스택의 값들을 pop해서 출력한다.*/
	public static void printStack(SearchableStack stack) {
		while (!stack.isEmpty())
			System.out.print(stack.pop() + "  ");
		System.out.println();
	}
		
}



