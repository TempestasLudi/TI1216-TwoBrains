package ml.vandenheuvel.ti1216.gui;

import java.util.logging.Logger;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ml.vandenheuvel.ti1216.client.ClientManager;

/**
 * Logout is the window that shows up when you log out of the application.
 */
public class Logout {
	
	private ClientManager manager; 
	
	private Logout(ClientManager manager){
		this.manager = manager;
	}
	
	/**
	 * Sets all the elements of the Logout window.
	 */
	public void display() {
		/**
		 * Sets the title of the new window.
		 */
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Logout");

		/**
		 * Sets the constraints of the window.
		 */
		VBox vbox = new VBox();

		/**
		 * Label that says Thank you for using this application.
		 */
		Label logoutLabel = new Label("Thank you for using our application!");

		/**
		 * Button that allows you to go back to the login screen.
		 */
		Button loginButton = new Button("Back to Login");
		loginButton.setOnAction(e -> {
				Login.display();
				window.close();
		});
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(logoutLabel, loginButton);

		/**
		 * Adds the vbox to the StackPane.
		 */
		StackPane layout = new StackPane();
		layout.getChildren().addAll(vbox);

		/**
		 * Sets the seize of the window and adds all the new elements.
		 */
		Scene scene = new Scene(layout, 350, 200);
		scene.getStylesheets().add("ml/vandenheuvel/ti1216/gui/Gui.css");
		window.setScene(scene);
		window.show();
	}
}
