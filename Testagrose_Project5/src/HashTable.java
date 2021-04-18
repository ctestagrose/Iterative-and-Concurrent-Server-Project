/**
 * This is the HashTable class and when called it will create an array of lists. 
 * The array of lists will be of size 101 and a hashing function will be used to determine where each state will be placed
 * This hashTable class uses separate chaining
 * The hash function will use the state name, population, and deaths to calculate the key
 * 
 * @author Conrad Testagrose
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

}





