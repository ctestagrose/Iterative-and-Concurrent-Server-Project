/**
 * COP 3530: Project 3 -  
 * 
 * This class will parse a .csv file for state objects and then create a stack using a Linked list of only the states with Very Good, Good, and Fair Death Rates.
 * The states will then be popped off the stack and inserted into a priority queue with the priority being the death rate.
 * The user can then choose to print the priority queue or delete an interval of objects in the priority queue
 * 
 * @author Conrad Testagrose
 * @version 03/07/2021
 *
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Project3 {
	
	public static Scanner input = new Scanner(System.in);
	public static Stack ns = new Stack();
	public static PriorityQ pQ = new PriorityQ();
	public static State[] states = new State[50];
	public static State temp;
	
	/**
	 * This is the main method and will allow the user to interact with the priority queue by deleting an interval of death rates and printing the stack
	 * This method will call readFile and then create a stack object and a priorityQueue objectthat will hold the parsed state objects 
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
			
			readFile();
			
			int lower = -1;
			int higher = 1000;
			int intervalChecker = 0;
			
			//Stack ns = new Stack();
			//PriorityQ pQ = new PriorityQ();
			
			for(int i = 0; i < states.length; i++) {
				
				double deathRateCat = ((states[i].getCovidDeaths() / states[i].getPopulation()) * 100000);
				
				
				if(deathRateCat < 50) {
					
					ns.push(states[i]);
					
				}
				else if((50 <= deathRateCat) && (deathRateCat < 100)) {
					
					ns.push(states[i]);
					
				}
				else if((100 <= deathRateCat) && (deathRateCat < 150)) {
					
					ns.push(states[i]);
					
				}
				else if(deathRateCat >= 150) {
					//left this just in case the functionality is requested in the future. 
					//ns.push(states[i]);
					//p++;
					
				}
				
			}
			System.out.print("Stack (top --> bottom):\n");
			System.out.println("Name               MHI         VCR         CFR           Case Rate     Death Rate");
			System.out.println("-----------------------------------------------------------------------------------");
			
			int stackSize = ns.getSize();
			
			ns.printStack(null, stackSize);
			
			while(ns.isEmpty() != true) {
				temp = ns.pop();
				pQ.insert(temp);
			}
			
			
			int quit = 0;
			char userInput;
			int userChoice;
			
			do {
				
				System.out.println("1) Enter a death rate interval for deletions\n2) Print the priority queue\n3) Exit the program");
				System.out.print("Enter your choice: ");
				userInput = input.next().charAt(0);
				userChoice = Character.getNumericValue(userInput);
				
				if((userChoice != 1) && (userChoice != 2) && (userChoice != 3)) {
					
					System.out.println("Please enter a valid choice");
					
				}
				else {
					
					switch(userChoice) {
					
					case 1:{
						
						input.nextLine();
						
						
						do {
							
							System.out.println("Please input the death rate interval you want to delete");
							System.out.println("Positive integers only");
							System.out.println("Enter with the following format: [x, y]");
							
							
							String test = input.nextLine();
							
							test = test.replaceAll("[^\\d]", " ");
							
							test = test.trim();
							
							test = test.replaceAll(" +", " ");
							
							String[] nums = test.split(" ");
							
							if(nums.length == 2) {
								
							
								lower = Integer.valueOf(nums[0]);
								
								higher = Integer.valueOf(nums[1]);
								
								intervalChecker = pQ.intervalCheck(lower, higher);
								
							}
							else {
								
								System.out.println("Incorrect input format please try again");
								
								
							}
							
							//System.out.println(lower);
							
							//System.out.println(higher);
							
							/*System.out.println("Please enter the lower interval value (Please enter a positive number)");
							lower = input.nextInt();
							
							System.out.println("Please enter the upper interval value");
							higher = input.nextInt();*/
							
							
						}while(intervalChecker == 0);
						
						pQ.intervalDelete(lower, higher);
						
						System.out.printf("Deleted all states with death rates within interval: [%d, %d]\n", lower, higher);
						
						break;
					}
					case 2:{
						//pQ.sort();
						//pQ.recursivePrint(null,  1);
						System.out.print("List (first --> last):\n");
						System.out.println("Name               MHI         VCR         CFR           Case Rate     Death Rate");
						System.out.println("-----------------------------------------------------------------------------------");
						
						pQ.printPriorityQ(null, 1);
						break;
					}
					case 3:{
						System.out.println("Quitting! Have a good day!");
						quit = 1;
						break;
					}
					
					}
				}
				
				
			}while(quit == 0);
			
			
			
	}

	/**
	 * This readFile method will read the name of the .csv file input by the user and parse the file for the information contained within the .csv file 
	 * The read file method will insert the parsed data into an array
	 * 
	 */
	
	public static void readFile() {

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
		          
		          states[counter]= new State(info[0], info[1], info[2], Double.parseDouble(info[3]), Double.parseDouble(info[4]), Double.parseDouble(info[5]), Double.parseDouble(info[6]), Double.parseDouble(info[7]), Double.parseDouble(info[8]));
		          //states[counter].set(info, counter);

		          double stateDR = ((states[counter].getCovidDeaths() / states[counter].getPopulation()) * 100000);
		          
		          states[counter].setDeathRate(stateDR);
		          
		          counter++;          
		        }


		      }

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

}
