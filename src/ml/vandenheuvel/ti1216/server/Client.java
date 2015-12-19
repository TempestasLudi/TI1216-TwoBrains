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
	private Socket socket;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	private Thread clientThread;
	// When client logs in, pull it's user data from the DB
	private User user;

	Client(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
		try {
			this.outputStream = new ObjectOutputStream(
					this.socket.getOutputStream());
			this.inputStream = new ObjectInputStream(
					this.socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.clientThread = new ClientThread(this.server, this, outputStream,
				inputStream);
		clientThread.start();
	}

	/**
	 * Send a message object through the object streams to the client.
	 * 
	 * @param chatmessage
	 *            The message to be sent
	 */
	public synchronized void sendMessage(ChatMessage chatmessage) {
		try {
			this.outputStream.writeObject(chatmessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stops the client entirely. First tries to close the streams, then the
	 * socket and lastly removes it's self from the parent server's clientList.
	 */
	public void stopClient() {
		this.clientThread.interrupt();
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public User getUser(){
		return this.user;
	}
}
