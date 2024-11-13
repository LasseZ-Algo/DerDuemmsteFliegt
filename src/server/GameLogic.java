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
	// TODO Save Gamerules

	public GameLogic(int life, int turnCount, List<ClientHandler> clients) {
		this.turnCount = turnCount;
		createPlayers(clients, life);
	}

	private void createPlayers(List<ClientHandler> clients, int life) {
		for (ClientHandler c : clients) {
			player.add(new Player(life, c.getPlayerName()));
		}
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
		String players = "";
		for (Player p : player) {
			players = players + "~" + p.getName();
		}
		return players;
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
