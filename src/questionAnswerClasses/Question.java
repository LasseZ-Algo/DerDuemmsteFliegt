package questionAnswerClasses;

public class Question {
	private String query;
	private String solution;
	
	public Question(String q, String s) {
		query = q;
		solution = s;
	}
	
	public String getQuery() {
		return query;
	}
	
	public String getSolution() {
		return solution;
	}
}
