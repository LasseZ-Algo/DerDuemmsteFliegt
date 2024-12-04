package graphicComponents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LobbyController {

	@FXML
	
	private Stage stage;
	private Scene scene;
	public Label lobbyName;
	public ListView list;
	
	public void setLobbyText(String lobbyname) {
		this.lobbyName.setText(lobbyname);
	}
	
	public void testEvent(ActionEvent e) {
		setLobbyText("Jochens Lobby");
	}
	
	
	public void disconnect(ActionEvent event) throws IOException {
		//TODO implement Disconnect
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		String css = this.getClass().getResource("Style.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.show();
	}
	
	public void sendmsg(ActionEvent event) {
		
	}
	
	public void fillplayers(ObservableList<String> player) {		
		list.setItems(player);
	}
}
