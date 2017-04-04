package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.ServerProxy;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * When a player wants more destination cards, this command will be called.
 * The client will respond with a  SelectRequestedDestinationCard command (though it could be multiple cards)
 *
 * Created by natha on 2/28/2017.
 */

public class GetDestinationCardsCommand implements ICommand {

    private int gameId;
    private String authenticationCode;

    public GetDestinationCardsCommand(int id, String code) {
        gameId = id;
        authenticationCode = code;
    }
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
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
