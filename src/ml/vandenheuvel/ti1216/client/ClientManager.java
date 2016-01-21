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
import ml.vandenheuvel.ti1216.gui.Home;
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
	
	private User user;
	
	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");
	
	private Login loginWindow;
	
	private Home homeWindow;
	
	private Menu menuWindow;
	
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
		this.loginWindow = new Login(this);
		this.loginWindow.display();
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
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * @return the homeWindow
	 */
	public Home getHomeWindow() {
		return homeWindow;
	}

	
	/**
	 * Sets the credentials.
	 * 
	 * @param credentials the credentials to set
	 */
	private void setCredentials(Credentials credentials) {
		this.credentials = credentials;
		if (this.chatPoller != null) {
			this.chatPoller.stop();
		}
		if (this.matchPoller != null) {
			this.matchPoller.stop();
		}
		if (credentials != null) {
			this.chatPoller = new ChatPoller(credentials, this);
			this.matchPoller = new MatchPoller(credentials, this);
			new Thread(this.chatPoller).start();
	//		TODO: enable
	//		new Thread(this.matchPoller).start();
			this.loginWindow.close();
			this.homeWindow = new Home(this);
			this.menuWindow = new Menu(this);
			this.menuWindow.display();
		}
		else {
			this.chatPoller = null;
			this.matchPoller = null;
		}
	}
	
	/**
	 * Sets the user.
	 * 
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Sets the credentials.
	 * 
	 * @param credentials the credentials to set
	 */
	public boolean login(Credentials credentials) {
		User user = ServerCommunicator.login(credentials);
		if (user != null) {
			logger.info("Logged in successfully.");
			this.setUser(user);
			this.setCredentials(credentials);
			return true;
		} else {
			logger.fine("Credentials not registered.");
			return false;
		}
	}
	
	public boolean register(Credentials credentials, User user) {
		boolean register = ServerCommunicator.register(credentials, user);
		if(register == true) {
			logger.info("Registered successfully.");
			this.setUser(user);
			this.setCredentials(credentials);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateUser(Credentials credentials, User user) {
		boolean update = ServerCommunicator.updateUser(credentials, user);
		if(update == true) {
			logger.info("Profile successfully updated.");
			this.user = user;
			return true;
		} else {
			return false;
		}
	}

	public void showHome() {
		this.menuWindow.displaySub(this.homeWindow.getScene());
	}
	
	public void logout() {
		this.setCredentials(null);
		this.loginWindow = new Login(this);
		this.loginWindow.display();
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
