package testClasses;

import java.io.IOException;
import java.net.UnknownHostException;

import client.Client;

public class testCLient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client("26.233.84.27", 5555, "Peter");
		System.out.println("Client startet");
	}

}
