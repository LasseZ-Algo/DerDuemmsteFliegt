package client;

import java.util.Random;

public class CreateId {

	//if ID does not exist {
	void createId() {
		long time = (System.currentTimeMillis() / 1000L);
		Random randomValue = new Random();
		Long hash = time + randomValue.nextInt(10000);
		hash.hashCode();
		//Save hash in Directory
	}

}
