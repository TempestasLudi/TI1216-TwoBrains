package ml.vandenheuvel.TI1216.source.client;

import ml.vandenheuvel.TI1216.source.data.Login;

/**
 * This class contains the main function and is run using two arguments; the
 * server and the port to use.
 * 
 * @author Bram van den Heuvel
 *
 */
public class ClientApplication {

	private ClientApplication(){
		//To stop implicit constructor
	}

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out
					.println("Server address and portnumber should be the only args.");
			return;
		}

		Client client = new Client(args[0], Integer.parseInt(args[1]));
		if (client.start())
			System.out.println("Connected.");

		// Ask for login credentials
		/* Temporary */
		Login login = null;
		/* Temporary */
		
		client.logIn(login);
		
		client.getServerListener().start();

		client.close();
	}
}
