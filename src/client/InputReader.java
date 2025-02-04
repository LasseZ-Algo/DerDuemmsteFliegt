package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ObservableBooleanValue;
import questionAnswerClasses.AllAnswers;
import questionAnswerClasses.Answer;
import server.Player;

public class InputReader implements Runnable {
	private BufferedReader in;
	public ClientLogic clientLogic;
	boolean isRunning = true;
	private PrintWriter out;
	private Client client;

	public InputReader(BufferedReader in, PrintWriter out, Client client) {
		this.in = in;
		this.out = out;
		this.client = client;
		clientLogic = new ClientLogic();
	}

	public void stop() {
		isRunning = false;
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			// Platform.runLater(() -> {
			char inputType = '0';
			try {
				inputType = (char) in.read();
				switch (inputType) {
				case '1': // Chat msg
					try {
						clientLogic.addChat(in.readLine());
						;
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;

				case '2': // ActivePlayer and Question
					try {
						String[] playerAndQuestion = in.readLine().split("~");
						clientLogic.newQuestion(Integer.parseInt(playerAndQuestion[0]), playerAndQuestion[1]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case '3': // Initialize game (Players,[Gamerulez])
					try { // "Jochen~Hans$3~2"
						String[] initValues = in.readLine().split("§");
						if(initValues.length == 2){
							clientLogic.init(initValues[0], initValues[1]);
						}else{
							clientLogic.init(initValues[0]);
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case '4': // Player, Question, Answer, Solution
					try {
						String[] AnswerInfo = in.readLine().split("~");
						clientLogic.addAnswer(new Answer(AnswerInfo[0], AnswerInfo[1], AnswerInfo[2], AnswerInfo[3]));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case '5': // Player loses life
					try {
						clientLogic.loseLife(Integer.parseInt(in.readLine()));
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case '6': // start vote Voteable Players ~getrennt~
					try {
						List<Integer> playerList = new ArrayList<>();
						String[] votablePlayers = in.readLine().split("~");
						for (String player : votablePlayers) {
							playerList.add(Integer.parseInt(player));
						}
						clientLogic.voting(playerList);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				case '7': // Add Playername to Playerlist
					try {
						clientLogic.addPlayer(new Player(in.readLine()));
					} catch(IOException e) {
						e.printStackTrace();
					}
					break;

				case'8': // Remove Playername from Playerlist
					try {
						clientLogic.removePlayer(Integer.parseInt(in.readLine()));
					} catch(IOException e) {
						e.printStackTrace();
					}
					break;
				
				case '9': // sync with gamestate
					// int activePlayer $ String question $ List<Integer> gameRules $
					// List<Player> players $ AllAnswers answers
					try {
						String[] syncValues = in.readLine().split("$");
						int activePlayer = Integer.parseInt(syncValues[0]);
						int[] gameRules = new int[2];
						;
						List<Player> players = new ArrayList<>();
						;
						AllAnswers answers = new AllAnswers();
						boolean isVoting;

						String[] gameRulesArray = syncValues[2].split("~");
						for (int i = 0; i < gameRulesArray.length; i++) {
							gameRules[i] = Integer.parseInt(gameRulesArray[i]);
						}

						String[] playerArray = syncValues[3].split("~");
						for (String playerName : playerArray) {
							players.add(new Player(gameRules[0], playerName));
						}

						String[] answerArray = syncValues[4].split("§");
						for (String wholeAnswer : answerArray) {
							String[] wAA = wholeAnswer.split("~");
							answers.add(new Answer(wAA[0], wAA[1], wAA[2], wAA[3]));
						}

						if (activePlayer == -1) {
							isVoting = true;
						} else {
							isVoting = false;
						}

						clientLogic.sync(activePlayer, syncValues[1], gameRules, players, answers, isVoting);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				case 'p': //ping
					out.println("p");
					break;
					
				case 'd': //disconnect
					client.stopConnection();
					break;
					
				case '0': // no input
					break;

				default: // Unexpected input
					/*
					 * try { throw new IOException(); } catch (IOException e) { e.printStackTrace();
					 * }
					 */
				}
			} catch (IOException e) {
				isRunning = false;
			}
			// });
		}
		System.out.println("Client Ende");
	}
}
