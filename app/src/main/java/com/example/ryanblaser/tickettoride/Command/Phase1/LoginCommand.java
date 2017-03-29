package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class LoginCommand implements ICommand {
    private User user;

    private LoginCommand() {
    }

    public LoginCommand(User u) {
        user = u;
    }

    @Override
    public List<ICommand> execute() {

        return null;
    }

    @JsonIgnore //So Jackson doesn't serialize this.
    @Override
    public String getAuthenticationCode() {
        return user.getStr_authentication_code();
    }

    @Override
    public User getUser() {
        return user;
    }

}