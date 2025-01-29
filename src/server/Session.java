package server;

import java.util.Arrays;
import java.util.List;

import questionAnswerClasses.Answer;
import questionAnswerClasses.Question;

public class Session {
	private GameLogic gL;
	private List<ClientHandler> clients;
	private int skipQuestionCount = 0;
	private int skipVoteCount = 0;
	private int activePlayer = -1;
	private Question activeQuestion;
	private List<Integer> votes;

	public Session(GameLogic gameLogic, List<ClientHandler> clients) {
		gL = gameLogic;
		this.clients = clients;
	}

	void skipQuestion() {
		skipQuestionCount++;
		if (gL.skip(skipQuestionCount)) {
			activeQuestion = newQuestion();
			broadcast("2~" + activePlayer + "~" + activeQuestion.getQuery());
			skipQuestionCount = 0;
		}
	}

	void dontSkipQuestion() {
		skipQuestionCount--;
	}

	void skipVote() {
		skipVoteCount++;
		if (gL.skip(skipVoteCount)) {
			nextPlayer();	// skip the vote, proceed to questions
			skipVoteCount = 0;
		}
	}

	void dontSkipVote() {
		skipVoteCount--;
	}

	Question newQuestion() {
		return gL.getQuestion();
	}

	Question newQuestion(int category) {
		return gL.getQuestion(category);
	}

	void answerGiven(String answer) {
		Answer fullAnswer = new Answer(activeQuestion, gL.getPlayerName(activePlayer), answer);
		gL.saveAnswer(fullAnswer);
		broadcast("4~" + fullAnswer.getBigAnswer());
		nextPlayer();
	}

	void nextPlayer() {
		activePlayer++;
		if (activePlayer <= gL.getNumberOfPlayers()) {
			activeQuestion = newQuestion();
			broadcast("2~" + activePlayer + "~" + activeQuestion.getQuery());
		} else {
			activePlayer = -1;
			broadcast("6~-1"); // Start vote
		}
	}

	void vote(int vote) {
		votes.add(vote);
		if (votes.size() >= gL.getNumberOfPlayers()) {
			List<Integer> voteWinner = gL.voting(votes);
			if (voteWinner.size() == 1) {
				if (gL.playerLoseLife(voteWinner.get(0))) {
					broadcast("5~" + voteWinner.get(0));	//Player dead
				} else {
					broadcast("5~" + voteWinner.get(0));	//Player lose life
				}
				nextPlayer();	//next Question
			} else { // newVote with mostVotedPlayers
				String winners = "";
				for (int i : voteWinner) {
					winners = winners + "~" + i;
				}
				broadcast("6" + winners);
			}
		}
	}

	public void addPlayer(Player player) {
		gL.addPlayer(player);
	}
	
	public void removePlayer(int index) {
		gL.removePlayer(index);
	}
	
	public void setGameRule(int index, int value) {
		gL.setGameRule(index, value);
	}

	private void broadcast(String msg) {
		for (ClientHandler client : clients) {
			client.sendMessage(msg);
		}
	}
	
	public boolean inGame() {
		return gL.inGame();
	}
	
	public String init() {
		return gL.getPlayerNames() + "§" + gL.getGameRulesString();
	}
	
	//int activePlayer § String question § List<Integer> gameRules § 
	//List<Player> players § AllAnswers answers
	public void sync(ClientHandler client) {
		String sync = activePlayer + "§" 
						+ activeQuestion.getQuery() + "§"
						+ gL.getGameRulesString() + "§" 
						+ gL.getPlayerNames() + "§" 
						+ gL.getAllAnswers();
		client.sendMessage(sync);
	}

}
