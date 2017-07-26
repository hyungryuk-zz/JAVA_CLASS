package refactoring.searchstack;

import java.util.Vector;

public class Stack  {
	protected Vector vector = new Vector();
		
	public void push(int in) {
		vector.add(new Integer(in));
	}
	
	public int pop() {
		return ((Integer)vector.remove(vector.size()-1)).intValue();
	}
	
	public int top() {
		return ((Integer)vector.get(vector.size()-1)).intValue();
	}
	
	public boolean isEmpty() {
		return vector.isEmpty();
	}
	
	public boolean isFull() {
		return false;
	}
	
	public Vector getVector() {
		return vector;
	}	
}