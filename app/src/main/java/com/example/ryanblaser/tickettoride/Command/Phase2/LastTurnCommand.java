package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.GameModels.CardsModel.TrainCard;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.Client.IClient;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Do we need this class??
 * Created by natha on 3/30/2017.
 */

public class LastTurnCommand implements ICommand {

    //Data members
    private Route route;
    private int gameId;
    private String authenticationCode;
    private List<TrainCard> cardsUsed;

    //Constructors
    public LastTurnCommand(){}
    public LastTurnCommand(int g, String code, Route route1, List<TrainCard> cards) {
        route = route1;
        gameId = g;
        authenticationCode = code;
        cardsUsed = cards;
    }

    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException, IClient.UserAlreadyLoggedIn {
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return authenticationCode;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }

    public Route getRoute() {
        return route;
    }

    public int getGameId() {
        return gameId;
    }

    public List<TrainCard> getCardsUsed() {
        return cardsUsed;
    }
}
