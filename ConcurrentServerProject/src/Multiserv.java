import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.io.*;

public class Multiserv {
  public static void main(String[] args) throws Exception {
    try{
    	Scanner scn = new Scanner(System.in);
		String ip = "139.62.210.153";
		InetAddress ip2 = InetAddress.getByName(ip);

		System.out.println("Please Enter Port Number: ");
		int portNumber = scn.nextInt();
		
		System.out.println("Now attempting to Connect");

		ServerSocket socket = new ServerSocket(portNumber); 
			System.out.println("CONNECTION SUCCESSFUL");
			System.out.println("To shut down the server press CTRL + C");

			
      while(true){
        Socket serverClient=socket.accept(); 
        System.out.println("New Connection");
        ServerThread sct = new ServerThread(serverClient);
        sct.start();
      }
    }catch(Exception e){
      System.out.println(e);
    }
  }
}
class ServerThread extends Thread {
	  Socket serverClient;
	  //int clientNo;
	  int squre;
	  ServerThread(Socket nSocket){
	    serverClient = nSocket;
	  }
	  public void run(){
		  try {
				System.out.println("NEW USER CONNECTED:" + serverClient.getInetAddress());

				InputStream in = serverClient.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				OutputStream output = serverClient.getOutputStream();

				PrintWriter writer = new PrintWriter(output, true);

				boolean run = true;

				Process pro;
				String value;
				BufferedReader br;

			
				
				String received = reader.readLine();

				switch (received) {

				case "1":
					DateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Calendar c = Calendar.getInstance();
					String timestamp = d.format(c.getTime());
					break;

				case "2":
					Process ut = Runtime.getRuntime().exec("uptime");
					BufferedReader readUT = new BufferedReader(
					new InputStreamReader(ut.getInputStream()));
						
					break;
				case "3":
					Runtime memory = Runtime.getRuntime();
						
					double maxMem = memory.maxMemory() / 1000000.0;
					double freeMem = memory.freeMemory() / 1000000.0;
					double totalMem = ((maxMem - freeMem) / 1000000.0);
						
					writer.println("");
					writer.printf("Total memory: %f MB Free memory: %f MB Memory in Use: %f\n", maxMem, freeMem, totalMem);
						
					break;
				case "4":
					Process netStat = Runtime.getRuntime().exec("netstat -a");
					BufferedReader nr = new BufferedReader(
					new InputStreamReader(netStat.getInputStream()));
					String line;
					while ((line = nr.readLine()) != null) {
							writer.println(line);
					}
					break;
				case "5":
					Process users = Runtime.getRuntime().exec("who");
					BufferedReader uR = new BufferedReader(new InputStreamReader(users.getInputStream()));
					String userName;
					while((userName = uR.readLine()) != null) {
						writer.println(userName);
					}
					break;
				case "6":
					pro = Runtime.getRuntime().exec("ps");
					br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
					while ((value = br.readLine()) != null) {
						writer.println(value);
						writer.flush();
					}
					break;
				default:
					break;
				}
				writer.close();
				serverClient.close();
				in.close();
				reader.close();
				
	    }catch(Exception ex){
	      System.out.println(ex);
	  }
	}
}