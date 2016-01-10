package ml.vandenheuvel.ti1216.client;

import java.io.ObjectInputStream;

import ml.vandenheuvel.ti1216.data.ChatMessage;
import ml.vandenheuvel.ti1216.data.Match;

/**
 * This thread runs as soon as the client has logged in. It listens for incoming
 * chatmessages and matches.
 *
 */
public class ServerListener implements Runnable {

	private ObjectInputStream inputStream;

	ServerListener(ObjectInputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * The actual running thread.
	 */
	@Override
	public void run() {
		try {
			Object receivedObject = this.inputStream.readObject();
			if (receivedObject instanceof ChatMessage) {
				// actions
			}
			if (receivedObject instanceof Match) {
				// actions
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
