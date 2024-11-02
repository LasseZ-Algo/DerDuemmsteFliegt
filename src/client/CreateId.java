package client;

import java.util.Random;

public class CreateId {

	//if ID does not exist {
	void createId() {
		long time = System.currentTimeMillis();
		Random randomValue = new Random();
		Long id = time + randomValue.nextInt(100000);
		//Save hash in Directory
	}

}
