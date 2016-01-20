package ml.vandenheuvel.ti1216.client;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.data.ChatMessage;
import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.data.Match;
import ml.vandenheuvel.ti1216.data.User;
import ml.vandenheuvel.ti1216.gui.Login;
import ml.vandenheuvel.ti1216.gui.Menu;

/**
 * ClientManager is the management class for the client-side application.
 */
public class ClientManager extends Application {

	/**
	 * The class that polls for new chat messages.
	 */
	private ChatPoller chatPoller;
	
	/**
	 * The class that polls for new matches.
	 */
	private MatchPoller matchPoller;
	
	private Credentials credentials;
	
	private Screen window;
	
	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");
	
	/**
	 * Boots up the application.
	 */
	public static void main(String[] args) {
		if(args.length == 0){
			logger.setLevel(Level.WARNING);
			logger.warning("Assuming loglevel \"WARNING\".");
		}
		else if(args.length == 1){
			switch (args[5]) {
			case "OFF":
				logger.setLevel(Level.OFF);
				break;
			case "SEVERE":
				logger.setLevel(Level.SEVERE);
				break;
			case "INFO":
				logger.setLevel(Level.INFO);
				break;
			case "FINE":
				logger.setLevel(Level.FINE);
				break;
			case "FINER":
				logger.setLevel(Level.FINER);
				break;
			case "FINEST":
				logger.setLevel(Level.FINEST);
				break;
			case "ALL":
				logger.setLevel(Level.ALL);
				break;
			default:
				logger.setLevel(Level.WARNING);
			}
		}
		else {
			logger.severe("Too many arguments. Only give the loglevel as argument.");
			return;
		}
		
		try {
			logger.addHandler(new FileHandler("%t/TwoBrains.log"));
			logger.info("Logging to " + System.getProperty("java.io.tmpdir") + "/TwoBrains.log" + " using loglevel "
					+ logger.getLevel().getLocalizedName());
		} catch (SecurityException e) {
			logger.log(Level.WARNING, "Could not obtain the right permissions to write the logfile in "
					+ System.getProperty("java.io.tmpdir"), e);
		} catch (IOException e) {
			logger.log(Level.WARNING, "Could not write in " + System.getProperty("java.io.tmpdir"), e);
		}
		
		logger.fine("Launching main application...");
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		new Login(this).display();
	}
	
	/**
	 * Gets the credentials.
	 * 
	 * @return the credentials
	 */
	public Credentials getCredentials() {
		return credentials;
	}
	

	/**
	 * Sets the credentials.
	 * 
	 * @param credentials the credentials to set
	 */
	public boolean login(Credentials credentials) {
		User user = ServerCommunicator.login(credentials);
		if (user != null) {
			logger.info("Connected successfully.");
			this.credentials = credentials;
			if (this.chatPoller != null) {
				this.chatPoller.stop();
			}
			if (this.matchPoller != null) {
				this.matchPoller.stop();
			}
			this.chatPoller = new ChatPoller(credentials, this);
			this.matchPoller = new MatchPoller(credentials, this);
			return true;
		} else {
			logger.fine("Credentials not registered.");
			return false;
		}
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
