package pl.edu.wszib.book.store.model;

public class User {
    int id;
    private String name;
    private String surname;
    private String login;
    private String pass;

    public User(int id, String name, String surname, String login, String pass) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.pass = pass;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
