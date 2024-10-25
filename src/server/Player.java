package server;

public class Player {
	private int lifecount;
	private String name;
	
	public Player(int lifecount, String name) {
		this.lifecount = lifecount;
		this.name = name;
	}
	
	public int getLife() {
		return lifecount;
	}
	
	public String getName() {
		return name;
	}
	
	public void loseLife() {
		lifecount--;
	}
}
