package server;

import java.util.List;

public class Session {
	GameLogic gL;
	List<ClientHandler> clients;
	boolean gameRunning = true;

	public Session(GameLogic gameLogic, List<ClientHandler> clients) {
		gL = gameLogic;
		this.clients = clients;
		InitializeGame();
		game();
	}

	private void game() {
		while (gameRunning) {
			Questions();
			Voting();
		}
	}

	private void InitializeGame() {
		broadcast("31" + gL.getPlayerNames());

		broadcast("32" + gL.getPlayerLife(0));

		broadcast("33" + gL.getTurnCount());

		// TODO Gamerules
	}

	private void Questions() {
		for (int i = 0; i < gL.getTurnCount(); i++) {
			for (int o = 0; o < gL.getNumberOfPlayers(); o++) {
				broadcast("2"+gL.getQuestion());
			}
		}
		//TODO Gamerule:Categories
	}

	private void Voting() {

	}

	private void broadcast(String msg) {
		for (ClientHandler client : clients) {
			client.sendMessage(msg);
		}
	}

}
