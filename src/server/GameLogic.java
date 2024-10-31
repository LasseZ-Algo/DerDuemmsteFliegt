package server;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
	private Player[] player;
	private int turnCount; //Number of Turns per Round / Time per Turn
	private AllQuestions quests;
	private AllAnswers anws;
	//TODO Save Gamerules
	
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
	
	public Question getQuestion() {
		return quests.getQuestion();
	}
	
	//returns question of specific category
	public Question getQuestion(int categoryIndex) {
		return quests.getQuestion(categoryIndex);
	}
	
	public void saveAnswers(Question question, String playername, String answer) {
		anws.add(question, playername, answer);
	}
	
	public List<Answer> getAnswers(){
		return anws.getAnswers();
	}
	
	public String getPlayerName(int p) {
		return player[p].getName();
	}
	
	public int getPlayerLife(int p) {
		return player[p].getLife();
	}
	
	public int getNumberOfPlayers() {
		return player.length;
	}
	
	public int getTurnCount() {
		return turnCount;
	}
	
	public boolean playerLoseLife(int mostVotedPlayer) {
		player[mostVotedPlayer].loseLife();
		return (0 == player[mostVotedPlayer].getLife());
	}
	
	public List<Integer> voting(int[] votes) {
		int[] voteCount = new int[player.length];
		for(int i : votes) {
			voteCount[i]++;
		}
		List<Integer> mostVote = new ArrayList<>();
		int mostVotes = -1;
		for(int o = 0; o < voteCount.length; o++) {
			if (voteCount[o] > mostVotes) {
				mostVote.clear();
				mostVotes = voteCount[o];
			}
			if(voteCount[o] == mostVotes) {
				mostVote.add(o);
			}
		}
		return mostVote;
	}
	
	public boolean skipQuestion(boolean[] toSkip) {
		int skip = 0;
		for(int i = 0; i < toSkip.length; i++) {
			if (toSkip[i] == true) {
				skip++;
			}
		}
		if(skip >= (player.length / 2) + 1) {
			return true;
		} else {
			return false;
		}
	}
	
}
