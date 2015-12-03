package ml.vandenheuvel.TI1216.source.gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainGUI extends Application
{
	Stage window;
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		window = primaryStage;
		window.setTitle("MainGUI");

		Button button = new Button("Go to Login");
		button.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override
			    public void handle(ActionEvent e) 
			    {
			    	LoginGUI.display();
			    }
		});
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		Scene scene = new Scene(layout, 350, 200);
		window.setScene(scene);
		window.show();
		
		
		
	}

}
