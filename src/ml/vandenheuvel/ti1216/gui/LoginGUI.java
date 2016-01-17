package ml.vandenheuvel.ti1216.gui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.client.ServerCommunicator;
import ml.vandenheuvel.ti1216.data.Credentials;

/**
 * LoginGUI is the first window of the GUI, here u are able to login.
 */
public class LoginGUI {
	
	private LoginGUI(){
		//Private constructor to hide the implicit public one
	}

	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");

	/**
	 * Specifies all the elements of the LoginGUI window.
	 */
	public static void display() {
		/**
		 * Sets the title of the new window.
		 */
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("LoginGUI");

		/**
		 * Make a new GridPane and set the constraints.
		 */
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		/**
		 * Label that says Username.
		 */
		Label nameLabel = new Label("Username: ");
		GridPane.setConstraints(nameLabel, 0, 0);

		/**
		 * InputField to enter your Username.
		 */
		TextField nameInput = new TextField();
		nameInput.setPromptText("Username");
		GridPane.setConstraints(nameInput, 1, 0);

		/**
		 * Label that says Password.
		 */
		Label passLabel = new Label("Password: ");
		GridPane.setConstraints(passLabel, 0, 1);

		/**
		 * InputField to enter your Password;
		 */
		PasswordField passInput = new PasswordField();
		passInput.setPromptText("Password");
		GridPane.setConstraints(passInput, 1, 1);

		/**
		 * Button to log in into the application.
		 */
		Button loginButton = new Button("Log in");
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * Fetches the inputs from nameInput and passInput and checks if the
			 * credentials are in the database already.
			 */
			@Override
			public void handle(ActionEvent e) {
				MainGUI.credentials = new Credentials(nameInput.getText(), passInput.getText());
				if (ServerCommunicator.login(MainGUI.credentials) != null) {
					System.out.println("You are successfully connected");
					logger.info("Connected successfully.");
					MenuGUI.display();
					window.close();
				} else {
					System.out.println("Your credentials are not registered");
					logger.fine("Credentials not registered.");
				}
			}
		});
		GridPane.setConstraints(loginButton, 1, 2);

		/**
		 * Hyperlink to go to the RegisterGUI window when you are not a member
		 * yet.
		 */
		Hyperlink registerLink = new Hyperlink();
		registerLink.setText("Not a member yet? Register now!");

		/**
		 * When u click this link, the RegisterGUI window will open.
		 */
		registerLink.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				RegisterGUI.display();
				window.close();
			}
		});
		GridPane.setConstraints(registerLink, 1, 3);

		/**
		 * Add all the Labels, TextFields and Buttons to the GridPane.
		 */
		grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, registerLink);

		/**
		 * Sets the seize of the window and add all the elements.
		 */
		Scene scene = new Scene(grid, 350, 200);
		window.setScene(scene);
		window.showAndWait();
	}
}
