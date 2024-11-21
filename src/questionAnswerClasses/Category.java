package questionAnswerClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Category {
	private List<Question> questionList = new ArrayList<>();
	private Random random;

	public Category(List<Question> questionList) {
		this.questionList = questionList;
		random = new Random();
	}

	public Question getQuestion() {
		return questionList.remove(random.nextInt(questionList.size()));
	}

	public String getAllQuestions() {
		String allQuestions = "";
		for (int i = 0; i < questionList.size(); i++) {
			if (i < questionList.size()) {
				allQuestions += questionList.get(i).getQuery() + "~" + questionList.get(i).getSolution() + "|";
			} else {
				allQuestions += questionList.get(i).getQuery() + "~" + questionList.get(i).getSolution();
			}
		}
		return allQuestions;
	}
}
