package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

class ClientHandler extends Thread {
	private Socket clientSocket;
	private List<ClientHandler> clientList;
	private PrintWriter out;
	private BufferedReader in;
	private String name;
	private Session session;


	public ClientHandler(Socket socket, List<ClientHandler> clients, Session session) {
		this.clientSocket = socket;
		clientList = clients;
		this.session = session;
	}

	public void run() {

		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			name = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(name);

		broadcast("Hello " + name);

		while (true) {
			char inputType = '0';
			try {
				inputType = (char) in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(inputType);
			switch (inputType) {
			case '1': // Chat msg
				String inputLine;
				try {
					if ((inputLine = in.readLine()) != null) {
						broadcast(1 + inputLine);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case '2': // PlayerAnswers
				// TODO handle PlayerAnswers
				try {
					session.answerGiven(in.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case '3': // PlayerVoteDerDümmste
				String vote;
				try {
					vote = in.readLine();
					session.vote(Integer.parseInt(vote));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case '4': // PlayerSkipVote
				String skipVote;
				try {
					if ((skipVote = in.readLine()) == "1") {
						session.skipVote();
					} else if (skipVote == "0") {
						session.dontSkipVote();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case '5': // PlayerVoteSkipQuestion
				String skipVoteQuestion;
				try {
					if ((skipVoteQuestion = in.readLine()) == "1") {
						session.skipQuestion();
					} else if (skipVoteQuestion == "0") {
						session.dontSkipQuestion();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case '9': // initialize Game
				try {
					String initialize = in.readLine();
					String[] initializer = initialize.split("~");
					int[] gamerules = {0, 0};
					for(int i = 0; i < initializer.length; i++) { 
						gamerules[i] = Integer.parseInt(initializer[i]); 
					}
					session.initializeGame(gamerules, clientList);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case '0': // No Input

				break;
			default: // Unexpected input
				/*
				 * try { throw new IOException(); } catch (IOException e) { e.printStackTrace();
				 * }
				 */
			}
			/*
			 * String test = "abc~def~ghe:rts"; 
			 * String[] testarray = test.split("~");
			 * for(String string : testarray) { System.out.println(string); }
			 */
		}

		/*
		 * TODO safely close in.close(); out.close(); clientSocket.close();
		 */
	}

	// sends msg to client
	public void sendMessage(String msg) {
		out.println(msg);
	}

	// Broadcasts message from server to all clients.
	public void broadcast(String msg) {
		for (ClientHandler client : clientList) {
			client.sendMessage(msg);
		}
	}

	public String getPlayerName() {
		return name;
	}
}
