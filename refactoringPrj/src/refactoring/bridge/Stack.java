/* Stack 구현 클래스의 인터페이스 */
package refactoring.bridge;

public abstract class Stack {
	abstract void push(int i);
	abstract int pop();
	abstract int top();
	abstract boolean isEmpty();
	abstract boolean isFull();
}