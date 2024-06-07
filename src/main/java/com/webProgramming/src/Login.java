package com.webProgramming.src;
import com.webProgramming.models.enums.UserType;

public class Login {
	String username, password;
    UserType type;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserType getType() {
		return type;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@Override
    public String toString() {
        return "Login [Username=" + username + "]";
    }
}
