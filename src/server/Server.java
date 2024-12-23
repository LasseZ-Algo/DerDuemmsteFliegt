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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() throws IOException {
		serverSocket.close();
		isRunning = false;
	}
}