package graphicComponents;

import java.io.IOException;

import client.Client;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.Server;

public class Data {
	private Client client;
	private Server server;
	private boolean isAdmin;
	private Scene MainMenu;
	private Stage stage;
	
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public Scene getMainMenu() {
		return MainMenu;
	}
	public void setMainMenu(Scene mainMenu) {
		MainMenu = mainMenu;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void shutdown() {
		if(server != null) {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(client != null) {
			try {
				client.stopConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
