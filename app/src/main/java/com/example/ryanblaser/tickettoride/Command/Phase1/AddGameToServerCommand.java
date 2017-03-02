package com.example.ryanblaser.tickettoride.Command.Phase1;

import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;

/**
 * Created by RyanBlaser on 2/27/17.
 */

public class AddGameToServerCommand implements ICommand {
    private String str_authentication_code;

    public AddGameToServerCommand(String str_authentication_code) {
        this.str_authentication_code = str_authentication_code;
    }

    @Override
    public CommandContainer execute() throws IServer.GameIsFullException {
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return str_authentication_code;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public Game getGame() {
        return null;
    }
}
