package program;

import java.io.FileInputStream;

public class UserDAO {

	public UserDAO() {
//		FileInputStream fileInputStream = new FileInputStream()
	}

	public void addUser(String username, String password) {
		User user = new User(username, password);
	}

	public boolean isValidUser() {
		return false;
	}
}
