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

	public ClientHandler(Socket socket, List<ClientHandler> clients) {
		this.clientSocket = socket;
		clientList = clients;
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
				inputType = (char)in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(inputType);
			switch (inputType) {
			case '1':				//Chat msg
				String inputLine;
				try {
					if ((inputLine = in.readLine()) != null) {
						broadcast(inputLine);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case '2':				//PlayerAnswers	
				//TODO handle PlayerAnswers
			break;
			case '3':				//PlayerVote
			
			break;
			case '0':			//No Input
				
			break;
			default:			//Unexpected input
				/*try {
					throw new IOException();
				} catch (IOException e) {
					e.printStackTrace();
				}
				*/
			}

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
