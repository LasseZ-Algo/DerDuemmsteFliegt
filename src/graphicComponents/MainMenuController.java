package graphicComponents;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import client.Client;
import server.Server;

public class MainMenuController {

	@FXML
	public TextField ip;
	private Stage stage;
	private Scene scene;
	private Parent root;
	public TextField changeUsername;
	public Client client;
	public Server server;
	
	@FXML
	private AnchorPane scenePane;

	public void switchToCredits(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Credits.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		String css = this.getClass().getResource("Style.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.show();
	}

	public void switchToMainmenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		String css = this.getClass().getResource("Style.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.show();
	}

	public void quitApplication(ActionEvent e) {
		stage = (Stage) scenePane.getScene().getWindow();
		stage.close();
	}
	
	public void getUsername(ActionEvent event) {
		String username = changeUsername.getText();
		System.out.println(username);
	}
	
	public void switchToEditor(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Editor.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		String css = this.getClass().getResource("Style.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.show();
	}
	
	public void switchToLobby(ActionEvent event) throws IOException {
		//server = Server.getInstance(ViewModel viewModel);
		//client = new Client("127.0.0.1", 5555, changeUsername.getText());
		Parent root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		String css = this.getClass().getResource("Style.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.show();
	}
	
	public void switchToGame(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		String css = this.getClass().getResource("Style.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.show();
	}
	
	public void connect(ActionEvent event) throws IOException {
		try{
			//client = new Client(ip.getText(), 5555, changeUsername.getText(), ViewModel viewModel);
			Parent root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			String css = this.getClass().getResource("Style.css").toExternalForm();
			scene.getStylesheets().add(css);
			stage.show();
			
		}catch (Exception e){
			//TODO give Error Message to Player
		}
	}

	public void exampleMethod(ActionEvent e) {

	}

}