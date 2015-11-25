package ml.vandenheuvel.TI1216.source.client;

import java.io.ObjectInputStream;

import ml.vandenheuvel.TI1216.source.data.ChatMessage;
import ml.vandenheuvel.TI1216.source.data.Match;

/**
 * This thread runs as soon as the client has logged in. It listens for incoming
 * chatmessages and matches.
 * 
 * @author Bram van den Heuvel
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
