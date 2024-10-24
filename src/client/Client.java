package client;

import java.io.*;
import java.net.*;

public class Client {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private BufferedReader inputConsole = null;

	public Client(String ip, int port) throws UnknownHostException, IOException {
		startConnection(ip, port);
		chat();
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
	}

	// sends msg to Server
	public void sendMessage(String msg) {
		out.println(msg);
	}
	
	public String receiveMessage() throws IOException {
		return in.readLine();
	}

	// prints msg from Server, Client can write msg to Server
	public void chat() throws IOException {
		InputReader input = new InputReader(in);
		Thread thread = new Thread(input);
		thread.start();
		inputConsole = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("test");
		while (true) {
			String line = "";
			while (!line.equals("exit")) {
				line = inputConsole.readLine();
				sendMessage(line);
			}
			input.stop();
			stopConnection();
			System.out.println("Client disconnected");
		}
	}
}
