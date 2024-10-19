package server;

import java.net.*;
import java.util.List;
import java.io.*;

public final class Server {
	private static Server INSTANCE;
	private ServerSocket serverSocket;
	private List<ClientHandler> clients;
	
	private Server() throws IOException {
		start(123);
	}
	
	public static Server getInstance() throws IOException {
		if(INSTANCE == null) {
			INSTANCE = new Server();
		}
		return INSTANCE;
	}

	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		while (true) {
			ClientHandler client = new ClientHandler(serverSocket.accept());
			client.start();
			clients.add(client);
		}
	}

	public void stop() throws IOException {
		serverSocket.close();
	}
	
	public void broadcast(String msg) {
		for(int i = 0; i < clients.size(); i++) {
			clients.get(i).sendMessage(msg);
		}
	}
	
}