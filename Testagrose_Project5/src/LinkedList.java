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
