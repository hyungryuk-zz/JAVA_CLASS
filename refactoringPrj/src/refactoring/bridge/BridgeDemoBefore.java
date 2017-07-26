/*
* 상속 계층도를 찢어 분리하기 (Tease Apart Inheritance)
*/
 
package refactoring.bridge;

/* Tease Apart Inheritance */
public class BridgeDemoBefore {
	public static void main(String[] args) {
		Stack[] stacks = { new EvenNumberStackArray(15), new OddNumberStackArray(15)};		
	
		for (int i = 0; i < stacks.length; i++)
			pushNumber(stacks[i], 1, 50);
				
		for (int j = 0;  j < stacks.length; j++) {
			printStack(stacks[j]);			
		}		
	}
	
	/* start부터 end까지 stack에 숫자를 입력한다. */
	public static void pushNumber(Stack stack, int start, int end) {
		for (int i = start; i <= end; i++)
			stack.push(i);
	}
	
	/* 스택의 값들을 pop해서 출력한다.*/
	public static void printStack(Stack stack) {
		while (!stack.isEmpty())
			System.out.print(stack.pop() + "  ");
		System.out.println();
	}
		
}




