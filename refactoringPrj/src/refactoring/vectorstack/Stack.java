package refactoring.vectorstack;

import java.util.Vector;

public class Stack extends Vector {
		
	public void push(int in) {
		add(new Integer(in));
	}
	
	public int pop() {
		return ((Integer)remove(size()-1)).intValue();
	}
	
	public int top() {
		return ((Integer)get(size()-1)).intValue();
	}
	
	public boolean isEmptyStack() {
		return isEmpty();
	}
	
	public boolean isFull() {
		return false;
	}	
}