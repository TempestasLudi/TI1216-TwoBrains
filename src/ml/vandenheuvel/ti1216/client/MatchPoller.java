package ml.vandenheuvel.ti1216.client;

import java.util.ArrayList;

import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.data.Match;

/**
 * MatchPoller polls to the server for new matches.
 */
public class MatchPoller implements Runnable{
	
	/**
	 * Whether the poller should keep polling or not.
	 */
	private boolean run = true;
	
	/**
	 * The credentials of the user matches should be fetched for.
	 */
	private Credentials credentials;
	
	/**
	 * The client-side application manager.
	 */
	private ClientManager manager;
	
	/**
	 * Class constructor.
	 * 
	 * @param credentials the credentials of the user matches should be fetched for
	 * @param manager the manager of the client-side application
	 */
	public MatchPoller(Credentials credentials, ClientManager manager) {
		this.credentials = credentials;
		this.manager = manager;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void run() {
		while (this.run) {
			ArrayList<Match> matches = ServerCommunicator.fetchMatches(this.credentials);
			for (int i = 0; i < matches.size(); i++) {
				this.manager.incomingMatch(matches.get(i));
			}
			if (matches.size() == 0) {
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
