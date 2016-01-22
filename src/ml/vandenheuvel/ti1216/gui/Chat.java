package ml.vandenheuvel.ti1216.gui;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.client.ClientManager;
import ml.vandenheuvel.ti1216.data.ChatMessage;

/**
 * Chat is a pop-up window that shows a chat with a specific person.
 */
public class Chat {

	private ClientManager manager;

	private Stage window;

	private Pane messageList;

	private ScrollPane scroller;

	private String username;

	public Chat(ClientManager manager, String username) {
		this.manager = manager;
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets all the elements of the Chat window.
	 */
	public void display() {
		/**
		 * Sets the title of the new window and fetches the user and his chats
		 * from the database.
		 */
		this.window = new Stage();
		window.initModality(Modality.WINDOW_MODAL);
		window.setTitle(this.username);

		VBox vbox = new VBox();
		HBox hbox = new HBox();

		/**
		 * Label that act as a chat screen.
		 */
		this.scroller = new ScrollPane();
		this.scroller.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.messageList = new VBox();
		this.messageList.getStyleClass().add("message-list");
		this.messageList.setMinWidth(300);
		this.scroller.setContent(messageList);
		this.scroller.setPrefSize(300, 250);

		ArrayList<ChatMessage> messages = this.manager.getMessages();
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).getSender().equals(this.username)) {
				this.newChat(messages.get(i), true);
			}
			if (messages.get(i).getReceiver().equals(this.username)) {
				this.newChat(messages.get(i), false);
			}
		}
		this.scroller.setVvalue(1.0);

		/**
		 * InputField to enter the chat messages.
		 */
		TextField chatInput = new TextField("Type your text");
		chatInput.setPrefWidth(245);
		chatInput.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode().getName().equals("Enter")) {
				this.sendChat(chatInput);
			}
		});

		/**
		 * Button to send the chat message.
		 */
		Button sendButton = new Button("Send");
		sendButton.setOnAction(e -> {
			ChatMessage message = new ChatMessage(-1, this.manager.getUser().getUsername(), chatInput.getText(),
					this.username, false);
			this.sendChat(chatInput);
		});

		hbox.getChildren().addAll(chatInput, sendButton);
		vbox.setPrefWidth(300);
		vbox.getChildren().addAll(scroller, hbox);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(vbox);

		Scene scene = new Scene(borderPane, 300, 280);
		scene.getStylesheets().add("ml/vandenheuvel/ti1216/gui/Gui.css");
		window.setScene(scene);
		window.show();
	}

	public void toFront() {
		this.window.show();
		this.window.toFront();
	}

	public void newChat(ChatMessage message) {
		this.newChat(message, false);
	}

	private void newChat(ChatMessage message, boolean mine) {
		HBox row = new HBox();
		row.setMaxWidth(280);
		row.setPadding(new Insets(0, 0, 5, 0));
		Label chatLabel = new Label(message.getMessage());
		chatLabel.setMaxWidth(250);
		chatLabel.setPadding(new Insets(5, 10, 5, 10));
		if (mine) {
			row.getStyleClass().add("my-message-row");
		} else {
			row.getStyleClass().add("other-message-row");
		}
		chatLabel.setWrapText(true);
		row.getChildren().add(chatLabel);
		boolean bottom = this.scroller.getVvalue() == this.scroller.getVmax();
		this.messageList.getChildren().add(row);

		// Scroll down
		if (bottom) {
			DoubleProperty wProperty = new SimpleDoubleProperty();
			wProperty.bind(this.messageList.heightProperty());
			wProperty.addListener(new ChangeListener<Number>() {
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					scroller.setVvalue(scroller.getVmax());
					wProperty.removeListener(this);
				}
			});
		}
	}

	private void sendChat(TextField chatInput) {
		if (chatInput.getText().trim().equals("")) {
			return;
		}
		ChatMessage message = new ChatMessage(-1, this.manager.getUser().getUsername(), chatInput.getText(),
				this.username, false);
		if (this.manager.sendChat(message)) {
			this.newChat(message, true);
			chatInput.setText("");
		}
	}

}
