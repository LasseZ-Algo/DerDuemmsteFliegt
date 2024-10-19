package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler extends Thread {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
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
					Server.getInstance().broadcast(inputLine);
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
}
