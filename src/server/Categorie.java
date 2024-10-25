package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Categorie {
	private List<Question> questionList = new ArrayList<>();
	private Random random;
	
	public Categorie() {
		//TODO create Questions in questionList
		random = new Random();
	}
	
	public Question getQuestion(){
		return questionList.remove(random.nextInt(questionList.size()));
	}
}
