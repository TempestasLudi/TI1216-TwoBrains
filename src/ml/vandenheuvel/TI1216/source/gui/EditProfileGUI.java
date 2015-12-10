package ml.vandenheuvel.TI1216.source.gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditProfileGUI {

	static String username = "Default"; //databasecommunicator.getUser().getUsername();
	static String postcode = "Default";//databasecommunicator.getUser().getPostCode();
	static String description = "Default";//databasecommunicator.getUser().getDescription();
	
	public static void display(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("EditProfileGUI");
		
		
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
		
		Label setPCLabel = new Label(postcode);
		GridPane.setConstraints(setPCLabel, 1, 1);
		
		Label descrLabel = new Label("Description: "); //pull description from database
		GridPane.setConstraints(descrLabel, 0, 2);
		
		Label setDescrLabel = new Label(description);
		GridPane.setConstraints(setDescrLabel, 1, 2);
		
		Button editName = new Button("Edit");
		editName.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override
			    public void handle(ActionEvent e) 
			    {	
				 TextField nameInput = new TextField();
					nameInput.setPromptText("Edit username");
					GridPane.setConstraints(nameInput, 2, 0);
					grid.getChildren().add(nameInput);	
					username = nameInput.getText();
					setNameLabel.setText(username);
			    
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
					GridPane.setConstraints(postCodeInput, 2, 1);
					grid.getChildren().add(postCodeInput);
					postcode = postCodeInput.getText();
					setPCLabel.setText(postcode);
			    
			    }
		});
		GridPane.setConstraints(editPostCode, 3, 1);
		
		Button editDescr = new Button("Edit");
		editDescr.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override
			    public void handle(ActionEvent e) 
			    {	
				 TextField descrInput = new TextField();
				 descrInput.setPrefSize(245, 245);
					//descrInput.setPromptText("Edit description");
					descrInput.setText(description);
					GridPane.setConstraints(descrInput, 2, 2);
					
					grid.getChildren().add(descrInput);
					
					description = descrInput.getText();
					setDescrLabel.setText(description);
			    }
		});
		
		Button menuButton = new Button("Return back to menu");
		menuButton.setOnAction(new EventHandler<ActionEvent>()
		{
				public void handle(ActionEvent e)
				{
					MenuGUI.display();
					window.close();
				}
		});
		GridPane.setConstraints(editDescr, 3, 2);
		GridPane.setConstraints(setDescrLabel, 1, 2);
		GridPane.setConstraints(menuButton, 0, 4);
	
		
			
		grid.getChildren().addAll(nameLabel, setNameLabel, postCodeLabel, setPCLabel, descrLabel, setDescrLabel, editName, editPostCode, editDescr, menuButton);
		
		Scene scene = new Scene(grid, 500, 500);
		window.setScene(scene);
		window.showAndWait();
	}
	
	
}
