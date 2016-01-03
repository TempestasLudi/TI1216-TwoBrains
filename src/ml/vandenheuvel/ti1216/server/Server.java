package ml.vandenheuvel.ti1216.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ml.vandenheuvel.ti1216.data.DatabaseCommunicator;

/**
 * ApiServer is a HTTP server to handle API requests.
 * 
 * @author Bram van den Heuvel
 */
public class Server {

	private int portNumber;
	private ArrayList<Client> clientList;
	private DatabaseCommunicator getDatabaseCommunicator;
	private ServerSocket serverSocket;
	private Processor processor;

	public Server(int portNumber, String databaseAddress, String databaseName, String databaseUsername, String databasePassword) {
		this.portNumber = portNumber;
		this.clientList = new ArrayList<Client>();
		this.getDatabaseCommunicator = new DatabaseCommunicator(databaseAddress, databaseName, databaseUsername, databasePassword);
		this.processor = new Processor(this);
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
	 * @return the databaseCommunicator object
	 */
	public DatabaseCommunicator getDatabaseCommunicator() {
		return this.getDatabaseCommunicator;
	}
	
	/**
	 * Get method for the processor object of this server
	 * 
	 * @return the processor object
	 */
	public Processor getProcessor() {
		return this.processor;
	}

	/**
	 * Stop communicating with a client. Stop it's thread, close it's streams
	 * and socket. Then remove it from the clientList.
	 * 
	 * @param i The index of the client in the clientList
	 */
	public void removeClient(int i) {
		this.clientList.get(i).stopClient();
		this.clientList.remove(i);
	}

}
