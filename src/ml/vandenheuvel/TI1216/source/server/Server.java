package ml.vandenheuvel.TI1216.source.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import ml.vandenheuvel.TI1216.source.data.*;

/**
 * The actual server, this code runs until the server is stopped. As soon as it
 * starts the server is ready to accept client connections.
 * 
 * @author Bram van den Heuvel
 *
 */
public class Server {

	private int portNumber;
	private ArrayList<Client> clientList;
	private DatabaseCommunicator databaseCommunicator;
	private ServerSocket serverSocket;

	public Server(int portNumber) {
		this.portNumber = portNumber;
		this.clientList = new ArrayList<Client>();
		this.databaseCommunicator = new DatabaseCommunicator("hostname", "database");
		// Start thread to make the matches
	}

	public void start() {
		try {
			serverSocket = new ServerSocket(this.portNumber);
			System.out.println("Started with success on port " + this.portNumber);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				Client client = new Client(this, clientSocket);
				this.clientList.add(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get method for the databaseCommunicator object of this server.
	 * 
	 * @return The databaseCommunicator object
	 */
	public DatabaseCommunicator getDatabaseCommunicator() {
		return this.databaseCommunicator;
	}

	/**
	 * Stop communicating with a client. Stop it's thread, close it's streams
	 * and socket. Then remove it from the clientList.
	 * 
	 * @param i
	 *            The index of the client in the clientList
	 */
	public void removeClient(int i) {
		this.clientList.get(i).stopClient();
		this.clientList.remove(i);
	}

}
