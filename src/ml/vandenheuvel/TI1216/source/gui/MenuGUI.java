package ml.vandenheuvel.TI1216.source.gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuGUI 
{

	public static void display()
	{
		Stage window = new Stage();
		window.setTitle("MenuGUI");
		
		HBox topMenu = new HBox();
		Button settingsButton = new Button("Settings");
		settingsButton.setOnAction(new EventHandler<ActionEvent>()
		{
				public void handle(ActionEvent e)
				{
					SettingsGUI.display();
				}
		});
		Button logoutButton = new Button("Log out");
		logoutButton.setOnAction(new EventHandler<ActionEvent>()
		{
				public void handle(ActionEvent e)
				{
					LogoutGUI.display();
				}
		});
		topMenu.setAlignment(Pos.CENTER_RIGHT);
		topMenu.getChildren().addAll(settingsButton, logoutButton);
	
		
		VBox leftMenu = new VBox();
		Button editButton = new Button("Edit profile");
		editButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override
			    public void handle(ActionEvent e) 
			    {	
				 	EditProfileGUI.display();
			    }
		});
		Button chatButton = new Button("Open chat");
		chatButton.setOnAction(new EventHandler<ActionEvent>()
		{
				public void handle(ActionEvent e)
				{
					ChatGUI.display();
				}
			
		});
		leftMenu.setPrefWidth(200);
		leftMenu.getChildren().addAll(editButton, chatButton);
		
		VBox centerMenu = new VBox();
		Label matchLabel = new Label("All matches: ");
		Label match1Label = new Label("Match1");
		Label match2Label = new Label("Match2");
	    centerMenu.setPrefWidth(200);
		centerMenu.getChildren().addAll(matchLabel, match1Label, match2Label);

		VBox rightMenu = new VBox();
		HBox rightMenu2 = new HBox();
		Label chatLabel = new Label("");
		chatLabel.setStyle("-fx-background-color: white");
		chatLabel.setMinSize(300, 250);
		TextField chatInput = new TextField("Type your text");
		chatInput.setPrefWidth(245);
		Button sendButton = new Button("Send");
		rightMenu2.getChildren().addAll(chatInput, sendButton);
		rightMenu.setPrefWidth(300);
		rightMenu.getChildren().addAll(chatLabel, rightMenu2);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);
		borderPane.setLeft(leftMenu);
		borderPane.setCenter(centerMenu);
		borderPane.setRight(rightMenu);
		
		Scene scene = new Scene(borderPane, 700, 400);
		window.setScene(scene);
		window.showAndWait();
	}

}
