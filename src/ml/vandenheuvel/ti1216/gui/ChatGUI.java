package ml.vandenheuvel.ti1216.gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

public class ChatGUI {
	
	private ChatGUI(){
		//Default constructor to hide the implicit one
	}
	
	public static void display() {
		Stage window = new Stage();
		window.setTitle("ChatGUI");

		VBox vbox = new VBox();
		HBox hbox = new HBox();
		Label chatLabel = new Label("");
		chatLabel.setStyle("-fx-background-color: white");
		chatLabel.setMinSize(300, 250);
		TextField chatInput = new TextField("Type your text");
		chatInput.setPrefWidth(245);
		Button sendButton = new Button("Send");
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