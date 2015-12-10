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

public class LoginGUI 
{
	//static DatabaseCommunicator dbCommunicator = new DatabaseCommunicator("192.168.1.111", "TI1206");

	public static void display()
	{
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("LoginGUI");
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
		
		Label passLabel = new Label("Password: ");
		GridPane.setConstraints(passLabel, 0, 1);
		
		PasswordField passInput = new PasswordField();
		passInput.setPromptText("Password");
		GridPane.setConstraints(passInput, 1, 1);
		
		Button loginButton = new Button("Log in");
		loginButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override
			    public void handle(ActionEvent e) 
			    {	
				 	//String username = nameInput.toString();
				 	//String password = passInput.toString();
				 	//logIn(username, password);
				 	MenuGUI.display();
			    }
		});
		GridPane.setConstraints(loginButton, 1, 2);
		
		Hyperlink registerLink = new Hyperlink();
		registerLink.setText("Not a member yet? Register now!");
		registerLink.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	RegisterGUI.display();
		    }
		});
		GridPane.setConstraints(registerLink, 1, 3);
		
		grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, registerLink);
		
		Scene scene = new Scene(grid, 350, 200);
		window.setScene(scene);
		window.showAndWait();
	}
	
	//public static void logIn(String username, String password){
		//Credentials cred = new Credentials(username, password);
	 	//if(dbCommunicator.canLogin(cred)){
	 		//MenuGUI.display();
	 		//window.close();
	 	//}
	 	//else{
	 		//error message
	 		//System.out.println("Can't log in");
	 	//}
	//}

}
