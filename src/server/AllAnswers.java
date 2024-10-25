package server;

import java.util.ArrayList;
import java.util.List;

public class AllAnswers {
	private List<Answer> answerList = new ArrayList<>();
	
	public void add(Question quest, String name, String answer) {
		answerList.add(new Answer(quest, name, answer));
	}
	
	public List<Answer> getAnswers(){
		return answerList;
	}
	
	public void flush() {
		answerList.clear();
	}
}
