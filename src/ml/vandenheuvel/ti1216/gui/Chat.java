package ml.vandenheuvel.ti1216.gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.client.ServerCommunicator;

/**
 * Chat is a pop-up window that shows a chat with a specific person.
 */
public class Chat {

	/**
	 * Sets all the elements of the Chat window.
	 */
	private Chat() {
		// Default constructor to hide the implicit one
	}

	public static void display() {
		/**
		 * Sets the title of the new window and fetches the user and his chats
		 * from the database.
		 */
		Stage window = new Stage();
		window.setTitle("ChatGUI");
		window.setTitle("Chat");
		ServerCommunicator.login(Main.credentials);
		ServerCommunicator.fetchChats(Main.credentials);

		VBox vbox = new VBox();
		HBox hbox = new HBox();

		/**
		 * Label that act as a chat screen.
		 */
		Label chatLabel = new Label("");
		chatLabel.setStyle("-fx-background-color: white");
		chatLabel.setMinSize(300, 250);

		/**
		 * InputField to enter the chat messages.
		 */
		TextField chatInput = new TextField("Type your text");
		chatInput.setPrefWidth(245);

		/**
		 * Button to send the chat message.
		 */
		Button sendButton = new Button("Send");
		sendButton.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * Fetches the input from the above InputField and sends them to the
			 * database.
			 */
			@Override
			public void handle(ActionEvent e) {
				// ServerCommunicator.sendChat(MainGUI.credentials,
				// chatInput.getText());
			}
		});

		hbox.getChildren().addAll(chatInput, sendButton);
		vbox.setPrefWidth(300);
		vbox.getChildren().addAll(chatLabel, hbox);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(vbox);

		Scene scene = new Scene(borderPane, 300, 280);
		window.setScene(scene);
		window.showAndWait();
	}
}
