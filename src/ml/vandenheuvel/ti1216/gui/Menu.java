package ml.vandenheuvel.ti1216.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.client.ServerCommunicator;

/**
 * Menu is the main window of this application, from here you can access most
 * other windows.
 */
public class Menu {
	
	static double xPress;
	static double xRelease;

	private Menu() {
		// Private constructor to hide the implicit public one
	}

	/**
	 * Sets all the elements of the Menu window.
	 */
	public static void display() {

		/**
		 * Sets the title of the new window and fetches the user and his matches
		 * from the database.
		 */
		Stage window = new Stage();
		window.setTitle("Menu");
		ServerCommunicator.login(Main.credentials);
		ServerCommunicator.fetchMatches(Main.credentials);

		/**
		 * Creates the top row of this window.
		 */
		HBox topMenu = new HBox();

		/**
		 * Button to go to the SettingsGUI window.
		 */
		Button settingsButton = new Button("Settings");
		settingsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Settings.display();
				window.close();
			}
		});

		/**
		 * Button to log out from this application.
		 */
		Button logoutButton = new Button("Log out");
		logoutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Logout.display();
				window.close();
			}
		});

		topMenu.setAlignment(Pos.CENTER_RIGHT);
		topMenu.getChildren().addAll(settingsButton, logoutButton);
		/**
		 * Creates the left row of this application.
		 */
		VBox leftMenu = new VBox();

		/**
		 * Button to go to the EditProfileGUI window.
		 */
		Button editButton = new Button("Edit profile");
		editButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				EditProfile.display();
				window.close();
			}
		});

		/**
		 * Button to go to the ChatGUi window.
		 */
		Button chatButton = new Button("Open chat");
		chatButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Chat.display();
				window.close();
			}

		});
		leftMenu.setPrefWidth(200);
		leftMenu.getChildren().addAll(editButton, chatButton);

		/**
		 * Creates the center of this window.
		 */
		VBox centerMenu = new VBox();

		/**
		 * Label that says All matches.
		 */
		Label matchLabel = new Label("All matches: ");

		/**
		 * Multiple Labels to show all the matches.
		 */
		Label match1Label = new Label("Match1");

		match1Label.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				xPress = e.getX();
				System.out.println(xPress);
			}
		});
		match1Label.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
			    xRelease = e.getX();
			    System.out.println(xRelease);
				if(xRelease - xPress > 0)
				{
					System.out.println("Added the match to preferences.");
				}
				else
				{
					System.out.println("Deleted the match.");
				}
			}
		});
		
		Label match2Label = new Label("Match2");

		/**
		 * Button to ask for immediate matches.
		 */
		Button urgentButton = new Button("Help now!");
		urgentButton.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * Fetches more matches from the database.
			 */
			@Override
			public void handle(ActionEvent e) {
				ServerCommunicator.fetchMatches(Main.credentials);
			}
		});
		centerMenu.setPrefWidth(200);
		centerMenu.setPrefWidth(200);
		centerMenu.getChildren().addAll(matchLabel, match1Label, match2Label, urgentButton);

		/**
		 * Creates the right row of this application.
		 */
		VBox rightMenu = new VBox();
		HBox rightMenu2 = new HBox();

		/**
		 * Adds a Label that represent the chat.
		 */
		Label chatLabel = new Label("");
		chatLabel.setStyle("-fx-background-color: white");
		chatLabel.setMinSize(300, 250);

		/**
		 * InputField to enter your chat messages.
		 */
		TextField chatInput = new TextField("Type your text");
		chatInput.setPrefWidth(245);

		/**
		 * A Button to send the chat messages.
		 */
		Button sendButton = new Button("Send");
		rightMenu2.getChildren().addAll(chatInput, sendButton);
		rightMenu.setPrefWidth(300);
		rightMenu.getChildren().addAll(chatLabel, rightMenu2);

		/**
		 * Adds all the different components to the BorderPane.
		 */
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);
		borderPane.setLeft(leftMenu);
		borderPane.setCenter(centerMenu);
		borderPane.setRight(rightMenu);

		/**
		 * Sets the seize of the window and add all the elements.
		 */
		Scene scene = new Scene(borderPane, 700, 400);
		scene.getStylesheets().add("ml/vandenheuvel/ti1216/gui/Gui.css");
		window.setScene(scene);
		window.showAndWait();
	}

}
