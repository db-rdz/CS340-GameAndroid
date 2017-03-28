package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.DestCard;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;

import java.util.List;

/**
 * Created by natha on 3/27/2017.
 */

public class FirstTurnCommand implements ICommand {

    private int gameId;
    private String authenticationCode;
    private List<DestCard> destCardsToKeep;

    public FirstTurnCommand(){}
    public FirstTurnCommand(int id, String code, List<DestCard> keepThese) {
        gameId = id;
        authenticationCode = code;
        destCardsToKeep = keepThese;
    }

    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return authenticationCode;
    }

    @Override
    public User getUser() {
        return null;
    }

    public int getGameId() {
        return gameId;
    }

    public List<DestCard> getDestCardsToKeep() {
        return destCardsToKeep;
    }
}
