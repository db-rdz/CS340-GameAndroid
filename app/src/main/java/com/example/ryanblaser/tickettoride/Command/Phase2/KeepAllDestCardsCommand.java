package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by RyanBlaser on 3/28/17.
 */

public class KeepAllDestCardsCommand implements ICommand {

    private int gameId;
    private String authenticationCode;
    private List<DestCard> cardsKept;

    public KeepAllDestCardsCommand(){}

    public KeepAllDestCardsCommand(int id, String code, List<DestCard> list)
    {
        gameId = id;
        authenticationCode = code;
        cardsKept = list;
    }

    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return authenticationCode;
    }

    public int getGameId()
    {
        return gameId;
    }

    public List<DestCard> getCardsKept()
    {
        return cardsKept;
    }
}
