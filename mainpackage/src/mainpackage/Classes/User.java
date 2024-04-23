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
        this.name = name;
        this.surname = surname;
        System.out.println(type + " " + getName() + " " + getSurname() + " was created");
        count++;
    }

    public void register() {
        System.out.println("User " + type + " registered");
    }

    public void login() {
        System.out.println("User " + type + " logged in");
    }

    public void logout() {
        System.out.println("User " + type + " logged out");
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