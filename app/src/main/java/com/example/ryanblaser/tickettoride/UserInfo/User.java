package com.example.ryanblaser.tickettoride.UserInfo;

/**
 * Created by RyanBlaser on 2/5/17.
 */

public class User {

    //Data members
    private Username username;
    private Password password;
    private int int_authentication_code;

    //Constructor
    public User() {
        username = new Username();
        password = new Password();

        username.setUsername("");
        password.setPassword("");
        int_authentication_code = -1; //initialize to -1 for debugging purposes.
    }

    //Getters
    /*
     * Goes into the Username class and changes the string there
     */
    public String getUsername() {
        return username.getUsername();
    }

    /*
     * Goes into the Username class and changes the string there
     */
    public void setUsername(String username) {
        this.username.setUsername(username);
    }

    public int getInt_authentication_code() {
        return int_authentication_code;
    }

    //Setters
    /*
     * Goes into the Password class and grabs the string version of the password.
     */
    public String getPassword() {
        return password.getPassword();
    }

    /*
     * Goes into the Password class and changes the string there
     */
    public void setPassword(String password) {
        this.password.setPassword(password);
    }

    public void setInt_authentication_code(int int_authentication_code) {
        this.int_authentication_code = int_authentication_code;
    }
}
