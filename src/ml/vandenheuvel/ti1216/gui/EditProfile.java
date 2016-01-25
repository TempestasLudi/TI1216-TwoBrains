package ml.vandenheuvel.ti1216.gui;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ml.vandenheuvel.ti1216.client.ClientManager;
import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.data.Faculty;
import ml.vandenheuvel.ti1216.data.Grade;
import ml.vandenheuvel.ti1216.data.Program;
import ml.vandenheuvel.ti1216.data.User;

/**
 * EditProfile allows the user to change his personal information.
 */
public class EditProfile {

	private ClientManager manager;

	private Scene scene;

	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");

	public EditProfile(ClientManager manager) {
		this.manager = manager;
		this.renderScene();
	}

	/**
	 * Creates a scene, containing a main VBox layout element that contains a
	 * couple of inputs and labels.
	 */
	private void renderScene() {
		VBox wrapper = new VBox();
		wrapper.setAlignment(Pos.CENTER);
		wrapper.setMaxWidth(800);
		ObservableList<Node> wrapperChildren = wrapper.getChildren();

		int labelWidth = 150;

		Label messageLabel = new Label("");
		wrapperChildren.add(messageLabel);

		Label description = new Label("You can change your settings here: ");
		wrapperChildren.add(description);

		HBox passRow1 = new HBox();
		Label passLabel1 = new Label("Password: ");
		passLabel1.setMinWidth(labelWidth);
		TextField passInput1 = new PasswordField();
		passInput1.setPromptText("Password");
		passInput1.setMinWidth(250);
		passRow1.getChildren().addAll(passLabel1, passInput1);
		passRow1.setAlignment(Pos.CENTER);
		wrapperChildren.add(passRow1);

		HBox passRow2 = new HBox();
		Label passLabel2 = new Label("Confirm password: ");
		passLabel2.setMinWidth(labelWidth);
		TextField passInput2 = new PasswordField();
		passInput2.setPromptText("Confirm password");
		passInput2.setMinWidth(250);
		passRow2.getChildren().addAll(passLabel2, passInput2);
		passRow2.setAlignment(Pos.CENTER);
		wrapperChildren.add(passRow2);

		HBox postalRow = new HBox();
		Label postalLabel = new Label("PostalCode: ");
		postalLabel.setMinWidth(labelWidth);
		TextField postalInput = new TextField();
		postalInput.setPromptText("PostalCode");
		postalInput.setMinWidth(250);
		postalInput.setText(this.manager.getUser().getPostalCode());
		postalRow.getChildren().addAll(postalLabel, postalInput);
		postalRow.setAlignment(Pos.CENTER);
		wrapperChildren.add(postalRow);

		HBox descriptionRow = new HBox();
		Label descriptionLabel = new Label("Description: ");
		descriptionLabel.setMinWidth(labelWidth);
		TextField descriptionInput = new TextField();
		descriptionInput.setPromptText("Description");
		descriptionInput.setMinWidth(250);
		descriptionInput.setText(this.manager.getUser().getDescription());
		descriptionRow.getChildren().addAll(descriptionLabel, descriptionInput);
		descriptionRow.setAlignment(Pos.CENTER);
		wrapperChildren.add(descriptionRow);

		HBox oldPassRow = new HBox();
		Label oldPassLabel = new Label("Old password: ");
		oldPassLabel.setMinWidth(labelWidth);
		TextField oldPassInput = new PasswordField();
		oldPassInput.setPromptText("Password");
		oldPassInput.setMinWidth(250);
		oldPassRow.getChildren().addAll(oldPassLabel, oldPassInput);
		oldPassRow.setAlignment(Pos.CENTER);
		wrapperChildren.add(oldPassRow);

		wrapperChildren.add(new Label("Grades:"));

		List<Faculty> faculties = this.manager.communicator.fetchFaculties(this.manager.getCredentials());
		ObservableList<String> facultyOptions = FXCollections.observableArrayList();
		for (int i = 0; i < faculties.size(); i++) {
			facultyOptions.add(faculties.get(i).getID());
		}

		HBox facultyRow = new HBox();
		Label facultyLabel = new Label("Faculty: ");
		facultyLabel.setMinWidth(labelWidth);
		ComboBox<String> facultyInput = new ComboBox<>(facultyOptions);
		facultyInput.setPromptText("Select a faculty");
		facultyInput.setMinWidth(250);
		facultyRow.getChildren().addAll(facultyLabel, facultyInput);
		facultyRow.setAlignment(Pos.CENTER);
		wrapperChildren.add(facultyRow);

		ObservableList<String> programOptions = FXCollections.observableArrayList();

		HBox programRow = new HBox();
		Label programLabel = new Label("Program: ");
		programLabel.setMinWidth(labelWidth);
		ComboBox<String> programInput = new ComboBox<>(programOptions);
		programInput.setPromptText("Select a program");
		programInput.setMinWidth(250);
		programRow.getChildren().addAll(programLabel, programInput);
		programRow.setAlignment(Pos.CENTER);
		wrapperChildren.add(programRow);

		ObservableList<String> courseOptions = FXCollections.observableArrayList();

		HBox courseRow = new HBox();
		Label courseLabel = new Label("Course: ");
		courseLabel.setMinWidth(labelWidth);
		ComboBox<String> courseInput = new ComboBox<>(courseOptions);
		courseInput.setPromptText("Select a course");
		courseInput.setMinWidth(250);
		courseRow.getChildren().addAll(courseLabel, courseInput);
		courseRow.setAlignment(Pos.CENTER);
		wrapperChildren.add(courseRow);

		facultyInput.setOnAction(e -> {
			programOptions.clear();
			String facultyId = facultyInput.getValue();
			Faculty faculty = null;
			for (int i = 0; i < faculties.size(); i++) {
				if (faculties.get(i).getID() == facultyId) {
					faculty = faculties.get(i);
					break;
				}
			}
			for (int i = 0; i < faculty.getPrograms().size(); i++) {
				programOptions.add(faculty.getPrograms().get(i).getID());
			}
		});

		programInput.setOnAction(e -> {
			courseOptions.clear();
			String facultyId = facultyInput.getValue();
			Faculty faculty = null;
			for (int i = 0; i < faculties.size(); i++) {
				if (faculties.get(i).getID() == facultyId) {
					faculty = faculties.get(i);
					break;
				}
			}
			String programId = programInput.getValue();
			Program program = null;
			for (int i = 0; i < faculty.getPrograms().size(); i++) {
				if (faculty.getPrograms().get(i).getID() == programId) {
					program = faculty.getPrograms().get(i);
					break;
				}
			}
			for (int i = 0; i < program.getCourses().size(); i++) {
				courseOptions.add(program.getCourses().get(i).getID());
			}
		});

		HBox markRow = new HBox();
		Label markLabel = new Label("Mark: ");
		markLabel.setMinWidth(labelWidth);
		Slider markInput = new Slider(0, 10, 5);
		markInput.setMinWidth(250);
		markInput.setShowTickLabels(true);
		markInput.setShowTickMarks(true);
		markInput.setMajorTickUnit(1);
		markInput.setMinorTickCount(1);
		markInput.setBlockIncrement(.5);
		markInput.setSnapToTicks(true);
		markRow.getChildren().addAll(markLabel, markInput);
		markRow.setAlignment(Pos.CENTER);
		wrapperChildren.add(markRow);

		HBox gradeButtonRow = new HBox();
		gradeButtonRow.setPadding(new Insets(0, 0, 0, 150));
		Button gradeAddButton = new Button("Add grade to list");
		gradeAddButton.setMinWidth(250);
		gradeButtonRow.getChildren().addAll(gradeAddButton);
		gradeButtonRow.setAlignment(Pos.CENTER);
		wrapperChildren.add(gradeButtonRow);

		Grade[] grades = this.manager.getUser().getGradeList();
		VBox gradeList = new VBox();
		gradeList.getStyleClass().add("grade-list");
		for (int i = 0; i < grades.length; i++) {
			addGradeToBox(grades[i].getCourseId(), grades[i].getGrade(), gradeList);
		}
		wrapperChildren.add(gradeList);

		gradeAddButton.setOnAction(e -> {
			if (courseInput.getValue() != null) {
				addGradeToBox(courseInput.getValue(), markInput.getValue(), gradeList);
			}
		});

		HBox buttonRow = new HBox();
		buttonRow.setPadding(new Insets(0, 0, 0, 150));
		Button updateButton = new Button("Update");
		updateButton.setOnAction(e -> {
			Grade[] gradeArray = new Grade[gradeList.getChildren().size()];
			for (int i = 0; i < gradeList.getChildren().size(); i++) {
				Pane row = (Pane) gradeList.getChildren().get(i);
				String courseId = ((Label) ((Pane) wrapper.getChildren().get(0)).getChildren().get(0)).getText();
				double mark = Double.valueOf(((Label) row.getChildren().get(1)).getText());
				gradeArray[i] = new Grade(courseId, mark);
			}
			User user = new User(this.manager.getUser().getUsername(), postalInput.getText(),
					descriptionInput.getText(), gradeArray, this.manager.getUser().isUrgent());
			Credentials credentials = new Credentials(this.manager.getCredentials().getUsername(),
					passInput1.getText());
			if ((passInput1.getText().equals(passInput2.getText())) && 
				((oldPassInput.getText()).equals((this.manager.getCredentials().getPassword()))) && 
				(gradeList.getChildren().size() >= 5) &&
				(manager.updateUser(credentials, user))) {
				logger.fine("Profile successfully updated.");
				this.manager.showHome();
			} else {
				messageLabel.setText("Wrong data entered, you should add 5 courses to continue and make sure old Password is filled in correct.");
				logger.fine("Wrong data entered.");
			}
		});
		updateButton.setMinWidth(125);
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> this.manager.showHome());
		cancelButton.setMinWidth(125);
		buttonRow.getChildren().addAll(updateButton, cancelButton);
		buttonRow.setAlignment(Pos.CENTER);
		wrapperChildren.add(buttonRow);

		Scene scene1 = new Scene(wrapper, 500, 375);
		scene1.getStylesheets().add("ml/vandenheuvel/ti1216/gui/Gui.css");

		this.scene = scene1;
	}

	public void addGradeToBox(String courseId, double mark, VBox box) {
		for (int i = 0; i < box.getChildren().size(); i++) {
			Pane wrapper = (Pane) box.getChildren().get(i);
			String existingCourse = ((Label) ((Pane) wrapper.getChildren().get(0)).getChildren().get(0)).getText();
			if (existingCourse.equals(courseId)) {
				return;
			}
		}

		HBox gradeBox = new HBox();
		HBox courseBox = new HBox();
		Button removeButton = new Button("x");
		removeButton.setMinHeight(20);
		removeButton.setMaxHeight(20);
		removeButton.setMinWidth(20);
		removeButton.setMaxWidth(20);
		removeButton.getStyleClass().add("remove-grade");
		Label courseLabel = new Label(courseId);
		Label colonLabel = new Label(": ");
		courseBox.getChildren().addAll(courseLabel, colonLabel);
		courseBox.setMinWidth(150);
		Label markLabel = new Label(Double.toString(mark));
		markLabel.setMinWidth(230);
		gradeBox.getChildren().addAll(courseBox, markLabel, removeButton);
		gradeBox.setAlignment(Pos.CENTER);
		box.getChildren().add(gradeBox);

		removeButton.setOnAction(e -> ((Pane) removeButton.getParent().getParent()).getChildren().remove(removeButton.getParent()));
	}

	public Scene getScene() {
		return this.scene;
	}

}
