package server;

import java.util.List;

import questionAnswerClasses.Answer;
import questionAnswerClasses.Question;

public class Session {
	GameLogic gL;
	List<ClientHandler> clients;
	int skipQuestionCount = 0;
	int skipVoteCount = 0;
	int activePlayer = -1;
	Question activeQuestion;
	List<Integer> votes;

	public Session(GameLogic gameLogic, List<ClientHandler> clients) {
		gL = gameLogic;
		this.clients = clients;
		initializeGame();
	}

	void skipQuestion() {
		skipQuestionCount++;
		if (gL.skip(skipQuestionCount)) {
			// Broadcast to all Players
			// get new question (and broadcast)
			skipQuestionCount = 0;
		}
	}

	void dontSkipQuestion() {
		skipQuestionCount--;
	}

	void skipVote() {
		skipVoteCount++;
		if (gL.skip(skipQuestionCount)) {
			// Broadcast to all Players
			// skip the vote, proceed to questions
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
		broadcast("4" + fullAnswer.getBigAnswer());
		nextPlayer();
	}

	void nextPlayer() {
		activePlayer++;
		if (activePlayer <= gL.getNumberOfPlayers()) {
			activeQuestion = newQuestion();
			broadcast("2" + activeQuestion.getQuery());
		} else {
			activePlayer = -1;
			broadcast("52");
		}
	}

	void vote(int vote) {
		votes.add(vote);
		if (votes.size() >= gL.getNumberOfPlayers()) {
			List<Integer> voteWinner = gL.voting(votes);
			if (voteWinner.size() == 1) { // PlayerLoseLife / PlayerDies
				if (gL.playerLoseLife(voteWinner.get(0))) {
					broadcast("50" + voteWinner.get(0));
				} else {
					broadcast("51" + voteWinner.get(0));
				}
			} else { // newVote with mostVotedPlayers
				String winners = "";
				for (int i : voteWinner) {
					winners = winners + "~" + i;
				}
				broadcast("52" + winners);
			}
		}
	}

	private void initializeGame() {
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
