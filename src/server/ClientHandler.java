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

		while (true) { //Sends Message to Server, Server broadcasts Message
			String inputLine;
			try {
				if((inputLine = in.readLine()) != null){
					broadcast(inputLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/*
		 * in.close(); out.close(); clientSocket.close();
		 */
	}

	//sends msg to client
	public void sendMessage(String msg) {
		out.println(msg);
	}
	
	//Broadcasts message from server to all clients.
	public void broadcast(String msg) {
		for(ClientHandler client : clientList) {
			client.sendMessage(msg);
		}
	}
}
