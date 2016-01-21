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

//	private double xPress;
//	private double xRelease;
	private ClientManager manager;

//	private Stage window;
	private VBox contentWrapper;
	private Scene subscene;

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
		window.setTitle("Menu");
		window.setMaximized(true);

		/**
		 * Creates the top row of this window.
		 */
		HBox topMenu = new HBox();

		/**
		 * Button to go to the EditProfileGUI window.
		 */
		Button homeButton = new Button("Home");
		homeButton.setOnAction(e -> displaySub(new Home(this.manager).getScene()));

		/**
		 * Button to go to the EditProfileGUI window.
		 */
		Button editButton = new Button("Edit profile");
		editButton.setOnAction(e -> displaySub(new EditProfile(this.manager).getScene()));

		/**
		 * Button to ask for immediate matches.
		 */
		Button urgentButton = new Button("I am desperate!");
		urgentButton.getStyleClass().add("urgent");
		urgentButton.setOnAction(e -> {
			User user = this.manager.getUser();
			user.setUrgent(true);
			this.manager.updateUser(this.manager.getCredentials(), user);
		});

		/**
		 * Button to go to the SettingsGUI window.
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
		topMenu.getChildren().addAll(homeButton, editButton, urgentButton, settingsButton, logoutButton);

		/**
		 * Adds all the different components to the BorderPane.
		 */
		this.contentWrapper = new VBox();
		VBox contentWrapper1 = this.contentWrapper;
		contentWrapper1.getChildren().addAll(topMenu);
		this.displaySub(this.manager.getHomeWindow().getScene());
		// borderPane.setCenter(centerMenu);

		/**
		 * Sets the seize of the window and add all the elements.
		 */
		Scene scene = new Scene(contentWrapper1, 700, 400);
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

}
