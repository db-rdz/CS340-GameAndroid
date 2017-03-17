package com.example.ryanblaser.tickettoride.Command.Phase2;

import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.iRoute;
import com.example.ryanblaser.tickettoride.Client.User;
import com.example.ryanblaser.tickettoride.Command.ICommand;
import com.example.ryanblaser.tickettoride.Server.IServer;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * This command is called whenever a player wants to claim a route.
 * A BroadcastCommand will be called after to tell other players when a route has been claimed.
 *
 * The route contains the game owner, weight (route length), and route color.
 * Use those to store data in the server side.
 *
 * Created by natha on 2/27/2017.
 */

public class ClaimRouteCommand implements ICommand {
    //Data members
    private Route route;
    private int gameId;
    String authenticationCode;

    //Constructors
    public ClaimRouteCommand(){}
    public ClaimRouteCommand(int g, String code, Route route1) {
        route = route1;
        gameId = g;
        authenticationCode = code;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return null;
    }

    @JsonIgnore
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

    public int getGameId()
    {
        return gameId;
    }

    public String getStrAuthenticationCode()
    {
        return authenticationCode;
    }

}
