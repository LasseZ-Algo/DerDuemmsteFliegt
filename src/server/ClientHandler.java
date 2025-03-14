package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

class ClientHandler extends Thread {
	private Socket clientSocket;
	private List<ClientHandler> clientList;
	private PrintWriter out;
	private BufferedReader in;
	private String name;
	private Session session;
	private boolean isRunning;
	private Clock clock;

	public ClientHandler(Socket socket, List<ClientHandler> clients, Session session) {
		this.clientSocket = socket;
		try {
			socket.setSoTimeout(10000);
		} catch (SocketException e) {
			disconnect();
		}
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

		// ViewModel.LobbyController.addplayer
		initialize();
		isRunning = true;
		while (isRunning) {
			char inputType = '0';
			try {
				inputType = (char) in.read();
			} catch (IOException e) {
				isRunning = false;
			}
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

			case 'p': // Ping
				break;

			case 'd': // disconnect
				disconnect();

				break;

			case '0': // No Input

				break;
			default: // Unexpected input
				/*
				 * try { throw new IOException(); } catch (IOException e) { e.printStackTrace();
				 * }
				 */
			}
		}
		/*
		 * TODO safely close in.close(); out.close(); clientSocket.close();
		 */
	}

	public void disconnect() {
		try {
			out.println("d");
			clock.halt();
			isRunning = false;
			in.close();
			out.close();
			clientSocket.close();
			System.out.println(name + " disconnected.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (!session.inGame()) {
				for (int i = 0; i < clientList.size(); i++) {
					if (clientList.get(i) == this) {
						session.removePlayer(i);
						clientList.remove(i);
					}
				}
			}
		}
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

	// TODO Initialize the newly connected player
	private void initialize() {
		System.out.println(name + " connected.");
		broadcast(1 + "Hello " + name);
		int[] multi = new int[1];
		if (!session.inGame()) {
			for (int i = 0; i < clientList.size(); i++) {
				if (clientList.get(i) == this) {
					multi[0] = i;
					session.addPlayer(new Player(name), multi);
				}
			}
		}
		sendMessage("3" + session.init());
		clock = new Clock(this);
		clock.start();
	}
}

class Clock extends Thread {
	private boolean isRunning;
	private ClientHandler handler;
	
	public Clock(ClientHandler handler) {
		this.handler = handler;
	}
	
	public void halt() {
		isRunning = false;
	}
	
	public void run(){
		isRunning = true;
		while(isRunning) {
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			handler.sendMessage("p");
		}
	}
}
