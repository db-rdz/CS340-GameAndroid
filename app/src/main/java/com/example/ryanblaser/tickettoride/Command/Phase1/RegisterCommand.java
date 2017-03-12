package com.example.ryanblaser.tickettoride.Command.Phase1;
import com.example.ryanblaser.tickettoride.Command.ICommand;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class RegisterCommand implements ICommand {
    private User user;

    private RegisterCommand() {
    }

    public RegisterCommand(User u) {
        user = u;
    }

//
    @Override
    public List<ICommand> execute() {

        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return null;
    }


    @Override
    public User getUser() {
        return user;
    }


}