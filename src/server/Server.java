package server;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public final class Server {
	private static Server INSTANCE;
	private ServerSocket serverSocket;
	private List<ClientHandler> clients = new ArrayList<>();
	
	private Server() throws IOException {
		start(5555);
	}
	
	public static Server getInstance() throws IOException {
		if(INSTANCE == null) {
			INSTANCE = new Server();
		}
		
		return INSTANCE;
	}

	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		GameLogic gameLogic = new GameLogic();
		Session session = new Session(gameLogic, clients);
		System.out.println("Server startet");
		while (true) {
			ClientHandler client = new ClientHandler(serverSocket.accept(), clients, session);
			client.start();
			clients.add(client);
		}
	}

	public void stop() throws IOException {
		serverSocket.close();
	}
}