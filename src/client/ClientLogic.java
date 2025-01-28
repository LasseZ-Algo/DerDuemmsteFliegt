package client;

import java.util.List;
import java.util.Observable;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import questionAnswerClasses.AllAnswers;
import questionAnswerClasses.Answer;
import server.Player;

public class ClientLogic {

	private int activePlayer;
	private String question;
	private List<Integer> gameRules;
	private List<Player> players;
	private ObservableList<String> categories;
	private ObservableList<String> playerNames;
	private AllAnswers answers;
	private boolean isVoting;
	private ObservableList<String> chat = FXCollections.observableArrayList();

	void addChat(String msg) {
		Platform.runLater(() -> {
			chat.add(msg);
		});
	}

	public ObservableList<String> getChat() {
		return chat;
	}

	void addPlayer(Player player) {
		Platform.runLater(() -> {
			players.add(player);
			playerNames.add(player.getName());
		});
	}

	void removePlayer(int i) {
		Platform.runLater(() -> {
			playerNames.remove(i);
			players.remove(i);
		});
	}

	public ObservableList<String> getPlayerNames() {
		return playerNames;
	}

	public void addCategory(String category) {
		Platform.runLater(() -> {
			this.categories.add(category);
		});
	}

	public ObservableList<String> getCategories() {
		return this.categories;
	}

	public void removeCategory(int index) {
		Platform.runLater(() -> {
			this.categories.remove(index);
		});
	}

	void initGame(String players, String gameRules) {
		String[] gameRulesArray = gameRules.split("~");
		for (String ruleValue : gameRulesArray) {
			this.gameRules.add(Integer.parseInt(ruleValue));
		}
		String[] playerArray = players.split("~");
		for (String playerName : playerArray) {
			this.players.add(new Player(this.gameRules.get(0), playerName));
		}
		Platform.runLater(() -> {
			for (int i = 0; i < players.length(); i++) {
				playerNames.add(this.players.get(i).getName());
			}
		});
	}

	void addAnswer(Answer answer) {
		answers.add(answer);
		// TODO Display answer given
	}

	void loseLife(int player) {
		this.players.get(player).loseLife();
		if (this.players.get(player).getLife() == 0) {
			this.players.remove(player);
		}
		// TODO Display loss of Life // Death of Player
	}

	void voting(List<Integer> votablePlayers) {
		isVoting = true;
		if (votablePlayers.get(0) == -1) { // start vote with all players

		} else { // start vote with players in List

		}
	}

	void newQuestion(int activePlayer, String question) {
		isVoting = false;
		if (this.activePlayer == activePlayer) {
			// TODO Visualize SkipQuestion; Same Player
		}
		this.activePlayer = activePlayer;
		this.question = question;
	}

	void sync(int activePlayer, String question, List<Integer> gameRules, List<Player> players, AllAnswers answers,
			boolean isVoting) {

		this.activePlayer = activePlayer;
		this.question = question;
		this.gameRules = gameRules;
		this.players = players;
		this.answers = answers;
		this.isVoting = isVoting;
		if (isVoting) {
			// goto Vote
		} else {
			// goto Questions
		}
	}
}