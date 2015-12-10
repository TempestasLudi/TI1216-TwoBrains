package ml.vandenheuvel.TI1216.source.gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ml.vandenheuvel.TI1216.source.data.Credentials;
import ml.vandenheuvel.TI1216.source.data.DatabaseCommunicator;
import ml.vandenheuvel.TI1216.source.data.User;

public class RegisterGUI 
{
	//static DatabaseCommunicator dbCommunicator = new DatabaseCommunicator("192.168.1.111", "TI1206");

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
		
		Button loginButton = new Button("Register");
		loginButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override
			    public void handle(ActionEvent e) 
			    {
				 	//String username = nameInput.toString();
				 	//String password = null;
				 	//if(passInput1.toString().equals(passInput2.toString())){
				 		//password = passInput1.toString();
				 	//}
				 	//else{
				 		//error message
				 	//}
				 	//Credentials cred = new Credentials(username, password);
				 	//User u1 = new User(username);
				 	//if(dbCommunicator.canRegister(cred)){
				 		//dbCommunicator.save(u1, cred);
				 		MenuGUI.display();
				 		//window.close();
				 	//}
				 	//else{
				 		//error message
				 		//System.out.println("Can't register");
				 	//}
			    	
			    }
		});
		GridPane.setConstraints(loginButton, 1, 3);
		
		grid.getChildren().addAll(nameLabel, nameInput, passLabel1, passInput1, passLabel2, passInput2, loginButton);
		
		Scene scene = new Scene(grid, 350, 200);
		window.setScene(scene);
		window.showAndWait();
	}

}
