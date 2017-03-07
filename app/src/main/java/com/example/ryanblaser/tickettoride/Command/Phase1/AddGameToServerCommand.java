package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by RyanBlaser on 2/27/17.
 */

public class AddGameToServerCommand implements ICommand {
    private Game game;
    private String str_authentication_code;

    private AddGameToServerCommand() {
    }

    public AddGameToServerCommand(Game g, String k) {
        game = g;
        str_authentication_code = k;
    }

    @JsonProperty("str_authentication_code")
    @Override
    public String getAuthenticationCode() {
        return str_authentication_code;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }
    //
    @Override
    public List<ICommand> execute() {
        return null;

    }

    @Override
    public Game getGame() { return game; }
}