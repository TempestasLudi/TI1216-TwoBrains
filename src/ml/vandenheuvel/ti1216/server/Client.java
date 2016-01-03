package ml.vandenheuvel.ti1216.server;

import java.net.Socket;

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
		this.communicator.interrupt();
		//TODO: Close properly
		//TODO: Remove this client from the arraylist it's in.
	}
	
	public Server getServer(){
		return this.server;
	}

}
