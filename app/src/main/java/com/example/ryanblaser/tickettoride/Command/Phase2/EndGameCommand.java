package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.util.List;

/**
 * Created by natha on 4/4/2017.
 */

public class EndGameCommand implements ICommand {

    private int gameId;
    private String authenticationCode;

    public EndGameCommand(int id, String code) {
        gameId = id;
        authenticationCode = code;
    }

    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return authenticationCode;
    }

    public int getGameId() {
        return gameId;
    }
}
