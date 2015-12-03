package ml.vandenheuvel.TI1216.source.gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuGUI 
{

	public static void display()
	{
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("MenuGUI");
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

}
