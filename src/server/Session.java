package server;

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
			broadcast("52"); // Start vote
		}
	}

	void vote(int vote) {
		votes.add(vote);
		if (votes.size() >= gL.getNumberOfPlayers()) {
			List<Integer> voteWinner = gL.voting(votes);
			if (voteWinner.size() == 1) {
				if (gL.playerLoseLife(voteWinner.get(0))) {
					broadcast("50~" + voteWinner.get(0));	//Player dead
				} else {
					broadcast("51~" + voteWinner.get(0));	//Player lose life
				}
				nextPlayer();	//next Question
			} else { // newVote with mostVotedPlayers
				String winners = "";
				for (int i : voteWinner) {
					winners = winners + "~" + i;
				}
				broadcast("52" + winners);
			}
		}
	}

	public void initializeGame(int[] gamerules, List<ClientHandler> clients) {
		gL.initializeGame(gamerules, clients);
		broadcast("31~" + gL.getPlayerNames());

		broadcast("32~" + gL.getPlayerLife(0));

		broadcast("33~" + gL.getTurnCount());

		// TODO Gamerules
	}

	private void broadcast(String msg) {
		for (ClientHandler client : clients) {
			client.sendMessage(msg);
		}
	}

}
