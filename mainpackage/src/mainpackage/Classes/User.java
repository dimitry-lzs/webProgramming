package mainpackage.Classes;
import mainpackage.enums.UserType;

public abstract class User {
    private static int count = 0;

    private UserType type;
    private String username;
    private String name;
    private String surname;

    public User(UserType type, String username, String name, String surname) {
        this.type = type;
        this.username = username;
        this.name = username;
        this.surname = username;

        System.out.println("User " + type + " created");
        count++;
    }

    public UserType getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public static int getCount() {
        return count;
    }
}