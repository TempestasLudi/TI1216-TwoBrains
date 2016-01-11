package ml.vandenheuvel.ti1216.client;

import java.util.ArrayList;

import ml.vandenheuvel.ti1216.data.ChatMessage;
import ml.vandenheuvel.ti1216.data.Credentials;

/**
 * ChatPoller polls to the server for new chat messages.
 */
public class ChatPoller implements Runnable{
	
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
	public void run() {
		while (this.run) {
			ArrayList<ChatMessage> chats = ServerCommunicator.fetchChats(this.credentials);
			for (int i = 0; i < chats.size(); i++) {
				this.manager.incomingChat(chats.get(i));
			}
			if (chats.size() == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Lets the poller terminate after the next poll.
	 */
	public void stop() {
		this.run = false;
	}
	
}
