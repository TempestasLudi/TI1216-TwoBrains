package ml.vandenheuvel.ti1216.client;

import ml.vandenheuvel.ti1216.data.ChatMessage;
import ml.vandenheuvel.ti1216.data.Match;
import ml.vandenheuvel.ti1216.gui.Main;

/**
 * ClientManager is the management class for the client-side application.
 */
public class ClientManager {

	/**
	 * The class that polls for new chat messages.
	 */
	private ChatPoller chatPoller;
	
	/**
	 * The class that polls for new matches.
	 */
	private MatchPoller matchPoller;
	
	public ClientManager(String[] args) {
		Main.launch(args);
	}
	
	/**
	 * Boots up the application.
	 */
	public static void main(String[] args) {
		new ClientManager(args);
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

	/**
	 * Handles an incoming chat message.
	 * 
	 * @param message the message to handle
	 */
	public void incomingMatch(Match match) {
		System.out.println(match.getMatchUsername());
		// TODO: do something useful
	}
	
}
