package client;

import java.io.*;
import java.net.*;

public class Client {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private BufferedReader inputConsole = null;
	private String name;

	public Client(String ip, int port, String name) throws UnknownHostException, IOException {
		startConnection(ip, port);
		this.name = name;
		startchat();
	}

	private void startConnection(String ip, int port) throws UnknownHostException, IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	public void stopConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		System.out.println("Client disconnected");
	}

	// sends msg to Server
	public void sendMessage(String msg) {
		out.println(1 + msg);
	}

	// prints msg from Server, Client can write msg to Server
	private void startchat() throws IOException {
		out.println(name);
		InputReader input = new InputReader(in);
		Thread thread = new Thread(input);
		thread.start();
	}
}
