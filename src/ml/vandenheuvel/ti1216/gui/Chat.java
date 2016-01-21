package ml.vandenheuvel.ti1216.gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import ml.vandenheuvel.ti1216.client.ClientManager;

/**
 * Chat is a pop-up window that shows a chat with a specific person.
 */
public class Chat {
	
	private ClientManager manager;
	
	private Stage stage1;
	
	public Chat(ClientManager manager) {
		this.manager = manager;
	}
	/**
	 * Sets all the elements of the Chat window.
	 */
	public void display() {
		/**
		 * Sets the title of the new window and fetches the user and his chats
		 * from the database.
		 */
		this.stage1 = new Stage();
		Stage window = this.stage1;
		window.initModality(Modality.WINDOW_MODAL);
		window.setTitle("Chat");

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
		sendButton.setOnAction(e -> {
			//TODO: Send something
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
