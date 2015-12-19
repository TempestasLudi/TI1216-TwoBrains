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

public class RegisterGUI 
{
	public static void display()
	{
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("RegisterGUI");
		window.setMinWidth(250);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label nameLabel = new Label("Username: ");
		GridPane.setConstraints(nameLabel, 0, 0);
		
		TextField nameInput = new TextField();
		nameInput.setPromptText("Username");
		GridPane.setConstraints(nameInput, 1, 0);
		
		Label passLabel1 = new Label("Password: ");
		GridPane.setConstraints(passLabel1, 0, 1);
		
		PasswordField passInput1 = new PasswordField();
		passInput1.setPromptText("Password");
		GridPane.setConstraints(passInput1, 1, 1);
		
		Label passLabel2 = new Label("Confirm Password: ");
		GridPane.setConstraints(passLabel2, 0, 2);
		
		PasswordField passInput2 = new PasswordField();
		passInput2.setPromptText("Password");
		GridPane.setConstraints(passInput2, 1, 2);
		
		Label postalLabel = new Label("PostalCode: ");
		GridPane.setConstraints(postalLabel, 0, 3);
		
		TextField postalInput = new TextField();
		postalInput.setPromptText("PostalCode");
		GridPane.setConstraints(postalInput, 1, 3);
		
		Label descriptionLabel = new Label("Description: ");
		GridPane.setConstraints(descriptionLabel, 0, 4);
		
		TextField descriptionInput = new TextField();
		descriptionInput.setPromptText("Description");
		GridPane.setConstraints(descriptionInput, 1, 4);
		
		Label courseIDLabel = new Label("CourseID: ");
		GridPane.setConstraints(courseIDLabel, 0, 5);
		
		TextField courseIDInput = new TextField();
		courseIDInput.setPromptText("CourseID");
		GridPane.setConstraints(courseIDInput, 1, 5);
		
		Label gradeLabel = new Label("Grade: ");
		GridPane.setConstraints(gradeLabel, 0, 6);
		
		TextField gradeInput = new TextField();
		gradeInput.setPromptText("Grade");
		GridPane.setConstraints(gradeInput, 1, 6);
		
		
		Button loginButton = new Button("Register");
		loginButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override
			    public void handle(ActionEvent e) 
			    {
				 	Grade grade = new Grade(courseIDInput.getText(), gradeInput.getAnchor());
				 	Grade[] gradelist = new Grade[1];
				 	gradelist[0] = grade;
				 	Credentials credentials = new Credentials(nameInput.getText(), passInput1.getText());
				 	User user = new User(nameInput.getText(), postalInput.getText(), descriptionInput.getText(), gradelist);
			    	if (ServerCommunicator.register(credentials, user))
			    	{
			    	 	System.out.println("You are successfully registered");
			    		LoginGUI.display();
			    	 	window.close();
			    	}
			    	else
			    	{
			    		System.out.println("You did not enter the correct data");
			    	}
			    }
		});
		GridPane.setConstraints(loginButton, 1, 7);
		
		grid.getChildren().addAll(nameLabel, nameInput, passLabel1, passInput1, passLabel2, passInput2, postalLabel, postalInput, descriptionLabel, descriptionInput, courseIDLabel, courseIDInput, gradeLabel, gradeInput, loginButton);
		
		Scene scene = new Scene(grid, 500, 400);
		window.setScene(scene);
		window.showAndWait();
	}

}
