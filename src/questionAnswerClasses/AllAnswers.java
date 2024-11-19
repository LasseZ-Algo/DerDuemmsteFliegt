package questionAnswerClasses;

import java.util.ArrayList;
import java.util.List;

public class AllAnswers {
	private List<Answer> answerList = new ArrayList<>();
	
	public void add(Question quest, String name, String answer) {
		answerList.add(new Answer(quest, name, answer));
	}
	
	public void add(Answer answer) {
		answerList.add(answer);
	}
	
	public List<Answer> getAnswers(){
		return answerList;
	}
	
	public String getAllAnswersString() {
		String allAnswersString = "";
		for(int i = 0; i < answerList.size(); i ++) {
			if(i == answerList.size()) {
				allAnswersString += answerList.get(i).getBigAnswer();
			} else {
				allAnswersString += answerList.get(i).getBigAnswer() + "§";
			}
		}
		return allAnswersString;
	}
	
	public void flush() {
		answerList.clear();
	}
}
