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