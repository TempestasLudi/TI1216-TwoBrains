package ml.vandenheuvel.ti1216.gui;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.data.Credentials;

/**
 * Main is where the GUI is launched.
 */
public class Main extends Application {

	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");

	/**
	 * The credentials of the user that he uses to sign in.
	 */
	protected static Credentials credentials; 
	
	/**
	 * Runner method; launch the GUI application.
	 * 
	 * @param args possible argument for the loglevel to be used
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

	/**
	 * Get method for the credentials.
	 * 
	 * @return Credentials that represent the credentials.
	 */
	public static Credentials getCredentials() {
		return credentials;
	}

	/**
	 * Starter method; opens the LoginGUI window.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		logger.fine("Opening the LoginGUI window...");
		Login.display();
	}

}
