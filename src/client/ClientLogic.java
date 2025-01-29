package client;

import java.util.ArrayList;
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
	private int[] gameRules = new int[2];
	private List<Player> players = new ArrayList<Player>();
	private ObservableList<String> categories = FXCollections.observableArrayList();
	private ObservableList<String> playerNames = FXCollections.observableArrayList();
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

	void init(String players, String gameRules) {
		String[] gameRulesArray = gameRules.split("~");
		for (int i = 0; i < gameRulesArray.length; i++) {
			this.gameRules[i] = Integer.parseInt(gameRulesArray[i]);
		}
		String[] playerArray = players.split("~");
		for (String playerName : playerArray) {
			this.players.add(new Player(this.gameRules[0], playerName));
		}
		Platform.runLater(() -> {
			for (int i = 0; i < this.players.size(); i++) {
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

	void sync(int activePlayer, String question, int[] gameRules, List<Player> players, AllAnswers answers,
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