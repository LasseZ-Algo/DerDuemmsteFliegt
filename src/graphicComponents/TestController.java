package graphicComponents;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TestController {

	@FXML

	private Stage stage;
	private Scene scene;
	private Parent root;

	public void switchToCredits(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Credits.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
        String css = this.getClass().getResource("Style.css").toExternalForm();
        scene.getStylesheets().add(css);
		stage.show();
	}

	public void switchToMainmenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("testoffx.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
        String css = this.getClass().getResource("Style.css").toExternalForm();
        scene.getStylesheets().add(css);
		stage.show();
	}

	public void exampleMethod(ActionEvent e) {
		
	}

}