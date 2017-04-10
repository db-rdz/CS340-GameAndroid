package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class RegisterCommand implements ICommand {
    private String username;
    private String password;

    public RegisterCommand() {
    }
    public RegisterCommand(String u, String p) {
        username = u;
        password = p;
    }

    @Override
    public List<ICommand> execute() throws IClient.UserAlreadyLoggedIn {

        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}