/*
* 기본적인 스택 자료구조를 구현함
* Stack 클래스를 정의하고 Stack클래스는 java.util.Vector를 상속받았다.
* 메서드를 추출하기(Extract Method),
* 상속을 위임으로 바꾸기 (Replace Inheritance with Delegation)
*/
 
package refactoring.vectorstack;

import java.util.Vector;

public class VectorStackExample {
	public static void main(String[] args) {
		Stack stack = new Stack();		
	
		for (int i = 1; i <= 50; i++)
			stack.push(i);
			
		while (!stack.isEmptyStack())
			System.out.print(stack.pop() + "  ");
		System.out.println();
	}		
}



