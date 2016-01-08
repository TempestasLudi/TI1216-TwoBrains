package ml.vandenheuvel.ti1216.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LoginGUI.display();
	}

}
