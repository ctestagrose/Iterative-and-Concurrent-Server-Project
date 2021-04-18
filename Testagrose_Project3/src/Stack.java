
/**
 * The Stack class will provide the functionality to create a stack using a linked list.
 * The stack will contain state objects parsed from the states3.csv file
 * 
 * @author Conrad Testagrose
 * @version 03/05/2021
 *
 */

public class Stack {
	
	private Link first;
	private Link last;
	private int size;

	PriorityQ pq = new PriorityQ();
	/**
	 * This is the constructor for the Stack class
	 * The constructor sets the first link to a value of null
	 * 
	 */

	public Stack() {
		
		first = null;
	}

	/**
	 * The isEmpty method will return true when the first value is set to equal null
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		
		return (first == null);
		
	}
	
	/**
	 * The push method with push a state object onto the stack
	 * 
	 * @param state this object is passed to the push method from main and contains the information passed to the stack
	 */
	public void push(State state) {
		
		Link newLink = new Link(state);
		
		if(isEmpty()) {
			
			first = newLink;
			size++;
		}
		else {
			
			newLink.previous = last;
			last.next = newLink;
			size++;
		}
		
		last = newLink;
		
		
	}
	
	/**
	 * The pop method will pop a state object off the top of the stack(back of the list) and return the object
	 * 
	 * @return
	 */
	public State pop() {
		
		Link current = first;
		Link previous = first;
		State tS = last.state;
		
		previous = last.previous;
		current = last;
		
		if(first != last) {
			tS = last.state;
			last = previous;
			last.next = null;
		}
		else {
			tS = last.state;
			first = null;
			last = null;
			
		}
	
		size--;
		
		return tS;
		
	}
	
	/**
	 * The printStack method will print each member of the stack. This method will implement itself until every member is printed
	 * 
	 * @param current holds the value to be printed current.previous will be passed recursively to printStack as current 
	 * @param i is used to determine if the method is trying to print the top item in the stack
	 */
	
	public void printStack(Link current, int i) {


		if(i == size) {
			
			current = last;
			
		}
		
		if(current == first) {
			
			current.displayLink();
			
		}
	
		if(current.previous != null) {
		
			current.displayLink();
			
			i--;
			
			printStack(current.previous, i);
		}

	}
	
	/**
	 * The getSize() method will return the size of the stack
	 * 
	 * @return the size of the stack which is incremented on push and decremented on pop
	 */
	public int getSize() {
		
		return size;
	}
}
