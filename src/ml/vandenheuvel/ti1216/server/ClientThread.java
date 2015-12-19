package ml.vandenheuvel.ti1216.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ml.vandenheuvel.ti1216.data.*;

/**
 * The thread that handles all incoming communication from the client and
 * handles the first authorization.
 * 
 * @author Bram van den Heuvel
 *
 */
public class ClientThread extends Thread {
	private Server server;
	private Client client;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private volatile boolean running = true;

	ClientThread(Server server, Client client, ObjectOutputStream outputStream,
			ObjectInputStream inputStream) {
		this.server = server;
		this.client = client;
		this.outputStream = outputStream;
		this.inputStream = inputStream;
	}

	/**
	 * This thread first sets up a connection, then listens to the client on the
	 * socket.
	 */
	@Override
	public void run() {

		try {
			Credentials credentials = (Credentials) inputStream.readObject();

			// TODO HTTP message moet zeggen of er gesignupt moet worden
			// If the user wants to sign up, let him try
				while (!this.server.getDatabaseCommunicator().canRegister(
						new Credentials(credentials.getUsername(), ""))) {
					// Feedback: 1 means failed
					this.outputStream.writeByte(1);
					credentials = (Credentials) inputStream.readObject();
				}
				// Feedback: 0 means success
				this.outputStream.writeByte(0);

				// Save this new user
				// User newUser = (User) inputStream.readObject();
				// this.server.getDatabaseCommunicator().save(newUser);

			// Logging in. Password isn't checked here, needs more functionality
			while (!this.server.getDatabaseCommunicator().canLogin(
					new Credentials(credentials.getUsername(), ""))) {
				// Feedback: could not log in
				this.outputStream.writeByte(1);
			}
			this.outputStream.writeByte(0);
			// Send personal preferences, chats, matches, everything that is
			// seen when logged in to the application.
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Sending the userdata
		try {
			this.outputStream.writeObject(this.client.getUser());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Everything below runs as long as the client is online.
		while (running) {
			try {
				Object object = inputStream.readObject();
				if (object instanceof ChatMessage) {
					// Save message
					// Send to receiver if possible
				}
				if (object instanceof User) {
					// Edit preferences in database
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
