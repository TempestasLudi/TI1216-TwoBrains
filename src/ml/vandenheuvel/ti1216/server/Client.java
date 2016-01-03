package ml.vandenheuvel.ti1216.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ml.vandenheuvel.ti1216.data.*;

/**
 * Every connection is kept track of in this client class. A thread that listens
 * to the server is opened from here. Messages can be sent from here.
 * 
 * @author Bram van den Heuvel
 */

public class Client {
	
	private Server server;
	private Thread communicator;

	Client(Server server, Socket socket) {
		this.server = server;
		this.communicator = new Thread(new ClientCommunicator(this, socket));
		this.communicator.start();
	}

	/**
	 * Stops the client entirely. First tries to stop the thread and then removes it's self from the parent server's clientList.
	 */
	public void stopClient() {
		this.communicator.close();
		this.communicator.interrupt();
		//TODO: Remove this client from the arraylist it's in.
	}

}
