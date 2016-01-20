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
import ml.vandenheuvel.ti1216.data.Grade;
import ml.vandenheuvel.ti1216.data.User;

/**
 * EditProfile allows the user to change his personal information.
 */
public class EditProfile {

	private ClientManager manager;

	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");

	public EditProfile(ClientManager manager) {
		this.manager = manager;
	}

	/**
	 * Sets all the elements of the EditProfile window.
	 */
	public void display() {
		/**
		 * Sets the title of the new window and fetches the user from the
		 * database.
		 */
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("EditProfile");

		/**
		 * Makes a new GridPane and set the constraints.
		 */
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label messageLabel = new Label("");
		GridPane.setConstraints(messageLabel, 0, 0, 2, 1);

		/**
		 * Label that shows that the user can change his setting here.
		 */
		Label textLabel = new Label("You can change your settings here: ");
		GridPane.setConstraints(textLabel, 0, 1, 3, 1);

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
		GridPane.setConstraints(passInput1, 1, 2, 2, 1);

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
		GridPane.setConstraints(passInput2, 1, 3, 2, 1);

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
		GridPane.setConstraints(postalInput, 1, 4, 2, 1);

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
		GridPane.setConstraints(descriptionInput, 1, 5, 2, 1);

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
		GridPane.setConstraints(courseIDInput, 1, 6, 2, 1);

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
		GridPane.setConstraints(gradeInput, 1, 7, 2, 1);

		/**
		 * Button to update the User profile.
		 */
		Button updateButton = new Button("Update");
		updateButton.setOnAction(e -> {
			Grade grade = new Grade(courseIDInput.getText(), Integer.parseInt(gradeInput.getText()));
			Grade[] gradelist = new Grade[1];
			gradelist[0] = grade;
			User user = new User(this.manager.getUser().getUsername(), postalInput.getText(), descriptionInput.getText(), gradelist);
			Credentials credentials = new Credentials(this.manager.getCredentials().getUsername(), passInput1.getText());
			if ((passInput1.getText().equals(passInput2.getText())) && (manager.updateUser(credentials, user))) {
				logger.fine("Profile successfully updated.");
				window.close();
			} else {
				logger.fine("Wrong data entered.");
			}
		});
		GridPane.setConstraints(updateButton, 1, 8);

		/**
		 * Button to cancel updating the User profile.
		 */
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> {
			window.close();
		});
		GridPane.setConstraints(cancelButton, 2, 8);

		/**
		 * Adds all the Labels, TextFields and Buttons to the GridPane.
		 */
		grid.getChildren().addAll(messageLabel, textLabel, passLabel1, passInput1, passLabel2,
				passInput2, postalLabel, postalInput, descriptionLabel, descriptionInput, courseIDLabel, courseIDInput,
				gradeLabel, gradeInput, updateButton, cancelButton);

		/**
		 * Sets the seize of the window and adds all the new elements.
		 */
		Scene scene = new Scene(grid, 500, 375);
		scene.getStylesheets().add("ml/vandenheuvel/ti1216/gui/Gui.css");
		window.setScene(scene);
		window.showAndWait();
	}

}
