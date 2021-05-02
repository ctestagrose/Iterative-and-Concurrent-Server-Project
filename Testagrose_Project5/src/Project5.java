/**
 * COP 3530: Project 5 - Hash Tables
 * 
 * This program will create a hashtable of states and allow the user to interact with the hashtable in a variety of ways
 * 
 * @author Conrad Testagrose
 * @version 4/18/2021
 */


import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Project5 {
	
	public static Scanner input = new Scanner(System.in);
	public static HashTable ht = new HashTable();
	public static State[] states = new State[50];
	
	/**
	 * This is the main method which will serve as the main interaction window to the hash table by the user
	 * 
	 */
	public static void main(String[] args) {
		
		readFile();

		int userChoice = 0;

		do{
			System.out.println("1) Print hash table");
			System.out.println("2) Delete a state of a given name");
			System.out.println("3) Insert a state of a given name");
			System.out.println("4) Search and print a state and its DR for a given name");
			System.out.println("5) Print numbers of empty cells and collisions");
			System.out.println("6) Exit");

			char userInput = input.next().charAt(0);
			userChoice = Character.getNumericValue(userInput);

			switch(userChoice){

				case 1:{
					
					ht.display();

					break;

				}
				case 2:{
					
					System.out.println("Please enter the state name to delete");

					input.nextLine();

					String name = input.nextLine();
					
					int index = findState(name);
					
					if(index == -1) {
						
						 break;
						
					}
					
					ht.delete(states[index].getName(), states[index].getPopulation(), states[index].getDeaths());
					
					System.out.printf("%s has been deleted from the hashtable\n\n", states[index].getName());
					
					break;

				}
				case 3:{

					System.out.println("Please enter the state name you would like to add");

					input.nextLine();

					String name = input.nextLine();
					
					int index = findState(name);
					
					if(index == -1) {
						
						 break;
						
					}
					
					ht.insert(states[index].getName(), states[index].getPopulation(), states[index].getDeaths());
					
					System.out.printf("%s has been added to the hashtable\n\n", states[index].getName());
					
					break;

				}
				case 4:{
					
					System.out.println("Please enter the state name");

					input.nextLine();

					String name = input.nextLine();
					
					int index = findState(name);
					
					if(index == -1) {
						
						 break;
						
					}
					
					ht.find(states[index].getName(), states[index].getPopulation(), states[index].getDeaths());
					
					break;

				}
				case 5:{
					
					ht.printEmptyAndCollisions();

					break;

				}
				case 6:{
					
					System.out.println("Quitting!!");

					break;

				}
				default:{

					System.out.println("Invalid choice enter 1 - 6:");
					break;
				}

			}

		}
		while(userChoice != 6);




    }

    /**
     * This is the readFile() method and reads data from a csv file which is then inserted in to an array and a hashtable
     * 
     * 
     */
    public static void readFile(){

        System.out.println("Please input the csv file containing your data");
		
		String fileName = input.nextLine();
		

		BufferedReader bfr = null;
		String delimiter = ",";

		    try{
		      bfr = new BufferedReader(new FileReader(fileName));

		      String line = "";
		      bfr.readLine();

		      int counter = 0;

		      while((line = bfr.readLine()) != null){
		        String[] info = line.split(delimiter);

		        if(info.length > 0){
		          
					
					String name = info[0];
					long population = Long.parseLong(info[4]);
					long deaths = Long.parseLong(info[6]);
					
					ht.insert(name, population, deaths);
					states[counter] = new State(name, population, deaths);
					
		          	counter++;
				      
				  
		        }


		      }

			  System.out.printf("There were %d records read into the hash table\n", counter, fileName);
			  System.out.println("");
			  
		    }
		    catch(FileNotFoundException e){
		      System.out.println(e.getStackTrace());
		    }
		    catch(IOException e){
		      System.out.println(e.getStackTrace());
		    }
		    finally{
		      if(bfr != null){
		        try{
		          bfr.close();
		        }
		        catch(IOException e){
		          System.out.println(e.getStackTrace());
		        }
		      }
		    }
		
	
		
	}
    
    /**
     * The findState method will search the array states for a state and return the index to the calling function
     * 
     * 
     * @param findName : this paramter holds the string of the state name to be searched
     * @return an integer value of the index or -1 if not found in the array 
     */
    public static int findState(String findName) {
    	
    	for(int j = 0; j < states.length; j++) {
    		
    		if(states[j].getName().compareToIgnoreCase(findName) == 0) {
    			
    			return j;
    			
    		}
    		
    	}
    	
    	
    	System.out.printf("Check spelling or %s is not a state\n", findName);
    	
    	return -1;
    	
    }

}



