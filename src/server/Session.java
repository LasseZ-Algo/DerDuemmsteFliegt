package server;

import java.util.List;

public class Session{
	GameLogic gL;
	List<ClientHandler> clients;
	boolean gameRunning = true;
	int skipQuestionCount = 0;

	public Session(GameLogic gameLogic, List<ClientHandler> clients) {
		gL = gameLogic;
		this.clients = clients;
		InitializeGame();
	}
	
	void SkipQuestion() {
		skipQuestionCount++;
		if (gL.skipQuestion(skipQuestionCount)) {
			//Broadcast to all Players
			//get new question (and broadcast)
			skipQuestionCount = 0;
		}
	}
	
	void DontSkipQuestion() {
		skipQuestionCount--;
	}
	
	

	private void InitializeGame() {
		broadcast("31" + gL.getPlayerNames());

		broadcast("32" + gL.getPlayerLife(0));

		broadcast("33" + gL.getTurnCount());

		// TODO Gamerules
	}

	private void broadcast(String msg) {
		for (ClientHandler client : clients) {
			client.sendMessage(msg);
		}
	}

}
