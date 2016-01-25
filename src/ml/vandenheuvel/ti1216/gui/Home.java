package ml.vandenheuvel.ti1216.gui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.client.ClientManager;
import ml.vandenheuvel.ti1216.data.Match;

/**
 * Settings allows the user that save his preferences.
 */
public class Home {

	private ClientManager manager;

	private Scene scene;

	private Pane matches;
	
	private double xPress;
	private double xRelease;

	public Home(ClientManager manager) {
		this.manager = manager;
		this.renderScene();
	}

	/**
	 * Sets all the elements of the Home window.
	 */
	private void renderScene() {
		HBox wrapper = new HBox();
		wrapper.setAlignment(Pos.CENTER);
		this.matches = new VBox();
		wrapper.getChildren().add(this.matches);
		this.matches.getChildren().add(new Label("Matches:"));

		ArrayList<Match> matchesList = this.manager.getMatches();
		for (int i = 0; i < matchesList.size(); i++) {
			this.addMatch(matchesList.get(i));
		}

		Scene scene = new Scene(wrapper, 350, 200);
		scene.getStylesheets().add("ml/vandenheuvel/ti1216/gui/Gui.css");
		this.scene = scene;
	}

	public void addMatch(Match match) {
		HBox wrapper = new HBox();
		Label matchLabel = new Label(match.getMatchUsername());
		matchLabel.setMinWidth(200);
		matchLabel.setOnMousePressed(e -> {
			xPress = e.getX();
		});
		matchLabel.setOnMouseReleased(e -> {
			xRelease = e.getX();
			if(xRelease - xPress < 10) {
				this.matches.getChildren().remove(wrapper);
				this.manager.discardMatch(match);
			}
		});
		Button chatButton = new Button("Chat");
		chatButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			this.manager.openChat(match.getMatchUsername());
		});
		Button discardButton = new Button("Discard");
		discardButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			this.matches.getChildren().remove(wrapper);
			this.manager.discardMatch(match);
		});
		wrapper.getChildren().addAll(matchLabel, chatButton, discardButton);
		Platform.runLater(new Runnable() {
			public void run() {
				matches.getChildren().add(wrapper);
			}
		});
	}

	public Scene getScene() {
		return this.scene;
	}

}
