package server;

import java.util.ArrayList;
import java.util.List;

import questionAnswerClasses.AllAnswers;
import questionAnswerClasses.AllQuestions;
import questionAnswerClasses.Answer;
import questionAnswerClasses.Question;

public class GameLogic {
	private List<Player> player;
	private int turnCount; // Number of Turns per Round / Time per Turn
	private AllQuestions quests;
	private AllAnswers anws;
	private int[] gamerules;
	// TODO Save Gamerules
	
	public void initializeGame(int[] gamerules, List<ClientHandler> clients) {
		this.gamerules = gamerules;
		this.turnCount = gamerules[1];
		createPlayers(clients, gamerules[0]);
	}

	private void createPlayers(List<ClientHandler> clients, int life) {
		for (ClientHandler c : clients) {
			player.add(new Player(life, c.getPlayerName()));
		}
	}

	public int[] getGameRules() {
		return this.gamerules;
	}
	
	public String getGameRulesString() {
		String gameRulesString = "";
		for(int i = 0; i < gamerules.length; i++) {
			if(i < gamerules.length) {
				gameRulesString += gamerules[i] + "~";
			} else {
				gameRulesString += gamerules[i];
			}
		}
		return gameRulesString;
	}
	
	public Question getQuestion() {
		return quests.getQuestion();
	}

	// returns question of specific category
	public Question getQuestion(int categoryIndex) {
		return quests.getQuestion(categoryIndex);
	}

	public void saveAnswer(Question question, String playername, String answer) {
		anws.add(question, playername, answer);
	}

	public void saveAnswer(Answer answer) {
		anws.add(answer);
	}

	public List<Answer> getAnswers() {
		return anws.getAnswers();
	}
	
	public String getAllAnswers() {
		return anws.getAllAnswersString();
	}

	public String getPlayerName(int p) {
		return player.get(p).getName();
	}

	public int getPlayerLife(int p) {
		return player.get(p).getLife();
	}

	public int getNumberOfPlayers() {
		return player.size();
	}

	public int getTurnCount() {
		return turnCount;
	}

	public String getPlayerNames() {
		String playerNamesString = "";
		for(int i = 0; i < player.size(); i++) {
			if(i < player.size()) {
				playerNamesString += player.get(i) + "~";
			} else {
				playerNamesString += player.get(i);
			}
		}
		return playerNamesString;
	}

	public boolean playerLoseLife(int mostVotedPlayer) {
		player.get(mostVotedPlayer).loseLife();
		if(player.get(mostVotedPlayer).getLife() == 0) {
			player.remove(mostVotedPlayer);
			return true;
		}
		return false;
	}

	public List<Integer> voting(List<Integer> votes) {
		int[] voteCount = new int[player.size()];
		for (int i : votes) {
			voteCount[i]++;
		}
		List<Integer> mostVote = new ArrayList<>();
		int mostVotes = -1;
		for (int o = 0; o < voteCount.length; o++) {
			if (voteCount[o] > mostVotes) {
				mostVote.clear();
				mostVotes = voteCount[o];
			}
			if (voteCount[o] == mostVotes) {
				mostVote.add(o);
			}
		}
		return mostVote;
	}

	public boolean skip(int skip) {
		if (skip >= (player.size() / 2) + 1) {
			return true;
		} else {
			return false;
		}
	}
}
