package ml.vandenheuvel.ti1216.client;

import ml.vandenheuvel.ti1216.data.ChatMessage;

/**
 * ClientManager is the managment class for the client-side application.
 */
public class ClientManager {
	
	/**
	 * The class that polls for new chat messages.
	 */
	private ChatPoller poller;
	
	/**
	 * Boots up the application.
	 */
	public static void main() {
		new ClientManager();
	}
	
	public ClientManager() {
		
	}

	/**
	 * Handles an incoming chat message.
	 * 
	 * @param message the message to handle
	 */
	public void incomingChat(ChatMessage message) {
		System.out.println(message.getMessage());
		// TODO: do something useful
	}
	
}
