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
			while(true) {
				char inputType = '0';
				try {
					inputType = (char) in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch (inputType) {
				case '1': // Chat msg
					try {
						System.out.println(in.readLine());
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
					
				case '2': //Question
					//TODO get Question
					break;
				
				case '3': //Initialize game (Players, Lifes, Rounds, [Gamerulez])
					break;
					
				case '4': // Player, Question, Answer, Solution
					//TODO get Player, Question, Answer, Solution
					break;
				
				case '5': //events (Player loses life, Question skipped, Player dies, start vote, vote results)
					//TODO 
					break;
					
				case '0': //no input
					break;
					
				default: // Unexpected input
					/*
					 * try { throw new IOException(); } catch (IOException e) { e.printStackTrace();
					 * }
					 */
				}
			}
		}
	}

}
