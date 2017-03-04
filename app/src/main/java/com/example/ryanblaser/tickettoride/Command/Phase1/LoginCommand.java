package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoginCommand implements ICommand {
    private User user;

    private LoginCommand() {
    }

    public LoginCommand(User u) {
        user = u;
    }

    @Override
    public CommandContainer execute() {
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

    @JsonIgnore
    @Override
    public Game getGame() {
        return null;
    }
}