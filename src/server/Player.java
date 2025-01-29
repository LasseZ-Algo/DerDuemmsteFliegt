package server;

public class Player {
	private int lifecount;
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(int life, String name) {
		this.lifecount = life;
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
	
	public void setLife(int life) {
		lifecount = life;
	}
}
