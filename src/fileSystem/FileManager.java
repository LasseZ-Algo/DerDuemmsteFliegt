package fileSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import questionAnswerClasses.AllQuestions;
import questionAnswerClasses.Category;
import questionAnswerClasses.Question;

public class FileManager {

	private String path;

	public FileManager(String path) {
		this.path = path;
	}

	public void write(String filename, Category category) {
		try {
			FileWriter writer = new FileWriter(path + filename);
			writer.write(category.getAllQuestions());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write(String filename, String string) {
		try {
			FileWriter writer = new FileWriter(path + filename);
			writer.write(string);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readData(String filename) {

	}

	public Category read(String filename) {
		List<Question> category = new ArrayList<>();
		File file = new File(path + filename);
		try {
			Scanner reader = new Scanner(file);
			String filecontent = reader.nextLine();
			String[] questions = filecontent.split("|");
			for (String question : questions) {
				String[] quest = question.split("~");
				category.add(new Question(quest[0], quest[1]));
			}
			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Category(category);
	}

	public AllQuestions read(String[] filenames) {
		Category[] categories = new Category[filenames.length];
		for (int i = 0; i < filenames.length; i++) {
			categories[i] = read(filenames[i]);
		}
		return new AllQuestions(categories);
	}

	public boolean delete(String filename) {
		File file = new File(path + filename);
		if (file.delete()) {
			return true;
		} else {
			return false;
		}
	}
}
