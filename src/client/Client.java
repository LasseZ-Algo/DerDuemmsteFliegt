package client;

import java.io.*;
import java.net.*;

public class Client {
	 private Socket clientSocket;
	 private PrintWriter out;
	 private BufferedReader in;
	 
	 public void startConnection(String ip, int port) throws UnknownHostException, IOException {
		 clientSocket = new Socket(ip, port);
		 out = new PrintWriter(clientSocket.getOutputStream(), true);
		 in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	 }
	 
	 public void stopConnection() throws IOException {
		 in.close();
		 out.close();
		 clientSocket.close();
	 }
}
