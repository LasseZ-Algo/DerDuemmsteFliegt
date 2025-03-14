package questionAnswerClasses;

import java.util.Random;

public class AllQuestions {
	private Category[] categories;
	private Random random;
	
	public AllQuestions(Category[] categories) {
		this.categories = categories;
		random = new Random();
	}
	
	public Question getQuestion() {
		return categories[random.nextInt(categories.length)].getQuestion();
	}
	
	public Question getQuestion(int categoryIndex) {
		return categories[categoryIndex].getQuestion();
	}
}
