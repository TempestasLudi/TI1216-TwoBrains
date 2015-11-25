package ml.vandenheuvel.TI1216.source.client;

import java.util.Scanner;

import ml.vandenheuvel.TI1216.source.data.Login;

public class ClientApplication {

	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.out.println("Server address and portnumber should be the only args.");
			return;
		}
		
		Scanner sc = new Scanner(System.in);
		
		Client client = new Client(args[0], Integer.parseInt(args[1]));
		if(client.start()) System.out.println("Connected.");
		
		//Ask for login credentials
		/* Temporary */
		Login login = null;
		/* Temporary */
		Thread clientThread = client.logIn(login);
		clientThread.start();
		

		
		client.close(clientThread);
	}

}
