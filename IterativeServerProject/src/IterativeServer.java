import java.net.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.lang.Process;
import java.lang.Runtime;

public class IterativeServer {
	public static void main(String[] args) throws UnknownHostException {
		Scanner scn = new Scanner(System.in);
		String ip = "139.62.210.153";
		InetAddress ip2 = InetAddress.getByName(ip);

		System.out.println("Please Enter Port Number: ");
		int portNumber = scn.nextInt();
		
		System.out.println("Now attempting to Connect");

		try (ServerSocket socket = new ServerSocket(portNumber, 100, ip2)) {
			System.out.println("CONNECTION SUCCESSFUL");
			System.out.println("To shut down the server press CTRL + C");

			while (true) {
				Socket clientServer = socket.accept();
				System.out.println("NEW USER CONNECTED:" + clientServer.getInetAddress());

				InputStream in = clientServer.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				OutputStream output = clientServer.getOutputStream();

				PrintWriter writer = new PrintWriter(output, true);

				boolean run = true;

				Process pro;
				String value;
				BufferedReader br;

				if (!run) {
					continue;
				}
				do {
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
					clientServer.close();
					break;
				} while (run);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}