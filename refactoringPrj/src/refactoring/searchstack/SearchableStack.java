package refactoring.searchstack;

public class SearchableStack  {
	protected Stack stack = new Stack();
		
	public void push(int in) {
		stack.push(in);
	}
	
	public int pop() {
		return stack.pop();
	}
	
	public int top() {
		return stack.top();
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public boolean isFull() {
		return false;
	}
	
	public boolean search(int target) {
		return stack.getVector().contains(new Integer(target));
	}			
}