package ml.vandenheuvel.ti1216.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.data.Credentials;
/**
 * MainGUI is the startpoint of the GUI.
 * @author stefan
 */
public class MainGUI extends Application {

	/**
	 * The credentials of the user that he uses to sign in.
	 */
	protected static Credentials credentials; 
	
	/**
	 * Runner method; launch the GUI application.
	 * 
	 * @param args no params to give at the start.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Accessor method for the credentials.
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
		MenuGUI.display();
	}

}
