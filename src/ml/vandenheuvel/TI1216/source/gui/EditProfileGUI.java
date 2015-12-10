package ml.vandenheuvel.TI1216.source.gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.event.*;

public class EditProfileGUI {

	static String username = "Default"; //databasecommunicator.getUser().getUsername();
	static String postcode = "Default";//databasecommunicator.getUser().getPostCode();
	static String description = "Default";//databasecommunicator.getUser().getDescription();
	static int editCounter = 0;
	
	public static void display(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("EditProfileGUI");
		window.setMinWidth(500);
		window.setMinHeight(500);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label nameLabel = new Label("Username: "); // pull username from database
		GridPane.setConstraints(nameLabel, 0, 0);
		
		Label setNameLabel = new Label(username);
		GridPane.setConstraints(setNameLabel, 1, 0);
		
		Label postCodeLabel = new Label("Postcode: "); //pull postcode from database
		GridPane.setConstraints(postCodeLabel, 0, 1);
		
		Label descrLabel = new Label("Description: "); //pull description from database
		GridPane.setConstraints(descrLabel, 0, 2);
		
		Button editName = new Button("Edit");
		editName.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override
			    public void handle(ActionEvent e) 
			    {	
				 	editCounter++;
				 TextField nameInput = new TextField();
					nameInput.setPromptText("Edit username");
					GridPane.setConstraints(nameInput, 2, 0);
					grid.getChildren().add(nameInput);
					
					if(editCounter % 2 == 0)
					{
						username = nameInput.getText();
						
						setNameLabel.setText(username);
						
						
					}
			    
			    }
		});
		GridPane.setConstraints(editName, 3, 0);
		
		Button editPostCode = new Button("Edit");
		editPostCode.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override
			    public void handle(ActionEvent e) 
			    {	
				 TextField postCodeInput = new TextField();
					postCodeInput.setPromptText("Edit postcode");
					GridPane.setConstraints(postCodeInput, 1, 1);
					grid.getChildren().add(postCodeInput);
			    
			    }
		});
		GridPane.setConstraints(editPostCode, 2, 1);
		
		Button editDescr = new Button("Edit");
		editDescr.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override
			    public void handle(ActionEvent e) 
			    {	
				 TextField descrInput = new TextField();
					descrInput.setPromptText("Edit description");
					GridPane.setConstraints(descrInput, 1, 2);
					grid.getChildren().add(descrInput);
			    
			    }
		});
		GridPane.setConstraints(editDescr, 2, 2);
		
			
		grid.getChildren().addAll(nameLabel, setNameLabel, postCodeLabel, descrLabel, editName, editPostCode, editDescr);
		
		Scene scene = new Scene(grid, 350, 200);
		window.setScene(scene);
		window.showAndWait();
	}
	
	
}
