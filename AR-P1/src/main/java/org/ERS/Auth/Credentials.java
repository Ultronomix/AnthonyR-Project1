package org.ERS.Auth;

public class Credentials {

    private String username;
    private String password;

    // Jackson requires that DTOs have a no-arg constructor
    public Credentials() {
        super();
    }

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
