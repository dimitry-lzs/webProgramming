package com.webProgramming.src;
import com.webProgramming.models.User;
import com.webProgramming.models.enums.UserType;

public class Login {
	private String username, password;
    private UserType type;

    private long id;

    private User user;

    public User getUser() {
        return user;
    }

    public long getId() {
        return id;
    }

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserType getType() {
		return type;
	}

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "Login [Username=" + username + "]" + "[ID=" + id + "]";
    }
}
