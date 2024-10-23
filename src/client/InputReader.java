package client;

import java.io.BufferedReader;
import java.io.IOException;

public class InputReader implements Runnable{
	private BufferedReader in;
	boolean isRunning = true;

	public InputReader(BufferedReader in) {
		this.in = in;
	}
	
	public void stop() {
		isRunning = false;
	}
	
	@Override
	public void run() {
		while(isRunning) {
			try {
				System.out.println(in.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
