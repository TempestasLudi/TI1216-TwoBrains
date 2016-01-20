package ml.vandenheuvel.ti1216.gui;

import java.util.logging.Logger;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.client.ClientManager;
import ml.vandenheuvel.ti1216.data.Credentials;

/**
 * Login is the first window of the GUI, here u are able to login.
 */
public class Login {

	private ClientManager manager;

	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");

	public Login(ClientManager manager) {
		this.manager = manager;
	}

	/**
	 * Specifies all the elements of the Login window.
	 */
	public void display() {
		/**
		 * Sets the title of the new window.
		 */
		Stage window = new Stage();
		window.setResizable(false);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Login");

		/**
		 * Make a new GridPane and set the constraints.
		 */
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		Label messageLabel = new Label("");
		GridPane.setConstraints(messageLabel, 0, 0, 2, 1);

		Label messageLabel = new Label("");
		GridPane.setConstraints(messageLabel, 0, 0, 2, 1);

		/**
		 * Label that says Username.
		 */
		Label nameLabel = new Label("Username: ");
		GridPane.setConstraints(nameLabel, 0, 1);
		
		/**
		 * InputField to enter your Username.
		 */
		TextField nameInput = new TextField();
		nameInput.setPromptText("Username");
		GridPane.setConstraints(nameInput, 1, 1);

		/**
		 * Label that says Password.
		 */
		Label passLabel = new Label("Password: ");
		GridPane.setConstraints(passLabel, 0, 2);

		/**
		 * InputField to enter your Password;
		 */
		PasswordField passInput = new PasswordField();
		passInput.setPromptText("Password");
		GridPane.setConstraints(passInput, 1, 2);

		/**
		 * Button to log in into the application.
		 */
		Button loginButton = new Button("Log in");
		loginButton.setOnAction(e -> {
			if (manager.login(new Credentials(nameInput.getText(), passInput.getText()))) {
				window.close();
			} else {
				messageLabel.setText("Invalid credentials.");
			}
		});
		GridPane.setConstraints(loginButton, 1, 3);

		/**
		 * Hyperlink to go to the RegisterGUI window when you are not a member
		 * yet.
		 */
		Hyperlink registerLink = new Hyperlink();
		registerLink.setText("Not a member yet? Register now!");

		/**
		 * When u click this link, the RegisterGUI window will open.
		 */
		registerLink.setOnAction(e -> {
				Register.display();
				window.close();
		});
		GridPane.setConstraints(registerLink, 1, 4);

		/**
		 * Add all the Labels, TextFields and Buttons to the GridPane.
		 */
		grid.getChildren().addAll(messageLabel, nameLabel, nameInput, passLabel, passInput, loginButton, registerLink);

		/**
		 * Sets the size of the window and add all the elements.
		 */
		Scene scene = new Scene(grid, 350, 200);
		scene.getStylesheets().add("ml/vandenheuvel/ti1216/gui/Gui.css");
		window.setScene(scene);
		window.showAndWait();
	}
}
