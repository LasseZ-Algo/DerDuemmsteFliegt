package client;

import java.io.*;
import java.net.*;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;

public class Client {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private String name;
	public InputReader input;
	private boolean isConnected = true;
	public ObservableBooleanValue Connected = new SimpleBooleanProperty(isConnected);
	

	public Client(String ip, int port, String name) throws UnknownHostException, IOException {
		startConnection(ip, port);
		this.name = name;
		startchat();
	}

	private void startConnection(String ip, int port) throws UnknownHostException, IOException {
		clientSocket = new Socket(ip, port);
		try {
			clientSocket.setSoTimeout(10000);
		} catch (SocketException e) {
			stopConnection();
		}
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		Platform.runLater(() -> {
			isConnected = true;
		});
	}

	public void stopConnection() throws IOException {
		out.println("d");
		out.close();
		clientSocket.close();
		input.stop();
		System.out.println("Client disconnected");
		Platform.runLater(() -> {
			isConnected = false;
		});
	}

	// sends msg to Server
	public void sendMessage(String msg) {
		out.println(1 + name + ": " + msg);
	}

	// prints msg from Server, Client can write msg to Server
	private void startchat() throws IOException {
		out.println(name);
		input = new InputReader(in, out, this);
		Thread thread = new Thread(input);
		thread.start();
	}
}
