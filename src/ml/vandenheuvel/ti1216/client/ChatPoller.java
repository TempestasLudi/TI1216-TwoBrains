package ml.vandenheuvel.ti1216.client;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import ml.vandenheuvel.ti1216.data.ChatMessage;
import ml.vandenheuvel.ti1216.data.Credentials;

/**
 * ChatPoller polls to the server for new chat messages.
 */
public class ChatPoller implements Runnable{
	
	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");
	
	/**
	 * Whether the poller should keep polling or not.
	 */
	private boolean run = true;
	
	/**
	 * The credentials of the user messages should be fetched for.
	 */
	private Credentials credentials;
	
	/**
	 * The client-side application manager.
	 */
	private ClientManager manager;
	
	/**
	 * The amount of milliseconds to wait before polling
	 */
	private int interval = 1000;
	
	/**
	 * Class constructor.
	 * 
	 * @param credentials the credentials of the user messages should be fetched for
	 * @param manager the manager of the client-side application
	 */
	public ChatPoller(Credentials credentials, ClientManager manager) {
		this.credentials = credentials;
		this.manager = manager;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		logger.info("Started 'run()' method in ChatPoller.");
		while (this.run) {
			logger.fine("Fetching chats...");
			ArrayList<ChatMessage> chats = ServerCommunicator.fetchChats(this.credentials);
			logger.fine("Received " + Integer.toString(chats.size()) + " chats.");
			for (int i = 0; i < chats.size(); i++) {
				this.manager.incomingChat(chats.get(i));
			}
			if (chats.isEmpty()) {
				try {
					logger.finest("Sleeping for " + Integer.toString(this.interval) + " milliseconds.");
					Thread.sleep(this.interval);
				} catch (InterruptedException e) {
					logger.log(Level.WARNING, "Failed sleeping for " + Integer.toString(this.interval), e);
				}
			}
		}
		logger.info("ChatPoller thread stopped.");
	}
	
	/**
	 * Lets the poller terminate after the next poll.
	 */
	public void stop() {
		logger.fine("Stopping ChatPoller thread...");
		this.run = false;
	}
	
}
