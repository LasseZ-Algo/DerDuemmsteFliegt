package client;

import java.util.List;

import server.Player;

public class ClientLogic {

	private int activePlayer;
	private String question;
	private List<Integer> gameRules;
	private List<Player> player;
	
//	private List<RoundHistory> history;
 	
	
	void initGame(String players, String gameRules) {
		String[] gameRulesArray = gameRules.split("~");
		for(String ruleValue : gameRulesArray) {
			this.gameRules.add(Integer.parseInt(ruleValue));
		}
		String[] playerArray = players.split("~");
		for(String playerName : playerArray) {
			this.player.add(new Player(this.gameRules.get(0), playerName));
		}
	}
	
	void newQuestion(int activePlayer, String question) {
		if(this.activePlayer == activePlayer) {
			//TODO Visualize SkipQuestion; Same Player
		}
		this.activePlayer = activePlayer;
		this.question = question;
	}
}
