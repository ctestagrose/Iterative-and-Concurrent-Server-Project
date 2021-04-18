/**
 * The PriorityQ class will provide the functionality to create a PriorityQ that will hold state objects in a priority based on covid 19 death rate 
 * 
 * @author Conrad Testagrose
 * @version 03/04/2021
 *
 */

public class PriorityQ {
	
	private Link first;
	private Link last;
	public State temp;
	public int lower;
	public int higher;
	Link current = first;
	public int priority;
	
	/**
	 * This is the PriorityQ class constructor
	 * 
	 */
	public PriorityQ() {
			
	}

	/**
	 * The insert method will take a passed state object and insert it into the priority queue with the priority being the deathRate of the state 
	 * The lower the death rate the higher the priority
	 * 
	 * @param state this parameter is a state object that is passed from the calling method
	 */
	public void insert(State state) {
		
		
		Link newLink = new Link(state);
		Link current;
		double newLinkDR = newLink.state.getDeathRate();
		int position = 0;;
		
		current = first;
		
		if(first == null) {
			
			first = newLink;
			last = newLink;
			newLink.next = null;
			
		}
		else if(first != null){
			
			if(newLink.state.getDeathRate() < first.state.getDeathRate()) {
				
				newLink.next = first;
				first.previous = newLink.next;
				first = newLink;
			}
			else if(newLink.state.getDeathRate() > last.state.getDeathRate()) {
				
				newLink.next = null;
				last.next = newLink;
				newLink.previous = last.next;
				last = newLink;
			}
			else {
	
				int i = 1;
				
				current = first;
				
				while(current.next != null) {
					
					if(newLinkDR > current.state.getDeathRate()) {
						
						position = i;
						
					}
					
					current = current.next;
					i++;
				}
				
				int j = 1;
				
				current = first;
				
				while(j <= position) {
					
					if(j == position) {
						
						newLink.next = current.next;
						current.next.previous = newLink;
						newLink.previous = current;
						current.next = newLink;
					}
					
					j++;
					current = current.next;	
						
				}
					
				}
					
		}
		
	}

	/**
	 * The remove method will remove a link from the priority queue.
	 * 
	 * @param current this is the current state object to be rmeoved from the priority queue
	 */
	public void remove(Link current) {
		
		if(isEmpty()) {
			
			return;
		}
		else {
			
			if(current == first) {
				first = current.next;
			}
			if(current.next != null) {
				current.next.previous = current.previous;
			}
			if(current.previous != null) {
				current.previous.next = current.next;
			}
			if(current == first.next) {
				first.next = current.next;
			}
			
			current = null;
		}
		
	}
	
	/**
	 * The intervalDelete method will call the remove method for each state in a user supplied interval of deathrates
	 * 
	 * @param lower is the lower threshold of the interval
	 * @param higher is the upper threshold of the interval
	 */
	public void intervalDelete(int lower, int higher) {
		
		Link current = first;
		this.lower = lower;
		this.higher = higher;
		int i = 0;
		double currentDR = 0;
		
		
		while(current != null) {
			
			if(i > 0) {
				
				current = current.next;
			}
			
			if(current != null){//get the DR here
				//currentDR = ((current.state.getCovidDeaths() / current.state.getPopulation() * 100000));
				currentDR = current.state.getDeathRate();
			
			}
			
			if(currentDR >= lower && currentDR <= higher) {
				
				//current = first;
				remove(current);
				
				i = 0;
				
			}
			
			i++;
			
		}
		/*
		if(lower == 25 || lower == 24 || lower == 23) {
			
			remove(first.next);
			
		}
		*/
		
	}
	
	/**
	 * The printPriorityQ method will print each member of the priority queue. This method will implement itself until every member is printed
	 * 
	 * @param current holds the value to be printed current.next will be passed recursively to printStack as current 
	 * @param i is used to determine if the method is trying to print the first item of the queue
	 */
	public void printPriorityQ(Link current, int i){

		
		if(i == 1) {
			
			current = first;
		}
		if(first == null) {
			
			System.out.println("PRIORITY QUEUE EMPTY!");
			
			return;
		}
		
		if(current.next != null) {
		
			
			current.displayLink();
			
			i++;
			
			printPriorityQ(current.next, i);
			
			
		}
		
		if(current == last) {
			
			current.displayLink();
			
		}
		
		
	}
	
	/**
	 * The isEmpty method will return true if the first link of the linked list is null 
	 * 
	 * @return will return if the first link of the linked list is null
	 */
	public boolean isEmpty() {
		
		return (first == null);
	}
	
	/**
	 * The intervalCheck will check the user supplied interval to determine if the user is inputing an invalid interval or if the user is attempting to delete the entire priority queue
	 * 
	 * @param lower holds the lower threshold of the interval
	 * @param higher hold the upper threshold of the interval
	 * @return will return 0 if the user supplies an invalid interval and a 1 if the user has supplied a valid interval
	 */
	public int intervalCheck(int lower, int higher) {
		
		double firstDR = ((first.state.getCovidDeaths() / first.state.getPopulation() * 100000));
		double lastDR = ((last.state.getCovidDeaths() / last.state.getPopulation() * 100000));
				
		if(lower > higher) {
			
			System.out.println("Invalid: Your lower interval threshold is more than your upper interval threshold");
			System.out.println("");
			return 0;
		}
		
		if(lower <= firstDR && higher >= lastDR) {
			
			first = null;
			last = null;
			
			return 1;	
			
			
		}
		
		return 1;
	}
	/*
	public void sort() {
		
		Link current = null;
		Link walker = null;
		State temp;
		
		if(last == null) {
			
			return;
		}
		else {
			
			for(current = first; current.next != null; current = current.next) {
				
				for(walker = current.next; walker != null; walker = walker.next) {
					
					if(((current.state.getCovidDeaths() / current.state.getPopulation() * 100000) > ((walker.state.getCovidDeaths() / walker.state.getPopulation() * 100000)))){
						
						temp = current.state;
						current.state = walker.state;
						walker.state = temp;
					}
				}
				
			}
		}
		
	}
	*/
	
	/*
	public void remove2(int position) {
		
		Link current = first;
		int j = 0;
		
		while(j <= position) {
			
			if(j > 0) {
				
				current = current.next;
				
			}
			
			if(j == position) {
				
				if(isEmpty()) {
					return;
				}
				
				//perform deletion here
				if(first == current) {
					first = current.next;
				}//if first
				if(current.next != null) {
					current.next.previous = current.previous;
				}//if last
				if(current.previous != null){
					current.previous.next = current.next;
				}//else if not first or last
				
				current = null;
			}
			
			j++;
		}
	}
	*/
}
