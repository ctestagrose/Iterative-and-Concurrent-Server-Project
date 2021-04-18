import java.util.*;
import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class NewThread extends Thread{
	
	
	static ArrayList<Double> tot = new ArrayList<Double>();
	double Time;
	
	int userChoice;
	
	Socket socket;
	PrintWriter serverOutput;
	BufferedReader serverInput;
	
	int numClients;
	
	double timeLength;
	
	NewThread(String hostName, int portNumber, int userChoice, int i){
		
		try {
			this.socket = new Socket(hostName, portNumber);
			this.serverOutput = new PrintWriter(socket.getOutputStream(), true);
			this.serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.userChoice = userChoice;
			this.numClients = i;
			this.timeLength = 0;
		}
		catch(UnknownHostException e) {
			System.err.println("Please check the hostname");
			System.exit(1);
		}
		catch(IOException e) {
			System.err.println("Could not connect to the host");
			System.exit(1);
			
		}
		
		
	}
	
	public void run() {
		
		double time0 = System.currentTimeMillis();
		serverOutput.println(this.userChoice);
		
		try {
			
			while((serverInput.readLine()) != null){
				
				System.out.println(serverInput.readLine());
				
			}
			
			this.serverInput.close();
			this.serverOutput.close();
			this.socket.close();
			
			
		}
		catch(IOException e) {
			
			System.err.println("Encountered and issue while making a connection attempt");
			System.exit(1);
		}
		
		this.timeLength = (System.currentTimeMillis() - time0);
		tot.add(this.timeLength);
		
	}
	
}

public class MultiClient {

	static int clients;
	
	public static void main(String[] args) throws IOException {
		
		String hostName = "139.62.210.153";
		
		Scanner userScanner = new Scanner(System.in);
		
		System.out.println("Please enter the port number");
		
		int portNumber = userScanner.nextInt();
		
		int userChoice;
		int quit = 0;
		
		NewThread[] nt = new NewThread[100]; //number of new client threads to spawn
		
	
		while(quit == 0) {
			
			System.out.println("How many clients would you like to connect? (Please enter no more than 100)");
			
			clients = userScanner.nextInt();
			
			
			System.out.println("Please make your selection (Enter 1 - 7)");
			System.out.println("1: Check Date/Time");
			System.out.println("2: Check Server Uptime");
			System.out.println("3: Get Current Memory Usage");
			System.out.println("4: Get Server Netstat");
			System.out.println("5: Check the Current Users");
			System.out.println("6: See Running Processes");
			System.out.println("7: Exit Program");
			
			userChoice = userScanner.nextInt();
			
			while(userChoice < 1 || userChoice > 7) {
				
				System.out.println("Make your numerical selection please");
				userChoice = userScanner.nextInt();
			}
			
			switch(userChoice) {
			
			case 1:{
			
				int i = 0;
				
				while(i < clients) {
					
					DateTimeFormatter datef = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
					System.out.println(datef.format(now));
					
					i++;
				}
			
				break;
			}
			case 2:{
				
				int i = 0;
				
				while(i < clients) {
					
					Process upTimeProcess = Runtime.getRuntime().exec("uptime");
					BufferedReader utreader = new BufferedReader(new InputStreamReader(upTimeProcess.getInputStream()));
					String uptime;
					while((uptime = utreader.readLine()) != null) {
						
						System.out.println(uptime);
					}
					
					i++;
				}
				
				break;
			}
			case 7:{
				
				System.out.println("Thank you for using this program... Now Exiting!");
				
				System.exit(1);
				
				break;
			}
			}//end switch
			
			int i = 0;
			
			while(i < clients) {
				
				nt[i] = new NewThread(hostName, portNumber, userChoice, i);
				
				nt[i].start();
				
				try {
					nt[i].join();
				}
				catch(InterruptedException ie) {
					
					System.out.println(ie.getMessage());
				}
				
				i++;
			}
			
			double totalTime;
			
			totalTime = 0;
			
			for(double x: NewThread.tot) {
				
				totalTime = totalTime + x;
			}
			
			double avg = totalTime / clients;
			
			NewThread.tot.clear();
			System.out.printf("Average turn around time: %f ms\n", avg);
			System.out.printf("Total Time: %f ms\n", totalTime);
		}//end of while
		
		System.exit(1);
		userScanner.close();
		
	}
	
	
}
	
	
