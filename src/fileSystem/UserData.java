package fileSystem;

public class UserData {
	private String UserID;
	private String UserName;
	
	public UserData(String id, String name) {
		UserID = id;
		UserName = name;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

}
