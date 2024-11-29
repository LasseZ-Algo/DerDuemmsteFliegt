package graphicComponents;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class TestJavaFx extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Hello World");
		primaryStage.setScene(scene);
		String css = this.getClass().getResource("Style.css").toExternalForm();
		scene.getStylesheets().add(css);
		// scene2.getStylesheets().add(css);
		primaryStage.setOnCloseRequest(event -> {
			event.consume();
			quitApplication(primaryStage);
		});
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	// Close application on red cross
	public void quitApplication(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Quit Program");
		alert.setHeaderText("Quit Application?");

		if (alert.showAndWait().get() == ButtonType.OK) {
			stage.close();
		}
	}
}