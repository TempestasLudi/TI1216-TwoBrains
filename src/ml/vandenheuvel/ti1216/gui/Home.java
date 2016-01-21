package ml.vandenheuvel.ti1216.gui;

import java.util.logging.Logger;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.client.ClientManager;

/**
 * Settings allows the user that save his preferences.
 */
public class Home {

	private ClientManager manager;

	private Scene scene;

	public Home(ClientManager manager) {
		this.manager = manager;
		this.renderScene();
	}

	/**
	 * Sets all the elements of the Home window.
	 */
	private void renderScene() {
		HBox wrapper = new HBox();
		
		VBox match = new VBox();
		wrapper.getChildren().add(match);
		
		VBox doneMatches = new VBox();
		wrapper.getChildren().add(doneMatches);
		Label doneMatch01 = new Label("ASDF");
		doneMatches.getChildren().add(doneMatch01);
		
		Scene scene = new Scene(wrapper, 350, 200);
		scene.getStylesheets().add("ml/vandenheuvel/ti1216/gui/Gui.css");
		this.scene = scene;
	}

	public Scene getScene() {
		return this.scene;
	}
	
}
