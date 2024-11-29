package graphicComponents;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LobbyController {

	@FXML
	
	public Label lobbyName;
	
	public void setLobbyText(String lobbyname) {
		this.lobbyName.setText(lobbyname);
	}
	
	public void testEvent(ActionEvent e) {
		setLobbyText("Jochens Lobby");
	}
	
}
