package server;

import java.util.List;

public class GameLogic {
	private Player[] player;
	private int turnCount; //Number of Turns per Round / Time per Turn
	private AllQuestions quests;
	private AllAnswers anws;
	
	public GameLogic(int life, int turnCount, List<ClientHandler> clients){
		this.turnCount = turnCount;
		createPlayers(clients, life);
	}
	
	private void createPlayers(List<ClientHandler> clients, int life) {
		int i=0;
		for(ClientHandler c : clients) {
			player[i] = new Player(life, c.getPlayerName());
			i++;
		}
	}
	
	
	
}
