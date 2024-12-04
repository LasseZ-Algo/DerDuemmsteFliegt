package graphicComponents;

import client.Client;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class ViewModel {
	private Object activeScene;
	private ObservableList<String> player;
	private Client client;
	
	public Object getActiveScene() {
		return activeScene;
	}
	public void setActiveScene(Object activeScene) {
		this.activeScene = activeScene;
	}
	public ObservableList<String> getPlayer() {
		return player;
	}
	public void setPlayer(ObservableList<String> player) {
		this.player = player;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
