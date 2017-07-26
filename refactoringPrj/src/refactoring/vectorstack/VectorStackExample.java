/*
* �⺻���� ���� �ڷᱸ���� ������
* Stack Ŭ������ �����ϰ� StackŬ������ java.util.Vector�� ��ӹ޾Ҵ�.
* �޼��带 �����ϱ�(Extract Method),
* ����� �������� �ٲٱ� (Replace Inheritance with Delegation)
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



