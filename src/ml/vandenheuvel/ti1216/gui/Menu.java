package ml.vandenheuvel.ti1216.gui;

import java.util.logging.Logger;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.client.ClientManager;
import ml.vandenheuvel.ti1216.data.User;

/**
 * Menu is the main window of this application, from here you can access most
 * other windows.
 */
public class Menu {

	private ClientManager manager;

	// private Stage window;
	private VBox contentWrapper;
	private Scene subscene;
	
	private Button urgencyButton;

	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.client");

	public Menu(ClientManager manager) {
		this.manager = manager;
	}

	/**
	 * Sets all the elements of the Menu window.
	 */
	public void display() {
		logger.fine("Displaying Menu window...");
		/**
		 * Sets the title of the new window and fetches the user and his matches
		 * from the database.
		 */
		Stage window = new Stage();
		window.setResizable(false);
		window.setTitle("TwoBrains");

		/**
		 * Creates the top row of this window.
		 */
		HBox topMenu = new HBox();
		topMenu.setAlignment(Pos.CENTER);

		/**
		 * Button to go to the EditProfileGUI window.
		 */
		Button homeButton = new Button("Home");
		homeButton.setOnAction(e -> displaySub(new Home(this.manager).getScene()));

		/**
		 * Button to go to the EditProfile window.
		 */
		Button editButton = new Button("Edit profile");
		editButton.setOnAction(e -> displaySub(new EditProfile(this.manager).getScene()));

		/**
		 * Button to ask for immediate matches.
		 */
		this.urgencyButton = new Button();
		this.setUrgency(this.manager.getUser().isUrgent());

		/**
		 * Button to go to the Settings window.
		 */
		Button settingsButton = new Button("Settings");
		settingsButton.setOnAction(e -> displaySub(new Settings(this.manager).getScene()));

		/**
		 * Button to log out from this application.
		 */
		Button logoutButton = new Button("Log out");
		logoutButton.setOnAction(e -> {
			window.close();
			this.manager.logout();
		});
		topMenu.getChildren().addAll(homeButton, editButton, urgencyButton, settingsButton, logoutButton);

		/**
		 * Adds all the different components to the BorderPane.
		 */
		this.contentWrapper = new VBox();
		VBox contentWrapper1 = this.contentWrapper;
		contentWrapper1.getChildren().addAll(topMenu);
		this.displaySub(this.manager.getHomeWindow().getScene());
		// borderPane.setCenter(centerMenu);

		/**
		 * Sets the size of the window and add all the elements.
		 */
		Scene scene = new Scene(contentWrapper1, 800, 600);
		scene.getStylesheets().add("ml/vandenheuvel/ti1216/gui/Gui.css");
		window.setScene(scene);
		window.show();
	}

	public void displaySub(Scene subscene) {
		if (this.subscene != null) {
			this.contentWrapper.getChildren().remove(this.subscene.getRoot());
		}
		this.subscene = subscene;
		if (this.subscene != null) {
			this.contentWrapper.getChildren().add(subscene.getRoot());
		}
	}
	
	private void setUrgency(boolean urgent) {
		if (urgent) {
			this.urgencyButton.setText("I'm not desperate.");
			this.urgencyButton.getStyleClass().add("not-urgent");
			this.urgencyButton.getStyleClass().remove("urgent");
		} else {
			this.urgencyButton.setText("I am desperate.");
			this.urgencyButton.getStyleClass().add("urgent");
			this.urgencyButton.getStyleClass().remove("not-urgent");
		}
		this.urgencyButton.setOnAction(e -> {
			User user = this.manager.getUser();
			user.setUrgent(!urgent);
			this.manager.updateUser(this.manager.getCredentials(), user);
			this.setUrgency(!urgent);
		});
	}

}
