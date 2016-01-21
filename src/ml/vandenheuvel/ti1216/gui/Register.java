package ml.vandenheuvel.ti1216.gui;

import java.util.logging.Logger;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.client.ClientManager;
import ml.vandenheuvel.ti1216.client.ServerCommunicator;
import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.data.Grade;
import ml.vandenheuvel.ti1216.data.User;

/**
 * In Register you can sign up for this application if u don't have an account
 * yet.
 */
public class Register {

	private ClientManager manager;

	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");

	public Register(ClientManager manager) {
		this.manager = manager;
	}

	/**
	 * Specifies all the elements of the Register window.
	 */
	public void display() {
		logger.fine("Displaying Register window...");
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Register");

		/**
		 * Make a new GridPane and set the constraints.
		 */
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label messageLabel = new Label("");
		GridPane.setConstraints(messageLabel, 0, 0, 2, 1);

		/**
		 * Label that says Username.
		 */
		Label nameLabel = new Label("Username: ");
		GridPane.setConstraints(nameLabel, 0, 1);

		/**
		 * InputField to enter the Username.
		 */
		TextField nameInput = new TextField();
		nameInput.setPromptText("Username");
		GridPane.setConstraints(nameInput, 1, 1);

		/**
		 * Label that says Password.
		 */
		Label passLabel1 = new Label("Password: ");
		GridPane.setConstraints(passLabel1, 0, 2);

		/**
		 * InputField to enter the Password.
		 */
		PasswordField passInput1 = new PasswordField();
		passInput1.setPromptText("Password");
		GridPane.setConstraints(passInput1, 1, 2);

		/**
		 * Label that says Confirm Password.
		 */
		Label passLabel2 = new Label("Confirm Password: ");
		GridPane.setConstraints(passLabel2, 0, 3);

		/**
		 * InputField to confirm the Password.
		 */
		PasswordField passInput2 = new PasswordField();
		passInput2.setPromptText("Password");
		GridPane.setConstraints(passInput2, 1, 3);

		/**
		 * Label that says PostalCode.
		 */
		Label postalLabel = new Label("PostalCode: ");
		GridPane.setConstraints(postalLabel, 0, 4);

		/**
		 * InputField to enter the PostalCode.
		 */
		TextField postalInput = new TextField();
		postalInput.setPromptText("PostalCode");
		GridPane.setConstraints(postalInput, 1, 4);

		/**
		 * Label that says Description.
		 */
		Label descriptionLabel = new Label("Description: ");
		GridPane.setConstraints(descriptionLabel, 0, 5);

		/**
		 * InputField to enter the Description.
		 */
		TextField descriptionInput = new TextField();
		descriptionInput.setPromptText("Description");
		GridPane.setConstraints(descriptionInput, 1, 5);

		/**
		 * Button to sign up for the application.
		 */
		Button registerButton = new Button("Register");
		registerButton.setOnAction(e -> {
			Grade[] gradelist = new Grade[0];
			if ((passInput1.getText().equals(passInput2.getText())) && (manager
					.register(new Credentials(nameInput.getText(), passInput1.getText()), new User(nameInput.getText(),
							postalInput.getText(), descriptionInput.getText(), gradelist, false)))) {
				window.close();
			} else {
				messageLabel.setText("Wrong data entered.");
			}
		});
		GridPane.setConstraints(registerButton, 1, 8);

		/**
		 * Adds all the Labels, TextFields, Buttons and Sliders to the GridPane.
		 */
		grid.getChildren().addAll(messageLabel, nameLabel, nameInput, passLabel1, passInput1, passLabel2, passInput2,
				postalLabel, postalInput, descriptionLabel, descriptionInput, registerButton);

		/**
		 * Sets the seize of the window and adds all the elements.
		 */
		Scene scene = new Scene(grid, 500, 400);
		scene.getStylesheets().add("ml/vandenheuvel/ti1216/gui/Gui.css");
		window.setScene(scene);
		window.showAndWait();
	}
}
