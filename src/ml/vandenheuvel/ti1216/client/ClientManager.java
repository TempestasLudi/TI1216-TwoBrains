package ml.vandenheuvel.ti1216.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.data.ChatMessage;
import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.data.Match;
import ml.vandenheuvel.ti1216.data.User;
import ml.vandenheuvel.ti1216.gui.Chat;
import ml.vandenheuvel.ti1216.gui.EditProfile;
import ml.vandenheuvel.ti1216.gui.Home;
import ml.vandenheuvel.ti1216.gui.Login;
import ml.vandenheuvel.ti1216.gui.Menu;

/**
 * ClientManager is the management class for the client-side application.
 * Probably has to be started with 127.0.0.1 127.0.0.1 80 as run configuration.
 */
public class ClientManager extends Application {

	/**
	 * The communicator that handles all communication with the server.
	 */
	public ServerCommunicator communicator;

	/**
	 * The thread that polls for new chat messages.
	 */
	private ChatPoller chatPoller;

	/**
	 * The thread that polls for new matches.
	 */
	private MatchPoller matchPoller;

	private Credentials credentials;
	private User user;

	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");

	private Login loginWindow;
	private Home homeWindow;
	private Menu menuWindow;
	private ArrayList<Chat> chatWindows = new ArrayList<Chat>();

	private ArrayList<ChatMessage> messages = new ArrayList<ChatMessage>();
	private ArrayList<Match> matches = new ArrayList<Match>();

	/**
	 * Boots up the application.
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
			logger.setLevel(Level.SEVERE);
			logger.severe("Cannot start application without target address, target host and portnumber to connect to.");
			return;
		} else if (args.length == 3) {
			logger.setLevel(Level.WARNING);
			logger.warning("Assuming loglevel \"WARNING\".");
		} else if (args.length == 4) {
			switch (args[3]) {
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
		} else {
			logger.setLevel(Level.WARNING);
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
		Application.launch(args);
	}

	/**
	 * Starts the application.
	 */
	public void start(Stage primaryStage) {
		final List<String> parameters = getParameters().getRaw();
		logger.info("Starting new ServerCommunicator with parameters " + parameters.get(0) + " " + parameters.get(1)
				+ " " + parameters.get(2));
		this.communicator = new ServerCommunicator(parameters.get(0), parameters.get(1),
				Integer.valueOf(Integer.parseInt(parameters.get(2))));
		logger.fine("Opening Login window...");
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
	 * @return the messages
	 */
	public ArrayList<ChatMessage> getMessages() {
		return messages;
	}

	/**
	 * @return the matches
	 */
	public ArrayList<Match> getMatches() {
		return matches;
	}

	/**
	 * Sets the credentials.
	 * 
	 * @param credentials
	 *            the credentials to set
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
			new Thread(this.matchPoller).start();
			this.loginWindow.close();
			this.homeWindow = new Home(this);
			this.menuWindow = new Menu(this);
			this.menuWindow.display();
		} else {
			this.chatPoller = null;
			this.matchPoller = null;
			this.chatWindows = new ArrayList<Chat>();
			this.messages = new ArrayList<ChatMessage>();
			this.matches = new ArrayList<Match>();
		}
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Sets the credentials.
	 * 
	 * @param credentials
	 *            the credentials to set
	 */
	public boolean login(Credentials credentials) {
		JSONObject userData = this.communicator.login(credentials);
		if (userData != null) {
			logger.info("Logged in successfully.");
			this.setUser(User.fromJSON(userData.getJSONObject("user")));
			JSONArray matchData = userData.getJSONArray("matches");
			for (int i = 0; i < matchData.length(); i++) {
				this.matches.add(Match.fromJSON(matchData.getJSONObject(i)));
			}
			JSONArray messageData = userData.getJSONArray("messages");
			for (int i = 0; i < messageData.length(); i++) {
				this.messages.add(ChatMessage.fromJSON(messageData.getJSONObject(i)));
			}
			this.setCredentials(credentials);
			return true;
		} else {
			logger.fine("Credentials not registered.");
			return false;
		}
	}

	/**
	 * Tries to register a user.
	 * 
	 * @param credentials
	 *            the credentials to register by
	 * @param user
	 *            the user to register
	 * @return whether the user could be registered or not
	 */
	public boolean register(Credentials credentials, User user) {
		boolean register = this.communicator.register(credentials, user);
		if (register == true) {
			logger.info("Registered successfully.");
			this.setUser(user);
			this.setCredentials(credentials);
			this.menuWindow.displaySub(new EditProfile(this).getScene());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Tries to update a user.
	 * 
	 * @param credentials
	 *            the credentials to authenticate with
	 * @param user
	 *            the user to update
	 * @return whether the user could be updated or not
	 */
	public boolean updateUser(Credentials credentials, User user) {
		boolean update = this.communicator.updateUser(credentials, user);
		if (update == true) {
			logger.info("Profile successfully updated.");
			this.user = user;
			return true;
		} else {
			return false;
		}
	}

	public void openChat(String username) {
		for (int i = 0; i < this.chatWindows.size(); i++) {
			if (this.chatWindows.get(i).getUsername().equals(username)) {
				this.chatWindows.get(i).toFront();
				return;
			}
		}
		Chat newWindow = new Chat(this, username);
		this.chatWindows.add(newWindow);
		newWindow.display();
	}

	public boolean sendChat(ChatMessage message) {
		if (this.communicator.sendChat(this.credentials, message)) {
			return true;
		} else {
			return false;
		}
	}

	public void discardMatch(Match match) {
		this.matches.remove(match);
		match.setApproved(false);
		this.communicator.updateMatch(this.credentials, match);
	}

	/**
	 * Puts the home screen on in the menu.
	 */
	public void showHome() {
		this.menuWindow.displaySub(this.homeWindow.getScene());
	}

	/**
	 * Returns to login screen.
	 */
	public void logout() {
		this.setCredentials(null);
		this.loginWindow = new Login(this);
		this.loginWindow.display();
	}

	/**
	 * Handles an incoming chat message.
	 * 
	 * @param message
	 *            the message to handle
	 */
	public void incomingChat(ChatMessage message) {
		Platform.runLater(new Runnable() {
			public void run() {
				openChat(message.getSender());
				for (int i = 0; i < chatWindows.size(); i++) {
					if (chatWindows.get(i).getUsername().equals(message.getSender())) {
						chatWindows.get(i).newChat(message);
						return;
					}
				}
			}
		});
	}

	/**
	 * Handles an incoming chat message.
	 * 
	 * @param message
	 *            the message to handle
	 */
	public void incomingMatch(Match match) {
		this.homeWindow.addMatch(match);
	}

	/**
	 * Stops all running processes.
	 */
	public void stop() {
		if (this.chatPoller != null) {
			this.chatPoller.stop();
		}
		if (this.matchPoller != null) {
			this.matchPoller.stop();
		}
		for (int i = 0; i < this.chatWindows.size(); i++) {
			this.chatWindows.get(i).close();
		}
	}

}
