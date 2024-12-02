package testClasses;

import java.io.IOException;
import java.security.Timestamp;
import java.util.Date;
import java.util.Random;

import client.Client;
import server.Server;

public class testConnection {

	public static void main(String[] args) throws IOException {	
		Server server = new Server();
		server.start();
	}
}
