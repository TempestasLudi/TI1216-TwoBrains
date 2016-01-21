package ml.vandenheuvel.ti1216.client;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.data.Match;

/**
 * MatchPoller polls to the server for new matches.
 */
public class MatchPoller implements Runnable{
	
	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");
	
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
	 * The amount of milliseconds to wait before polling
	 */
	private int interval = 1000;
	
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
	@Override
	public void run() {
		logger.info("Started 'run()' method in MatchPoller.");
		while (this.run) {
			logger.fine("Fetching matches...");
			ArrayList<Match> matches = ServerCommunicator.fetchMatches(this.credentials);
			logger.fine("Received " + Integer.toString(matches.size()) + " matches.");
			for (int i = 0; i < matches.size(); i++) {
				this.manager.incomingMatch(matches.get(i));
			}
			if (matches.isEmpty()) {
				try {
					logger.finest("Sleeping for " + Integer.toString(this.interval) + " milliseconds.");
					Thread.sleep(this.interval);
				} catch (InterruptedException e) {
					logger.log(Level.WARNING, "Failed sleeping for " + Integer.toString(this.interval), e);
				}
			}
		}
		logger.info("MatchPoller thread stopped.");
	}
	
	/**
	 * Lets the poller terminate after the next poll.
	 */
	public void stop() {
		logger.fine("Stopping MatchPoller thread...");
		this.run = false;
	}
	
}
