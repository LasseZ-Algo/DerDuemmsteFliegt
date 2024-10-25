package server;

public class Answer {
	private Question quest;
	private String name;
	private String answer;
	
	public Answer(Question quest, String name, String answer) {
		this.quest = quest;
		this.name = name;
		this.answer = answer;
	}
	
	public String getQuestion() {
		return quest.getQuery();
	}
	
	public String getSolution() {
		return quest.getSolution();
	}
	
	public String getName() {
		return name;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public String getBigAnswer() {
		return "Frage: " + quest.getQuery() + "\n Lösung: " + quest.getSolution() + 
				"\n" + name + " : " + answer;
	}
}
