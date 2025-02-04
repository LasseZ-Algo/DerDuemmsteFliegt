package server;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public final class Server extends Thread {
	private ServerSocket serverSocket;
	private List<ClientHandler> clients = new ArrayList<>();
	int port = 5555;
	boolean isRunning = true;

	public Server() throws IOException {

	}

	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			GameLogic gameLogic = new GameLogic();
			Session session = new Session(gameLogic, clients);
			System.out.println("Server startet");
			while (isRunning) {
				ClientHandler client = new ClientHandler(serverSocket.accept(), clients, session);
				client.start();
				clients.add(client);
			}
		} catch (IOException e) {

		}
		System.out.println("Server Ende");
	}

	public void close() throws IOException {
		for(ClientHandler client : clients) {
			client.sendMessage("d");
		}
		isRunning = false;
		serverSocket.close();
	}
	
	public void kick(int i) {
		clients.get(clients.size()-1).disconnect();
	}
}