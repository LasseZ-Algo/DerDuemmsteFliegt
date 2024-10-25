package server;

import java.util.Random;

public class AllQuestions {
	private Categorie[] categories;
	private Random random;
	
	public AllQuestions() {
		//TODO invoke Categorie Constructors
		random = new Random();
	}
	
	public Question getQuestion() {
		return categories[random.nextInt(categories.length)].getQuestion();
	}
}
