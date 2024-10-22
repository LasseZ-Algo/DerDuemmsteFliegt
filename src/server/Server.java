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
		System.out.println("Server staret");
		while (true) {
			ClientHandler client = new ClientHandler(serverSocket.accept(), clients);
			client.start();
			clients.add(client);
			System.out.println("Client connected");
		}
	}

	public void stop() throws IOException {
		serverSocket.close();
	}
	
}