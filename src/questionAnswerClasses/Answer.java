package questionAnswerClasses;

public class Answer {
	private Question quest;
	private String name;
	private String answer;
	
	public Answer(String query, String solution, String name, String answer) {
		this.quest = new Question(query, solution);
		this.name = name;
		this.answer = answer;
	}
	
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
	
	public String getBigAnswer() { //TODO rewrite to conform to clientInputReader
		return quest.getQuery() + "~"+ quest.getSolution() + "~" + name + "~" + answer;
	}
}
