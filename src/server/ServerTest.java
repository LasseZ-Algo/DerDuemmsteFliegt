package server;

import java.net.*;
import java.io.*;

public class ServerTest {
	    private ServerSocket serverSocket;
	    
	    public void start(int port) throws IOException {
	    	serverSocket = new ServerSocket(port);
	    	while(true) {
	    		new ClientHandler(serverSocket.accept()).start();
	    	}
	    }
	    	public void stop() throws IOException {
	    		serverSocket.close();
	    	}
	    }