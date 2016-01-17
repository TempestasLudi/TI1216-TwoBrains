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
import ml.vandenheuvel.ti1216.data.Grade;
import ml.vandenheuvel.ti1216.data.User;

/**
 * EditProfile allows the user to change his personal information.
 */
public class EditProfile {

	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");

	/**
	 * Sets all the elements of the EditProfile window.
	 */

	private EditProfile() {
		// Private constructor to hide the implicit public one
	}

	public static void display() {
		/**
		 * Sets the title of the new window and fetches the user from the
		 * database.
		 */
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("EditProfile");
		ServerCommunicator.login(Main.credentials);

		/**
		 * Makes a new GridPane and set the constraints.
		 */
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		/**
		 * Label that shows that the user can change his setting here.
		 */
		Label textLabel = new Label("You can change your settings here: ");
		GridPane.setConstraints(textLabel, 0, 0);

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
		TextField passInput1 = new TextField();
		passInput1.setPromptText("Password");
		GridPane.setConstraints(passInput1, 1, 2);

		/**
		 * Label that says Confirm Password.
		 */
		Label passLabel2 = new Label("Confirm Password: ");
		GridPane.setConstraints(passLabel2, 0, 3);

		/**
		 * InputField to enter the Password.
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
		GridPane.setConstraints(postalInput, 2, 4);

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
		 * Label that says CourseID.
		 */
		Label courseIDLabel = new Label("CourseID: ");
		GridPane.setConstraints(courseIDLabel, 0, 6);

		/**
		 * InputField to enter the CourseID.
		 */
		TextField courseIDInput = new TextField();
		courseIDInput.setPromptText("CourseID");
		GridPane.setConstraints(courseIDInput, 1, 6);

		/**
		 * Label that says Grade.
		 */
		Label gradeLabel = new Label("Grade: ");
		GridPane.setConstraints(gradeLabel, 0, 7);

		/**
		 * InputField to enter the Grade.
		 */
		TextField gradeInput = new TextField();
		gradeInput.setPromptText("Grade");
		GridPane.setConstraints(gradeInput, 1, 7);

		/**
		 * Button to update the User profile.
		 */
		Button updateButton = new Button("Update");
		updateButton.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * Fetches the input from all the text fields above and puts them
			 * into the database as an updated user.
			 */
			@Override
			public void handle(ActionEvent e) {
				Grade grade = new Grade(courseIDInput.getText(), gradeInput.getAnchor());
				Grade[] gradelist = new Grade[1];
				gradelist[0] = grade;
				Credentials credentials = new Credentials(nameInput.getText(), passInput1.getText());
				User user = new User(nameInput.getText(), postalInput.getText(), descriptionInput.getText(), gradelist);
				if (ServerCommunicator.updateUser(credentials, user) && passInput1.equals(passInput2)) {
					System.out.println("Your profile is successfully updated");
					logger.fine("Profile successfully updated.");
					Menu.display();
					window.close();
				} else {
					System.out.println("You did not enter the correct data");
					logger.fine("Wrong data entered.");
				}
			}
		});
		GridPane.setConstraints(updateButton, 1, 8);

		/**
		 * Adds all the Labels, TextFields and Buttons to the GridPane.
		 */
		grid.getChildren().addAll(textLabel, nameLabel, nameInput, passLabel1, passInput1, passLabel2, passInput2,
				postalLabel, postalInput, descriptionLabel, descriptionInput, courseIDLabel, courseIDInput, gradeLabel,
				gradeInput, updateButton);

		/**
		 * Sets the seize of the window and adds all the new elements.
		 */
		Scene scene = new Scene(grid, 500, 500);
		window.setScene(scene);
		window.showAndWait();
	}
	
	
}
