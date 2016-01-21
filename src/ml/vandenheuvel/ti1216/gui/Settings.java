package ml.vandenheuvel.ti1216.gui;

import java.util.logging.Logger;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.client.ClientManager;

/**
 * Settings allows the user that save his preferences.
 */
public class Settings {

	private ClientManager manager;

	private Scene scene;

	public Settings(ClientManager manager) {
		this.manager = manager;
		this.renderScene();
	}

	/**
	 * Sets all the elements of the Settings window.
	 */
	private void renderScene() {
		/**
		 * Sets the constraints of the window.
		 */
		VBox vbox = new VBox();
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();
		HBox hbox3 = new HBox();

		/**
		 * Label that allows you to receive a notification when a match is
		 * found.
		 */
		Label noti1Label = new Label("Notification when match is found?");

		/**
		 * Create a CheckBox that you can tick to enable the option above.
		 */
		CheckBox checkbox1 = new CheckBox();

		/**
		 * Label that allows you to receive a notification when someone starts a
		 * chat with you.
		 */
		Label noti2Label = new Label("Notification when a chat session starts?");

		/**
		 * Create a CheckBox that you can tick to enable the option above.
		 */
		CheckBox checkbox2 = new CheckBox();

		/**
		 * Label that lets you decide whether or not your location can be used.
		 */
		Label noti3Label = new Label("Are we allowed to use your location?");

		/**
		 * Create a CheckBox that you can tick to enable the option above.
		 */
		CheckBox checkbox3 = new CheckBox();

		/**
		 * Button to return back to the Menu.
		 */
		Button menuButton = new Button("Return back to menu");
		menuButton.setOnAction(e -> this.manager.showHome());

		/**
		 * Adds the Labels, CheckBoxes and Button to hbox and vbox.
		 */
		hbox1.setAlignment(Pos.TOP_CENTER);
		hbox2.setAlignment(Pos.TOP_CENTER);
		hbox3.setAlignment(Pos.TOP_CENTER);
		vbox.setAlignment(Pos.TOP_CENTER);
		hbox1.getChildren().addAll(noti1Label, checkbox1);
		hbox2.getChildren().addAll(noti2Label, checkbox2);
		hbox3.getChildren().addAll(noti3Label, checkbox3);
		vbox.getChildren().addAll(hbox1, hbox2, hbox3, menuButton);

		/**
		 * Adds the vbox and the hbox to the BorderPane.
		 */
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(vbox);

		/**
		 * Sets the seize of the window and adds all the new elements.
		 */
		Scene scene = new Scene(borderPane, 350, 200);
		scene.getStylesheets().add("ml/vandenheuvel/ti1216/gui/Gui.css");
		this.scene = scene;
	}

	public Scene getScene() {
		return this.scene;
	}
	
}
