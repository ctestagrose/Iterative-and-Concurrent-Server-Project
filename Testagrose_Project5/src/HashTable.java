

/**
 * This is the HashTable class and when called it will create an array of lists. 
 * The array of lists will be of size 101 and a hashing function will be used to determine where each state will be placed
 * This hashTable class uses separate chaining
 * The hash function will use the state name, population, and deaths to calculate the key
 * 
 * @author Conrad Testagrose
 * @version 4/18/2021
 *
 */
public class HashTable {
	
	private LinkedList[] hashArray = new LinkedList[101];
	
	/**
	 * This is the HashTable constructor it takes no arguments 
	 * 
	 */
	public HashTable() {
		
		for(int i = 0; i < 101; i++) {
			
			hashArray[i] = new LinkedList();
			
		}
		
		
	}
	
	/**
	 * This is the insert function and will use a hashing function to determine where to insert the state
	 * This function will call the Node class to create a node that will hold the data on the state and then insert the Node into its appropriate location
	 * 
	 * @param state : This is a String that holds the state name
	 * @param population : This is a long value that will hold the states population
	 * @param deaths : This is the long value of the number of deaths from covid-19
	 */
	public void insert(String state, long population, long deaths) {
		
		Node newNode = new Node(state, population, deaths);
				
		long num = 0;
		
		for(int i = 0; i < state.length(); i++) {
			
			num = num + state.charAt(i);
			
		}
		
		num = num + population + deaths;
		
		int key = (int) (num % 101);
		
		hashArray[key].insert(newNode);
		
	}
	
	/**
	 * This is the find function and it will be used to find a state in the hash table  
	 * 
	 * 
	 * @param state this String variable will hold the state name 
	 * @param population this long variable will hold the population of the state
	 * @param deaths this long variable will hold the deaths of a state
	 * @return 
	 */
	public int find(String state, long population, long deaths) {
		
		String findName = state;
		
		long num = 0;
		
		for(int i = 0; i < state.length(); i++) {
			
			num = num + state.charAt(i);
			
		}
		
		num = num + population + deaths;
		
		int key = (int) (num % 101);
		
		
		if(hashArray[key] != null) {
			
			hashArray[key].find(findName, key);
			
		}
		
		
		return 0;
	}
	
	/**
	 * This is the delete method and it will be used to delete a state from the hashtable
	 * 
	 * 
	 * @param state this String variable will hold the state's name
	 * @param population this long variable will hold the population of the state
	 * @param deaths this long variable will hold the deaths of a state
	 */
	public void delete(String state, long population, long deaths) {
		
		String findName = state;
		
		long num = 0;
		
		for(int i = 0; i < state.length(); i++) {
			
			num = num + state.charAt(i);
			
		}
		
		num = num + population + deaths;
		
		int key = (int) (num % 101);
		
		
		if(hashArray[key] != null) {
			
			hashArray[key].delete(findName, key);
				
				
			
			
		}
		
	}
	
	/**
	 * This is the display method and it will be used to display each index of the hash table
	 * 
	 * 
	 */
	public void display() {
		
		
		for(int i = 0; i < hashArray.length; i++) {
			
			System.out.printf("%3d.", i);
			
			hashArray[i].display();
			
			System.out.println("");
				
			
		}	
		
	
	}

	/**
	 * This is the printEmptyAndCollisions method and it will be used to print the number of empty indexes and the number of indexes that have collisions
	 * 
	 */
	public void printEmptyAndCollisions() {

		int empty = 0;
		int collisions = 0;
		
		
		for(int i = 0; i < hashArray.length; i++) {
			
			int eOC = hashArray[i].emptyOrCollision();
			
			if(eOC == 0) {
				
				empty++;

			}
			else if(eOC == 1) {
					
				collisions++;
				
			}
			
			eOC = 0;
			
		}
		
		System.out.printf("There are %d empty cells and %d collisions in the hash table\n\n", empty, collisions);
		
		
	}
	
	
	/**
	 * This is the Node class and it will provide the functionality to create node objects in the hash table lists
	 * This class will allow for the setting of the state name, population, and deaths
	 * This class will also provide a printNode() method which will allow for the printing of the contents in the node
	 * 
	 * 
	 * @author Conrad Testagrose
	 * @version 4/14/2021
	 *
	 */
	class Node{
		
		String name;
		long population;
		long deaths;
		Node nextNode;
		
		/**
		 * This is the constructor for the Node class
		 * 
		 * 
		 * @param name this String variable holds the name of the state 
		 * @param population this long variable holds the population of the state
		 * @param deaths this long variable holds the deaths of the state
		 */
		public Node(String name, long population, long deaths) {
			
			this.name = name;
			this.population = population;
			this.deaths = deaths;
			
		}
		
		/**
		 * This printNode function will print the name and the calculated death rate of the state contained in the node
		 * 
		 * 
		 */
		public void printNode() {
			
			System.out.printf("%-30s %-20.2f\n", name, (double)deaths/population * 100000);
			
		}
		
	}
	
	/**
	 * This is the LinkedList class and will provide the functionality necessary to create a list, insert into, and delete from the lists of the hash table
	 * 
	 * 
	 * @author Conrad Testagrose
	 * @version 4/17/2021
	 *
	 */
	public class LinkedList {
		
		private Node first;
		
		/**
		 * This is the LinkedList constructor 
		 * When the list is created in the hashtable array index it will set the first link to null
		 * 
		 * 
		 */
		public LinkedList() {
			
			first = null;
			
		}
		
		/**
		 * This is the insert method and it will insert a node into the singly linked list
		 * 
		 * 
		 * @param newNode this Node object holds the state to be inserted into the linked list
		 */
		public void insert(Node newNode) {
			
			if(first == null) {
				
				first = newNode;
				
			}
			else{
				
				Node current = first;
				
				while(current != null) {
					
					if(current.nextNode == null) {
						
						current.nextNode = newNode;
						
						return;
						
					}
					
					current = current.nextNode;
					
				}
				
			}
						
		}
		
		/**
		 * This is the delete method and it will delete a node from the singly linked list
		 * 
		 * 
		 * @param name this String variable will hold the name of the state to be deleted from the list
		 * @param index this integer variable will hold the index of the hashtable index
		 */
		public void delete(String name, int index) {
			
			Node current = first;
			
			if(first.name.compareToIgnoreCase(name) == 0) {
				
				if(first.nextNode != null) {
					
					first = first.nextNode;
					return;
					
				}
				else {
					
					first = null;
					return;
					
				}
				
			}
			
			while(current.nextNode != null) {
				
				if(current.nextNode.name.compareToIgnoreCase(name) == 0) {
					
					current.nextNode = current.nextNode.nextNode;
					
					return;
				}
				else {
					
					current = current.nextNode;
					
				}
				
			}
			
			
			System.out.printf("%s was not found\n", name);
			return;
			
		}
		
		
		/**
		 * This is the find method and will be used to find a state in the linked list
		 * 
		 * 
		 * @param name this String variable holds the name to find in the linked list
		 * @param index this integer variable holds the index of the hashtable array where the name should be located
		 */
		public void find(String name, int index) {
			
			Node current = first;
			
			while(current != null) {
				
				if(current.name.compareToIgnoreCase(name) == 0) {
					
					System.out.printf("%s is found at index %d with a DR of %.2f\n", current.name, index, (double)current.deaths/current.population * 100000);
					
					return;
					
				}
				
				current = current.nextNode;
				
				
			}
			
			
			System.out.printf("%s was not found\n", name);
			return;
			
		}
		
		/**
		 * This is the display method and it will be used to print the singly linked list at each index of the hashtable 
		 * 
		 * 
		 */
		public void display() {
			
			Node current = first;
			
			if(current == null) {
				
				System.out.println("   Empty");
				
			}
				
			while(current != null) {
				
				if(current == first) {
					
					System.out.print("   ");
					current.printNode();
					current = current.nextNode;
					
					
				}
				else {
				
					System.out.print("       ");
				
					current.printNode();
				
					current = current.nextNode;
				
				}
			}
			
		}
		
		/**
		 * This is the emptyOrCollision method and it is used to determine if there is a collision at a hash table index or if that index is empty. 
		 * 
		 * 
		 * 
		 * @return This method will return different integer values if it is empty, if there is a collision, or if there is only one object contained
		 */
		public int emptyOrCollision() {
			
			Node current = first;
			
			if(current == null) {
				
				return 0;
				
			}
			else if(current.nextNode != null){
			
				
				
				return 1;
			}
			else {
				
				
				return 2;
			}
			
		}
		

	}

}





