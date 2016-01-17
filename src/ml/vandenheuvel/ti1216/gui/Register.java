package ml.vandenheuvel.ti1216.gui;

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
 * In Register you can sign up for this application if u don't have an
 * account yet.
 */
public class Register {

	/**
	 * The user that signs up with his data.
	 */
	private static User user;

	/**
	 * The credentials of the user that he uses to sign up.
	 */
	private static Credentials credentials;

	/**
	 * The grades of the user's courses.
	 */
	private static Grade grade;

	private Register() {
		// To hide the implicit public one
	}

	/**
	 * Specifies all the elements of the Register window.
	 */
	public static void display() {
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

		Label nameLabel = new Label("Username: ");
		GridPane.setConstraints(nameLabel, 0, 0);

		/**
		 * InputField to enter the Username.
		 */
		TextField nameInput = new TextField();
		nameInput.setPromptText("Username");
		GridPane.setConstraints(nameInput, 1, 0);

		/**
		 * Label that says Password.
		 */
		Label passLabel1 = new Label("Password: ");
		GridPane.setConstraints(passLabel1, 0, 1);

		/**
		 * InputField to enter the Password.
		 */
		PasswordField passInput1 = new PasswordField();
		passInput1.setPromptText("Password");
		GridPane.setConstraints(passInput1, 1, 1);

		/**
		 * Label that says Confirm Password.
		 */
		Label passLabel2 = new Label("Confirm Password: ");
		GridPane.setConstraints(passLabel2, 0, 2);

		/**
		 * InputField to confirm the Password.
		 */
		PasswordField passInput2 = new PasswordField();
		passInput2.setPromptText("Password");
		GridPane.setConstraints(passInput2, 1, 2);

		/**
		 * Label that says PostalCode.
		 */
		Label postalLabel = new Label("PostalCode: ");
		GridPane.setConstraints(postalLabel, 0, 3);

		/**
		 * InputField to enter the PostalCode.
		 */
		TextField postalInput = new TextField();
		postalInput.setPromptText("PostalCode");
		GridPane.setConstraints(postalInput, 1, 3);

		/**
		 * Label that says Description.
		 */
		Label descriptionLabel = new Label("Description: ");
		GridPane.setConstraints(descriptionLabel, 0, 4);

		/**
		 * InputField to enter the Description.
		 */
		TextField descriptionInput = new TextField();
		descriptionInput.setPromptText("Description");
		GridPane.setConstraints(descriptionInput, 1, 4);

		/**
		 * Label that says CourseID.
		 */
		Label courseIDLabel = new Label("CourseID: ");
		GridPane.setConstraints(courseIDLabel, 0, 5);

		/**
		 * InputField to enter the CourseID
		 */
		TextField courseIDInput = new TextField();
		courseIDInput.setPromptText("CourseID");
		GridPane.setConstraints(courseIDInput, 1, 5);

		/**
		 * Label that says Grade.
		 */
		Label gradeLabel = new Label("Grade: ");
		GridPane.setConstraints(gradeLabel, 0, 6);

		/**
		 * Slider to enter the Grade.
		 */
		Slider gradeInput = new Slider(1, 10, 5);
		gradeInput.setMax(10);
		gradeInput.setMin(1);
		gradeInput.setShowTickMarks(true);
		gradeInput.setShowTickLabels(true);
		gradeInput.setMajorTickUnit(1);
		gradeInput.setMinorTickCount(1);
		gradeInput.setBlockIncrement(1);
		GridPane.setConstraints(gradeInput, 1, 6);

		/**
		 * Label that shows the value of the Slider.
		 */
		Label gradeValue = new Label(Double.toString(gradeInput.getValue()));
		GridPane.setConstraints(gradeValue, 2, 6);

		/**
		 * Button to sign up for the application.
		 */
		Button loginButton = new Button("Register");
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * Fetches the input from all the textfields above and puts them
			 * into the database as a new user.
			 */
			@Override
			public void handle(ActionEvent e) {
				grade = new Grade(courseIDInput.getText(), (int) gradeInput.getValue());
				Grade[] gradelist = new Grade[1];
				gradelist[0] = grade;
				credentials = new Credentials(nameInput.getText(), passInput1.getText());
				user = new User(nameInput.getText(), postalInput.getText(), descriptionInput.getText(), gradelist);
				if (ServerCommunicator.register(credentials, user) && passInput1.equals(passInput2)) {
					System.out.println("You are successfully registered");
					Login.display();
					window.close();
				} else {
					System.out.println("You did not enter the correct data");
				}
			}
		});
		GridPane.setConstraints(loginButton, 1, 7);

		/**
		 * Adds all the Labels, TextFields, Buttons and Sliders to the GridPane.
		 */
		grid.getChildren().addAll(nameLabel, nameInput, passLabel1, passInput1, passLabel2, passInput2, postalLabel,
				postalInput, descriptionLabel, descriptionInput, courseIDLabel, courseIDInput, gradeLabel, gradeInput,
				gradeValue, loginButton);

		/**
		 * Sets the seize of the window and adds all the elements.
		 */
		Scene scene = new Scene(grid, 500, 400);
		window.setScene(scene);
		window.showAndWait();
	}

}
