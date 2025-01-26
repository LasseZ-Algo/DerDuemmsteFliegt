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
	private Data data;
	public TextField ip;
	private Stage stage;
	private Scene scene;
	private Parent root;
	public TextField changeUsername;

	
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
		data.shutdown();
		stage.close();
		System.exit(0);
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
		data.setAdmin(true);
		data.setServer(new Server());
		Thread thread = new Thread(data.getServer());
		thread.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		data.setClient(new Client("127.0.0.1", 5555, changeUsername.getText())); 
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Lobby.fxml"));
		Parent root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		String css = this.getClass().getResource("Style.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.show();
		LobbyController lobby = loader.getController();
		
		lobby.init(data);
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
			data.setAdmin(false);
			data.setClient(new Client(ip.getText(), 5555, changeUsername.getText()));
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Lobby.fxml"));
			Parent root = loader.load();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			String css = this.getClass().getResource("Style.css").toExternalForm();
			scene.getStylesheets().add(css);
			stage.show();
			LobbyController lobby = loader.getController();
			lobby.init(data);
			
		}catch (Exception e){
			System.out.println("Fehler beim Verbinden");
			e.printStackTrace();
		}
	}
	
	public void init(Data data) {
		this.data = data;
	}
	
	public void exampleMethod(ActionEvent e) {

	}

}