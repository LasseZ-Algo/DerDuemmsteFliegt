package server;

import java.util.ArrayList;
import java.util.List;

import questionAnswerClasses.AllAnswers;
import questionAnswerClasses.AllQuestions;
import questionAnswerClasses.Answer;
import questionAnswerClasses.Question;

public class GameLogic {
	private List<Player> player = new ArrayList<Player>();
	private int turnCount; // Number of Turns per Round / Time per Turn
	private AllQuestions quests;
	private AllAnswers anws;
	private int[] gamerules = new int[2];
	private boolean inGame;
	// TODO Save Gamerules
	
	public boolean inGame() {
		return inGame;
	}
	
	public String getGameRulesString() {
		String gameRulesString = "";
		for(int i = 0; i < gamerules.length; i++) {
			if(i < gamerules.length - 1) {
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
			if(i < player.size() - 1) {
				playerNamesString += player.get(i).getName() + "~";
			} else {
				playerNamesString += player.get(i).getName();
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

	public List<Player> getPlayer() {
		return player;
	}
	
	public void addPlayer(Player player) {
		this.player.add(player);
	}
	
	public void removePlayer(int index) {
		this.player.remove(index);
	}

	public int[] getGameRules() {
		return gamerules;
	}
	
	public void setGameRule(int index, int value) {
		gamerules[index] = value;
	}
	
}
