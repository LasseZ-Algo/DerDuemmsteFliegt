package graphicComponents;

import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import client.Client;
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
	public ListView<String> list;
	public ListView<String> chatList;
	public ListView<String> categoryList;
	public ListView<String> gamerulesList;
	private Data data;
	public TextField chatInput;
	

	public void setLobbyText(String lobbyname) {
		this.lobbyName.setText(lobbyname);
	}

	public void testEvent(ActionEvent e) {
		data.getServer().kick(1);
	}

	public void disconnect(ActionEvent event) throws IOException {
		// TODO implement Disconnect
		data.shutdown();
	}

	public void sendmsg(ActionEvent event) {
		data.getClient().sendMessage(chatInput.getText());
		chatInput.clear();
	}

	public void fillplayers(ObservableList<String> player) {
		list.setItems(player);
	}

	public void chat(ObservableList<String> chat) {
		chatList.setItems(chat);
	}

	public void forcedDisconnect() {
		stage = data.getStage();
		stage.setScene(data.getMainMenu());
	}

	public void init(Data data) {
		this.data = data;
		chat(data.getClient().input.clientLogic.getChat());
		fillplayers(data.getClient().input.clientLogic.getPlayerNames());
		data.getClient().setMenu(this);
	}
}
