package ml.vandenheuvel.TI1216.source.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;

import ml.vandenheuvel.TI1216.source.data.ChatMessage;
import ml.vandenheuvel.TI1216.source.data.Login;
import ml.vandenheuvel.TI1216.source.data.User;

/**
 * This class can connect to a server, open streams and log in to the server.
 * The serverListener thread is started here.
 * 
 * @author Bram van den Heuvel
 *
 */
public class Client {
	private String server;
	private int portNumber;
	private User user;
	private Login login;
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private Thread serverListener;

	Client(String server, int portNumber) {
		this.server = server;
		this.portNumber = portNumber;
	}

	/**
	 * Tries to start a connection to a server and open open streams.
	 * 
	 * @return True when connecting succeeded, false otherwise.
	 */
	public boolean start() {
		try {
			this.socket = new Socket(this.server, this.portNumber);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		System.out.println("Connected to " + this.server + " on port "
				+ this.portNumber);
		try {
			this.inputStream = new ObjectInputStream(
					this.socket.getInputStream());
			this.outputStream = new ObjectOutputStream(
					this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * This method tries to log in on the server. The connection has already
	 * been established. It then waits for the userdata to arrive.
	 * 
	 * @param login
	 *            The username and password need to be supplied in a Login
	 *            object
	 * @return A thread which listens to all objects which are sent to the
	 *         client via the ObjectStream.
	 */
	public boolean logIn(Login login) {
		// Authentication
		int response = 1;
		do {
			try {
				this.outputStream.writeObject(login);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			try {
				response = this.inputStream.readByte();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// edit the login object
		} while (response != 0);
		this.login = login;
		try {
			this.user = (User) this.inputStream.readObject();
		} catch (IOException e) {
			e.getMessage();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.serverListener = new Thread(new ServerListener(this.inputStream));
		return true;
	}

	/**
	 * Stops the serverListener thread, closes the streams and the socket.
	 */
	public void close() {
		this.serverListener.interrupt();
		try {
			outputStream.close();
		} catch (IOException e) {
			e.getMessage();
		}
		try {
			inputStream.close();
		} catch (IOException e) {
			e.getMessage();
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public void sendMessage(ChatMessage chatMessage) {
		try {
			this.outputStream.writeObject(chatMessage);
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public Thread getServerListener(){
		return this.serverListener;
	}

}
