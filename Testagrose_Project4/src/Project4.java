/**
 * COP 3530: Project 4 - Binary Search Trees  
 * 

 * @author Conrad Testagrose
 * @version 03/24/2021
 *
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


class Project4{

    public static Scanner input = new Scanner(System.in);
	public static BinarySearchTree bst = new BinarySearchTree();

	/**
	 * This is the main method and will be the method that the user interacts with when using this program
	 * 
	 * 
	 * @param args
	 */
    public static void main(String[] args){

        readFile();

		int userChoice = 0;

		do{
			System.out.println("1) Print tree inorder");
			System.out.println("2) Print tree preorder");
			System.out.println("3) Print tree postorder");
			System.out.println("4) Delete a state for a given name");
			System.out.println("5) Search and print a state and its path for a given name");
			System.out.println("6) Print bottom states regarding DR");
			System.out.println("7) Print top states regarding DR");
			System.out.println("8) Exit");

			char userInput = input.next().charAt(0);
			userChoice = Character.getNumericValue(userInput);

			switch(userChoice){

				case 1:{

					System.out.println("Inorder Traversal:");
					System.out.println("Name           Death Rate");
					System.out.println("-------------------------");
					bst.traversalType(1);
					break;

				}
				case 2:{

					System.out.println("Preorder Traversal:");
					System.out.println("Name           Death Rate");
					System.out.println("-------------------------");
					bst.traversalType(2);
					break;

				}
				case 3:{

					System.out.println("Postorder Traversal:");
					System.out.println("Name           Death Rate");
					System.out.println("-------------------------");
					bst.traversalType(3);
					break;

				}
				case 4:{

					System.out.print("Please input the name of the state you would like to delete: ");

					input.nextLine();

					String name = input.nextLine();

					bst.delete(name);

					break;

				}
				case 5:{

					System.out.print("Please input the name of the state you would like to search: ");

					input.nextLine();

					String name = input.nextLine();

					bst.find(name);

					break;

				}
				case 6:{

					int numStates;

					System.out.print("Enter the number of states: ");

					numStates = input.nextInt();
					
					System.out.printf("Bottom %d states regarding DR:\n", numStates);
					System.out.println("Name           Death Rate");
					System.out.println("-------------------------");

					bst.printBottomStates(numStates);

					break;

				}
				case 7:{

					int numStates;
					
					System.out.print("Enter the number of states: ");

					numStates = input.nextInt();
					
					System.out.printf("Top %d states regarding DR:\n", numStates);
					System.out.println("Name           Death Rate");
					System.out.println("-------------------------");

					bst.printTopStates(numStates);

					break;

				}
				case 8:{

					System.out.println("Quitting!");

					break;

				}
				default:{

					System.out.println("Invalid choice enter 1 - 8:");
					break;
				}

			}

		}
		while(userChoice != 8);




    }

    /**
     * This is the readFile() method and reads data from a csv file which is then inserted in to a binary search tree
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
					double stateDR = ((Double.parseDouble(info[6]) / Double.parseDouble(info[4])) * 100000);
					
					bst.insert(name, stateDR);
					
		          	counter++;
				      
				  
		        }


		      }

			  System.out.printf("There were %d records read from %s to build a binary search tree\n", counter, fileName);
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

}