package testClasses;

import java.io.IOException;
import java.net.UnknownHostException;

import client.Client;

public class testCLient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client("10.188.6.16", 5555);
		System.out.println("Client startet");
	}

}
