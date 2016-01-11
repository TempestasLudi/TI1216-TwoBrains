package ml.vandenheuvel.ti1216.gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;


public class SettingsGUI 
{
	public static void display()
	{
		Stage window = new Stage();
		window.setTitle("SettingsGUI");
		
		VBox vbox = new VBox();
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();
		HBox hbox3 = new HBox();
		Label noti1Label = new Label("Notification when match is found?            ");
		CheckBox checkbox1 = new CheckBox();
		Label noti2Label = new Label("Notification when a chat session starts?    ");
		CheckBox checkbox2 = new CheckBox();
		Label noti3Label = new Label("Are we allowed to use your location?        ");
		CheckBox checkbox3 = new CheckBox();
		Button menuButton = new Button("Return back to menu");
		menuButton.setOnAction(new EventHandler<ActionEvent>()
		{
				@Override
				public void handle(ActionEvent e)
				{
					MenuGUI.display();
					window.close();
				}
		});
		hbox1.setAlignment(Pos.TOP_CENTER);
		hbox2.setAlignment(Pos.TOP_CENTER);
		hbox3.setAlignment(Pos.TOP_CENTER);
		vbox.setAlignment(Pos.TOP_CENTER);
		hbox1.getChildren().addAll(noti1Label, checkbox1);
		hbox2.getChildren().addAll(noti2Label, checkbox2);
		hbox3.getChildren().addAll(noti3Label, checkbox3);
		vbox.getChildren().addAll(hbox1, hbox2, hbox3, menuButton);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(vbox);

		
		Scene scene = new Scene(borderPane, 350, 200);
		window.setScene(scene);
		window.showAndWait();
	}
}