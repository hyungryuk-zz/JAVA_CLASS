/* Stack ���� Ŭ������ �������̽� */
package refactoring.bridge;

public abstract class Stack {
	abstract void push(int i);
	abstract int pop();
	abstract int top();
	abstract boolean isEmpty();
	abstract boolean isFull();
}