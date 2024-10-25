package server;

public class Question {
	private String query;
	private String solution;
	
	public Question(String q, String s) {
		query = q;
		solution = s;
	}
	
	String getQuery() {
		return query;
	}
	
	String getSolution() {
		return solution;
	}
}
