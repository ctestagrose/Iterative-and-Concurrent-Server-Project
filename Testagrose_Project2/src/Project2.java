/**
 * COP 3530: Project 2 - Stacks and PriorityQ Queues 
 * 
 * This class will parse a .csv file for state objects and then create priorityQ queues containing the state objects. 
 * The objects within the PriorityQ queues will then be moved to a stack and the stack will be printed.
 * 
 * @author Conrad Testagrose
 * @version 02/17/2021
 *
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Project2 {
	
	public static Scanner input = new Scanner(System.in);
	public static State[] states = new State[50];
	
	public static void main(String[] args) {
			
			readFile();
			
			int vg = 0; 
			int g = 0;
			int f = 0;
			int p = 0;
			
			for(int i = 0; i < states.length; i++) {
				
				double deathRateCat = ((states[i].getCovidDeaths() / states[i].getPopulation()) * 100000);
				
				
				if(deathRateCat < 50) {
					
					vg++;
					
				}
				else if((50 <= deathRateCat) && (deathRateCat < 100)) {
					
					g++;
					
				}
				else if((100 <= deathRateCat) && (deathRateCat < 150)) {
					
					f++;
					
				}
				else if(deathRateCat >= 150) {
			
					p++;
					
				}
				
			}
			
			PriorityQ pq = new PriorityQ(vg, g, f, p);  
			
			for(int i = 0; i < states.length; i++) {	
				pq.insert(states[i]);
			}
			
			pq.printQueue();
			
			Stack ns; 
			
			ns = pq.remove();
			
			System.out.println("");
			System.out.println("Stack Contents:");
			
			ns.printStack();
			
		
	}

	/**
	 * This readFile method will read the name of the .csv file input by the user and parse the file for the information contained within the .csv file 
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
