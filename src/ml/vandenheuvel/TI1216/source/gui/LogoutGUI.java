package ml.vandenheuvel.TI1216.source.gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LogoutGUI 
{
	public static void display()
	{
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("LogoutGUI");
		
		
		VBox vbox = new VBox();
		Label logoutLabel = new Label("Thank you for using our application!");
		Button loginButton = new Button("Back to Login");
		loginButton.setOnAction(new EventHandler<ActionEvent>()
		{
				public void handle(ActionEvent e)
				{
					LoginGUI.display();
					window.close();
				}
		});
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(logoutLabel, loginButton);
		
		StackPane layout = new StackPane();
		layout.getChildren().addAll(vbox);
		Scene scene = new Scene(layout, 350, 200);
		window.setScene(scene);
		window.show();
	}
}
		